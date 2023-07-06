package com.qdroid.users.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserDB::class], version = 1)
abstract class UsersDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

}