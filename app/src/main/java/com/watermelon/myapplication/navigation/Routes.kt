package com.watermelon.myapplication.navigation

sealed class Routes(val route:String){
    object Home: Routes("home")
    object Detail: Routes("detail")
}
