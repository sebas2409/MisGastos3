package com.watermelon.myapplication.home_page.service

import com.watermelon.myapplication.domain.Products
import com.watermelon.myapplication.home_page.data.network.StorerClient
import javax.inject.Inject

class ProductStorer @Inject constructor(private val storerClient: StorerClient) {

    suspend fun storeProduct(product: Products): List<Products> {
        return storerClient.save(product)
    }

    suspend fun getProducts(): List<Products> {
        return storerClient.getProducts()
    }
}