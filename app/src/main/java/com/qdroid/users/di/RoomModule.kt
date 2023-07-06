package com.qdroid.users.di

import android.content.Context
import androidx.room.Room
import com.qdroid.users.database.UserDao
import com.qdroid.users.database.UsersDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): UsersDatabase {
        return Room.databaseBuilder(
            context,
            UsersDatabase::class.java,
            "users-db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideUserDao(appDatabase: UsersDatabase): UserDao {
        return appDatabase.userDao()
    }

}