package com.andreisingeleytsev.differencesapp.ui.screens.menu_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.andreisingeleytsev.differencesapp.R
import com.andreisingeleytsev.differencesapp.ui.utils.UIEvent

@Composable
fun MenuScreen(navController: NavHostController, viewModel: MenuScreenViewModel = hiltViewModel()) {
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect() {
            when (it) {
                is UIEvent.Navigate -> {
                    navController.navigate(it.route)
                }

                else -> {

                }
            }
        }
    }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(id = R.drawable.main_bg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(modifier = Modifier.width(160.dp)) {
            Button(onClick = {
                viewModel.onEvent(MenuScreenEvent.OnPlayPressed)
            }, colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red
            ), modifier = Modifier.fillMaxWidth()) {
                Text(text = "PLAY", style = TextStyle(
                    color = Color.White, fontSize = 24.sp
                ))
            }
            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = {
                viewModel.onEvent(MenuScreenEvent.OnSettingsPressed)
            }, colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red
            ), modifier = Modifier.fillMaxWidth()) {
                Text(text = "SETTINGS", style = TextStyle(
                    color = Color.White, fontSize = 24.sp
                ))
            }
            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = {
                viewModel.onEvent(MenuScreenEvent.OnRulesPressed)
            }, colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red
            ), modifier = Modifier.fillMaxWidth()) {
                Text(text = "RULES", style = TextStyle(
                    color = Color.White, fontSize = 24.sp
                ))
            }
        }
    }
}