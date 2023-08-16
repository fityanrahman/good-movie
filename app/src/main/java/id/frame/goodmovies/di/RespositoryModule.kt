package id.frame.goodmovies.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.frame.goodmovies.data.repository.NetworkRepositoryImpl
import id.frame.goodmovies.domain.repository.NetworkRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RespositoryModule {

    @Binds
    @Singleton
    abstract fun bindRepository(repositoryImpl: NetworkRepositoryImpl): NetworkRepository
}