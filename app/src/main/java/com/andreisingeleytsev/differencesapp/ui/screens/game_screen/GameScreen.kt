package com.andreisingeleytsev.differencesapp.ui.screens.game_screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.andrei_singeleytsev.sportquizapp.presentation.dialog.DialogController
import com.andrei_singeleytsev.sportquizapp.presentation.dialog.DialogEvent
import com.andreisingeleytsev.differencesapp.R
import com.andreisingeleytsev.differencesapp.ui.utils.Routes
import com.andreisingeleytsev.differencesapp.ui.utils.TimeUtils
import com.andreisingeleytsev.differencesapp.ui.utils.UIEvent

@Composable
fun GameScreen(navController: NavHostController, viewModel: GameScreenViewModel = hiltViewModel()) {
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect() {
            when (it) {
                is UIEvent.Navigate -> {
                    navController.navigate(it.route)
                }
                is UIEvent.NavigatePopBack -> {
                    navController.navigate(it.route){
                        popUpTo(it.disableRoute){
                            inclusive = true
                        }
                    }
                }
                is UIEvent.PopBackStack -> {
                    navController.popBackStack()
                }

                else -> {

                }
            }
        }
    }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Image(
            painter = painterResource(id = R.drawable.main_bg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        if (viewModel.mainPicture.value != null) {
            val item = viewModel.mainPicture.value!!
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = item.drawableFirstId),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.FillWidth
                )
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(3.dp)) {
                    Card(colors = CardDefaults.cardColors(
                        containerColor = Color.Red
                    ), shape = RoundedCornerShape(19.dp)
                    ) {
                        Text(text = TimeUtils.getTime(viewModel.time.value), style = TextStyle(
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
                    Spacer(modifier = Modifier.width(8.dp))
                    Card(colors = CardDefaults.cardColors(
                        containerColor = Color.Red
                    ), shape = RoundedCornerShape(19.dp),
                        modifier = Modifier.clickable {
                            viewModel.onEvent(GameScreenEvent.OnFinish)
                        }
                    ) {
                        Text(text = "DONE", style = TextStyle(
                            color = Color.White, fontSize = 24.sp
                        ),
                            modifier = Modifier.padding(
                                start = 20.dp,
                                end = 20.dp,
                                top = 5.dp,
                                bottom = 5.dp
                            ))
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Card(colors = CardDefaults.cardColors(
                        containerColor = Color.Red
                    ), shape = RoundedCornerShape(19.dp),
                        modifier = Modifier.clickable {
                            viewModel.onEvent(GameScreenEvent.OnPause)
                        }
                    ) {
                        Icon(
                            painterResource(id = R.drawable.pause),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.padding(
                                start = 20.dp,
                                end = 20.dp,
                                top = 10.dp,
                                bottom = 10.dp
                            )
                        )
                    }
                }
                Image(
                    painter = painterResource(id = item.drawableSecondId),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.FillWidth
                )
            }
        }
    }
    GamePause(dialogController = viewModel)
}

@Composable
fun GamePause(
    dialogController: DialogController,
    viewModel: GameScreenViewModel = hiltViewModel()
) {
    if (dialogController.openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                dialogController.onDialogEvent(DialogEvent.OnCancel)
            },
            title = {
                Text(
                    text = dialogController.dialogTitle.value, style = TextStyle(
                        color = Color.White, fontSize = 22.sp, textAlign = TextAlign.Center
                    ), modifier = Modifier.fillMaxWidth()
                )
            },
            text = {
                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    Button(onClick = { viewModel.onEvent(GameScreenEvent.OnRestart) }, colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red
                    )) {
                        Text(text = "RESTART", style = TextStyle(
                            color = Color.White, fontSize = 22.sp
                        ))
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    Button(onClick = { viewModel.onEvent(GameScreenEvent.OnReturn) }, colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red
                    )) {
                        Text(text = "RETURN", style = TextStyle(
                            color = Color.White, fontSize = 22.sp
                        ))
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    Button(onClick = { viewModel.onEvent(GameScreenEvent.OnBackToMenu) }, colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red
                    )) {
                        Text(text = "MENU", style = TextStyle(
                            color = Color.White, fontSize = 22.sp
                        ))
                    }
                }
            },
            confirmButton = {

            },
            dismissButton = {

            },
            containerColor = Color.Transparent)
    }
}