@file:Suppress("UNCHECKED_CAST")

package com.qdroid.users.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.qdroid.users.data.repository.Repository
import com.qdroid.users.ui.state.UserDetailsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailsViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {

    private val _uiState: MutableStateFlow<UserDetailsUiState> =
        MutableStateFlow(UserDetailsUiState.Idle)
    internal val uiState: StateFlow<UserDetailsUiState> = _uiState.asStateFlow()

    fun getUsers(userId: Int) {
        viewModelScope.launch {

            Log.d("UserDetailsViewModel", "getUsers: $userId")

            repository.getUser(userId = userId).collect {
                _uiState.value = UserDetailsUiState.Success(it)
            }
        }
    }

}