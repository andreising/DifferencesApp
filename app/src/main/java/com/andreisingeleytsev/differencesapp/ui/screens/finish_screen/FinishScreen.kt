package com.andreisingeleytsev.differencesapp.ui.screens.finish_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.andreisingeleytsev.differencesapp.R
import com.andreisingeleytsev.differencesapp.ui.utils.TimeUtils
import com.andreisingeleytsev.differencesapp.ui.utils.UIEvent

@Composable
fun FinishScreen(
    navController: NavHostController,
    viewModel: FinishScreenViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect() {
            when (it) {
                is UIEvent.NavigatePopBack -> {
                    navController.navigate(it.route){
                        popUpTo(it.disableRoute){
                            inclusive = true
                        }
                    }
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
        Column(Modifier.padding(80.dp)) {
            Card(colors = CardDefaults.cardColors(
                containerColor = Color.Red
            ), shape = RoundedCornerShape(19.dp)
            ) {
                Text(text = viewModel.time.value, style = TextStyle(
                    color = Color.White, fontSize = 24.sp
                ),
                    modifier = Modifier.padding(
                        start = 20.dp,
                        end = 20.dp,
                        top = 5.dp,
                        bottom = 5.dp
                    )
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
            Card(colors = CardDefaults.cardColors(
                containerColor = Color.Red
            ), shape = RoundedCornerShape(19.dp), modifier = Modifier.clickable {
                viewModel.onEvent(FinishScreenEvents.OnNext)
            }
            ) {
                Text(text = "NEXT", style = TextStyle(
                    color = Color.White, fontSize = 24.sp
                ),
                    modifier = Modifier.padding(
                        start = 20.dp,
                        end = 20.dp,
                        top = 5.dp,
                        bottom = 5.dp
                    )
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
            Card(colors = CardDefaults.cardColors(
                containerColor = Color.Red
            ), shape = RoundedCornerShape(19.dp), modifier = Modifier.clickable {
                viewModel.onEvent(FinishScreenEvents.ToMenu)
            }
            ) {
                Text(text = "MENU", style = TextStyle(
                    color = Color.White, fontSize = 24.sp
                ),
                    modifier = Modifier.padding(
                        start = 20.dp,
                        end = 20.dp,
                        top = 5.dp,
                        bottom = 5.dp
                    )
                )
            }
        }
    }
}