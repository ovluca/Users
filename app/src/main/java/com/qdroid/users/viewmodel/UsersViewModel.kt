@file:Suppress("UNCHECKED_CAST")

package com.qdroid.users.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.qdroid.chargingstations.network.ApiResult
import com.qdroid.users.data.repository.Repository
import com.qdroid.users.database.UserDB
import com.qdroid.users.network.model.User
import com.qdroid.users.ui.state.UsersUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {

    private val _uiState: MutableStateFlow<UsersUiState> =
        MutableStateFlow(UsersUiState.Idle)
    internal val uiState: StateFlow<UsersUiState> = _uiState.asStateFlow()

    init {
        getUsers()
    }

    private fun getUsers() {
        viewModelScope.launch {
            _uiState.value = UsersUiState.Loading
            repository.getUsers().collect {
                when (it) {
                    is ApiResult.Error -> _uiState.value = UsersUiState.Failure(it.exception)
                    is ApiResult.Loading -> _uiState.value = UsersUiState.Loading
                    is ApiResult.Success -> {
                        val data: List<UserDB> = it.data as List<UserDB>
                        _uiState.value = UsersUiState.Success(data)
                    }
                }
            }
        }
    }

}