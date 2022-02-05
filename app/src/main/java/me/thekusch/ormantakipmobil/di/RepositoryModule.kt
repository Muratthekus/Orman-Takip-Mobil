package me.thekusch.ormantakipmobil.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.thekusch.ormantakipmobil.data.BaseService
import me.thekusch.ormantakipmobil.data.repository.BaseRepositoryImpl
import me.thekusch.ormantakipmobil.domain.repository.BaseRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideBaseRepository(baseService: BaseService): BaseRepository {
        return BaseRepositoryImpl(baseService)
    }
}