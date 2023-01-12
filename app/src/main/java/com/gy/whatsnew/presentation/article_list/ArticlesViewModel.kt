package com.gy.whatsnew.presentation.article_list

import android.accounts.NetworkErrorException
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gy.whatsnew.data.ArticlesRepository
import com.gy.whatsnew.data.model.Article
import com.gy.whatsnew.network.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

const val TAG = "ViewModel"

@HiltViewModel
class ArticlesViewModel @Inject constructor(private val repository: ArticlesRepository) : ViewModel() {

    private val _articlesRes = MutableLiveData<Resource<List<Article>>>()
    val articlesRes : LiveData<Resource<List<Article>>> = _articlesRes

    init {
        fetchArticles()
    }

    fun fetchArticles() {
        Log.i(TAG, "getArticles: pre fetch")

        _articlesRes.value = Resource.Loading()
        viewModelScope.launch {
            val response = repository.getArticles()

            if (!response.isSuccessful) {
                Log.i(TAG, "getArticles: error")
                _articlesRes.value = Resource.NetworkError(NetworkErrorException(response.errorBody()?.string() ?: "Unknown Network Error"))
            }
            else {
                Log.i(TAG, "getArticles: success")

                response.body()?.articles?.let {
                    _articlesRes.value = Resource.Success(it)
                } ?: run {
                    _articlesRes.value = Resource.NetworkError(Throwable("No Results!"))
                }
            }
        }
    }
}