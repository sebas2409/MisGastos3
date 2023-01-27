package com.watermelon.myapplication.di

import com.watermelon.myapplication.detail_home.data.network.ProductClient
import com.watermelon.myapplication.home_page.data.network.StorerClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModuleProvider {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://192.168.1.135:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideStorerClient(retrofit: Retrofit): StorerClient {
        return retrofit.create(StorerClient::class.java)
    }

    @Provides
    @Singleton
    fun provideProductClient(retrofit: Retrofit): ProductClient {
        return retrofit.create(ProductClient::class.java)
    }

}