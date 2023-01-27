package com.watermelon.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.watermelon.myapplication.detail_home.DetailScreen
import com.watermelon.myapplication.detail_home.DetailViewModel
import com.watermelon.myapplication.home_page.HomeScreen
import com.watermelon.myapplication.home_page.HomeViewModel

@Composable
fun MyNavHostController(
    navHost: NavHostController,
    homeViewModel: HomeViewModel,
    detailViewModel: DetailViewModel
) {
    NavHost(navController = navHost, startDestination = Routes.Home.route) {
        composable(Routes.Home.route) {
            HomeScreen(homeViewModel = homeViewModel, detailViewModel = detailViewModel, navController = navHost)
        }
        composable(Routes.Detail.route){
            DetailScreen(detailViewModel)
        }
    }
}