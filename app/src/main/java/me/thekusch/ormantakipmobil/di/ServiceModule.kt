package me.thekusch.ormantakipmobil.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.thekusch.ormantakipmobil.data.BaseService
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun provideBaseService(retrofit: Retrofit) =
        retrofit.create(BaseService::class.java)
}