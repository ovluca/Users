package com.qdroid.users.di

import android.content.Context
import android.content.SharedPreferences
import com.qdroid.users.data.datasource.local.LocalDataSource
import com.qdroid.users.data.datasource.local.LocalDataSourceImpl
import com.qdroid.users.data.datasource.remote.RemoteDataSource
import com.qdroid.users.data.datasource.remote.RemoteDataSourceImpl
import com.qdroid.users.data.repository.Repository
import com.qdroid.users.data.repository.RepositoryImpl
import com.qdroid.users.database.UserDao
import com.qdroid.users.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun providesSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences("token", Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun providesLocalDataSource(userDao: UserDao): LocalDataSource =
        LocalDataSourceImpl(userDao)

    @Provides
    @Singleton
    fun provideRemoteDataSource(apiService: ApiService): RemoteDataSource {
        return RemoteDataSourceImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource,
    ): Repository {
        return RepositoryImpl(remoteDataSource, localDataSource)
    }

}