package com.qdroid.users.ui.state

import com.qdroid.users.database.UserDB


sealed class UserDetailsUiState {
    object Idle : UserDetailsUiState()
    data class Success(val user: UserDB) : UserDetailsUiState()
}