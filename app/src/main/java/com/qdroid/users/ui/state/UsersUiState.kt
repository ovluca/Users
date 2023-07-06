package com.qdroid.users.ui.state

import com.qdroid.users.database.UserDB


sealed class UsersUiState {
    object Loading : UsersUiState()
    data class Failure(val error: String) : UsersUiState()
    object Idle : UsersUiState()
    data class Success(val users: List<UserDB>) : UsersUiState()
    object Navigate : UsersUiState()
}