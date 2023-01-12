package com.gy.whatsnew.data

import com.gy.whatsnew.network.ApiHelper
import javax.inject.Inject

class ArticlesRepository @Inject constructor(private val apiHelper: ApiHelper) {
    suspend fun getArticles() = apiHelper.getArticles()
}