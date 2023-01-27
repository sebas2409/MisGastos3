package com.watermelon.myapplication.home_page

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.watermelon.myapplication.domain.Products
import com.watermelon.myapplication.home_page.service.ProductStorer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val productStorer: ProductStorer) : ViewModel() {

    private val _products = MutableStateFlow<Map<String, List<Products>>>(emptyMap())
    val products = _products.asStateFlow()

    private val _showDialog = MutableStateFlow(false)
    val showDialog = _showDialog.asStateFlow()

    private val _name = MutableStateFlow("")
    val name = _name.asStateFlow()

    private val _price = MutableStateFlow("")
    val price = _price.asStateFlow()

    fun getData(){
        viewModelScope.launch {
            try {
                val response = productStorer.getProducts()
                if (response.isNotEmpty()) {
                    _products.value = response.groupBy { it.date }
                } else {
                    _products.value = emptyMap()
                }
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Error al obtener los productos", e)

            }
        }
    }

    fun storeProduct(context: Context) {
        viewModelScope.launch {
            val dateFormat = SimpleDateFormat("MMMM yyyy", Locale("es", "ES"))
            val date = dateFormat.format(Date())

            try {
                _products.value =
                    productStorer.storeProduct(
                        Products(
                            date,
                            _name.value,
                            _price.value.toDouble()
                        )
                    )
                        .groupBy { it.date }
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Error al guardar el producto", e)
                Toast.makeText(
                    context,
                    "No se ha podido guardar el producto, por favor intentelo mas tarde!",
                    Toast.LENGTH_LONG
                ).show()
            } finally {
                _showDialog.value = false
                _name.value = ""
                _price.value = ""
            }
        }
    }

    fun changeNameState(name: String) {
        _name.value = name
    }

    fun changePriceState(price: String) {
        _price.value = price
    }

    fun changeDialogState(show: Boolean) {
        _showDialog.value = show
    }

    fun demo() {
        val rs = products.value.entries.map { it -> it.key }.toList()
    }

}