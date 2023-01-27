package com.watermelon.myapplication.detail_home.service

import com.watermelon.myapplication.detail_home.data.network.ProductClient
import com.watermelon.myapplication.domain.ProductResponse
import com.watermelon.myapplication.domain.Products
import javax.inject.Inject

class DetailProductStorer @Inject constructor(private val productClient: ProductClient){

    suspend fun getProducts(month: String): List<ProductResponse> {
        return productClient.getProducts(month)
    }

    suspend fun deleteProduct(id: String): String {
        return productClient.deleteProduct(id)
    }
}