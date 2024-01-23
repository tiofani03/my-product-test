package com.tiooooo.myproduct.di

import com.tiooooo.myproduct.data.ProductRepository
import com.tiooooo.myproduct.data.ProductRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProductModule {
    @Provides
    @Singleton
    fun provideProductRepository(
    ): ProductRepository {
        return ProductRepositoryImpl()
    }
}
