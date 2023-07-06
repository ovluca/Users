package com.qdroid.users.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "users")
data class UserDB(
    @PrimaryKey
    @Json(name = "id") val id: Int,
    @Json(name = "email") val email: String,
    @Json(name = "first_name") val firstName: String,
    @Json(name = "last_name") val lastName: String,
    @Json(name = "avatar") val avatar: String,
)