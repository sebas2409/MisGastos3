package com.watermelon.myapplication.detail_home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.watermelon.myapplication.detail_home.service.DetailProductStorer
import com.watermelon.myapplication.domain.ProductResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val detailProductStorer: DetailProductStorer) :
    ViewModel() {

    private val _actualMonth = MutableStateFlow("")
    val name = _actualMonth.asStateFlow()

    private val _products = MutableStateFlow<List<ProductResponse>>(emptyList())
    val products = _products.asStateFlow()

    fun getData() {
        viewModelScope.launch {
            try {
                val response = detailProductStorer.getProducts(_actualMonth.value).reversed()
                if (response.isNotEmpty()) {
                    _products.value = response
                } else {
                    _products.value = emptyList()
                }
            } catch (e: Exception) {
                e.printStackTrace()

            }
        }
    }

    fun changeName(month: String) {
        _actualMonth.value = month
    }

    fun removeProduct(it: ProductResponse) {
        viewModelScope.launch {
            try {
                detailProductStorer.deleteProduct(it.id.toString())
                getData()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }
}