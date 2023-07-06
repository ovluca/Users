@file:OptIn(ExperimentalGlideComposeApi::class)

package com.qdroid.users.ui.screens

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.qdroid.users.R
import com.qdroid.users.database.UserDB
import com.qdroid.users.ui.navigation.Screen
import com.qdroid.users.ui.state.UsersUiState
import com.qdroid.users.viewmodel.UsersViewModel

@ExperimentalMaterial3Api
@Composable
fun UsersScreen(navController: NavHostController, viewModel: UsersViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) },
            )
        },
        content = {
            val loginUiState = viewModel.uiState.collectAsState()
            when (val uiState = loginUiState.value) {
                is UsersUiState.Failure -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = uiState.error)
                    }
                }

                UsersUiState.Loading -> Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(
                            Alignment.Center
                        )
                    )
                }

                is UsersUiState.Success -> UsersGrid(
                    paddingValues = it,
                    users = uiState.users,
                    onClicked = { userId ->
                        Log.d("UsersScreen", "UsersScreen: $userId")
                        navController.navigate(
                            Screen.UserDetails.route.replace("{userId}", userId.toString(), true)
                        )
                    }
                )

                UsersUiState.Idle -> {

                }

                UsersUiState.Navigate -> {}
            }
        }
    )
}

@ExperimentalMaterial3Api
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun UsersGrid(
    paddingValues: PaddingValues,
    users: List<UserDB>,
    onClicked: (userId: Int) -> Unit
) {
    LazyVerticalStaggeredGrid(
        modifier = Modifier.padding(paddingValues),
        columns = StaggeredGridCells.Fixed(2),
        content = {
            items(users) { user: UserDB ->
                Card(modifier = Modifier.padding(all = 5.dp), onClick = {
                    Log.d("UsersGrid", "UsersGrid: ${user.id}")
                    onClicked(user.id)
                }) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        GlideImage(
                            model = user.avatar,
                            contentDescription = "avatar_image"
                        ) {
                            it.diskCacheStrategy(DiskCacheStrategy.NONE)
                        }
                        Column {
                            Text(
                                modifier = Modifier.padding(all = 5.dp),
                                text = "${user.firstName} ${user.lastName}"
                            )
                            Text(
                                modifier = Modifier.padding(all = 5.dp),
                                text = user.email,
                                style = TextStyle(fontSize = 10.sp)
                            )
                        }
                    }
                }
            }
        })
}