package com.qdroid.users.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.qdroid.users.ui.state.UserDetailsUiState
import com.qdroid.users.viewmodel.UserDetailsViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
@Composable
fun UserDetailsScreen(
    navController: NavHostController,
    viewModel: UserDetailsViewModel,
    userId: Int
) {
    LaunchedEffect(viewModel) {
        viewModel.getUsers(userId)
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) },
            )
        },
        content = {
            val userDetailsUiState = viewModel.uiState.collectAsState()
            when (val uiState = userDetailsUiState.value) {

                is UserDetailsUiState.Success -> Column(
                    modifier = Modifier.padding(it),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val user = uiState.user
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
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

                    GlideImage(
                        model = user.avatar,
                        contentDescription = "avatar_image"
                    ) { requestBuilder ->
                        requestBuilder.diskCacheStrategy(DiskCacheStrategy.NONE)
                    }

                }

                UserDetailsUiState.Idle -> {

                }

            }
        }
    )
}