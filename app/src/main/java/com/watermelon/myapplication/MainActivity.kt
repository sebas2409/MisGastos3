package com.watermelon.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.watermelon.myapplication.home_page.HomeScreen
import com.watermelon.myapplication.home_page.HomeViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val homeViewModel: HomeViewModel by viewModels()

            HomeScreen()
        }
    }
}
