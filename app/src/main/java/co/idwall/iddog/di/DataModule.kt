package co.idwall.iddog.di

import co.idwall.iddog.repository.IddogRepository
import co.idwall.iddog.repository.IddogRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Singleton
    @Binds
    abstract fun bindIddogRepositoryImpl(
        repository: IddogRepositoryImpl
    ): IddogRepository

}