package com.watermelon.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import com.pusher.pushnotifications.PushNotifications
import com.watermelon.myapplication.detail_home.DetailViewModel
import com.watermelon.myapplication.home_page.HomeViewModel
import com.watermelon.myapplication.navigation.MyNavHostController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            PushNotifications.start(applicationContext, "de8b4774-5aa0-414f-b81e-57faeea4e81d");
            PushNotifications.addDeviceInterest("familia");
            PushNotifications.addDeviceInterest("debug-debug");

            val homeViewModel: HomeViewModel by viewModels()
            val detailViewModel: DetailViewModel by viewModels()

            val navHost = rememberNavController()
            MyNavHostController(
                navHost = navHost,
                homeViewModel = homeViewModel,
                detailViewModel = detailViewModel
            )
        }
    }
}
