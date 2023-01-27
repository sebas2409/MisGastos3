package com.watermelon.myapplication.detail_home.data.network

import com.watermelon.myapplication.domain.ProductResponse
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductClient {


    @GET("/products/{month}")
    suspend fun getProducts(@Path("month") month: String): List<ProductResponse>

    @DELETE("/delete/{id}")
    suspend fun deleteProduct(@Path("id") id: String): String

}