package com.example.fragmentart.data

import com.example.fragmentart.data.Urls.baseUrl
import com.example.fragmentart.model.DataModelItem
import com.example.fragmentart.model.ShowModelItem
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("cache/article/list")
    fun getData(
        @Query("postperpage") postperpage: Int?,
        @Query("arg") arg: String?,
        @Query("page") page: Int?,
    ): Call<ArrayList<DataModelItem>>

    @GET("cache/article")
    fun getDetails(
        @Query("article_id") article_id: Int?
    ): Call<ShowModelItem>

    companion object Factory {
        fun create(): ApiInterface {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()

            val retrofit = Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create()).baseUrl(baseUrl)
                .client(client)
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}