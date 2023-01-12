package com.gy.whatsnew.network

import com.gy.whatsnew.data.model.ArticlesResponse
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(
    private val apiContract: ApiContract
): ApiHelper {
    override suspend fun getArticles(): Response<ArticlesResponse> = apiContract.getArticles()
}