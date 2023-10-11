package com.andreisingeleytsev.differencesapp.ui.screens.settings_screen

import android.media.MediaPlayer
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.andreisingeleytsev.differencesapp.R
import com.andreisingeleytsev.differencesapp.ui.screens.menu_screen.MenuScreenEvent
import com.andreisingeleytsev.differencesapp.ui.utils.UIEvent

@Composable
fun SettingsScreen(mediaPlayer: MediaPlayer?, viewModel: SettingsScreenViewModel = hiltViewModel()) {
    val sound = remember {
        mutableStateOf(mediaPlayer!!.isPlaying)
    }
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect() {
            when (it) {
                is UIEvent.ChangePlayer -> {
                    sound.value = !sound.value
                    if (sound.value) mediaPlayer?.start()
                    else mediaPlayer?.pause()
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
                viewModel.onEvent(SettingsScreenEvent.OnSoundPressed)
            }, colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red
            ), modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "SOUND", style = TextStyle(
                        color = Color.White, fontSize = 24.sp
                    ))
                    Icon(
                        painter = painterResource(
                            id = if (sound.value) R.drawable.yes
                            else R.drawable.no
                        ), contentDescription = null, tint = Color.White
                    )
                }
                
                
            }
        }
    }
}