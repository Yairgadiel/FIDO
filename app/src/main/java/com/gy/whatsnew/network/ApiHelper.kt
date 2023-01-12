package com.gy.whatsnew.network

import com.gy.whatsnew.data.model.ArticlesResponse
import retrofit2.Response

interface ApiHelper {
    suspend fun getArticles() : Response<ArticlesResponse>
}