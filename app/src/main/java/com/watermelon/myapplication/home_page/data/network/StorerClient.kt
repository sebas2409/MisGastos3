package com.watermelon.myapplication.home_page.data.network

import com.watermelon.myapplication.domain.Products
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface StorerClient {

    @POST("/save")
    suspend fun save(@Body products: Products): List<Products>

    @GET("/get")
    suspend fun getProducts(): List<Products>
}