package me.thekusch.ormantakipmobil.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.thekusch.ormantakipmobil.domain.repository.BaseRepository
import me.thekusch.ormantakipmobil.domain.useCase.GetFireDataUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    @Singleton
    fun provideGetFireDataUseCase(
        baseRepository: BaseRepository
    ) = GetFireDataUseCase(baseRepository)
}