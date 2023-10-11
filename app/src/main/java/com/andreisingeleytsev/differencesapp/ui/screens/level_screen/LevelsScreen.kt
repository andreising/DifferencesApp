package com.andreisingeleytsev.differencesapp.ui.screens.level_screen

import android.graphics.Picture
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.andreisingeleytsev.differencesapp.R
import com.andreisingeleytsev.differencesapp.data.entity.DifPictureItem
import com.andreisingeleytsev.differencesapp.ui.theme.DisabledColor
import com.andreisingeleytsev.differencesapp.ui.utils.UIEvent

@Composable
fun LevelsScreen(navController: NavHostController, viewModel: LevelScreenViewModel = hiltViewModel()) {
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
    val pictureList = viewModel.difPictureItemList.collectAsState(initial = emptyList())
    Image(
        painter = painterResource(id = R.drawable.main_bg),
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
    LazyColumn(modifier = Modifier
        .padding(30.dp)
        .fillMaxSize()) {
        items(pictureList.value){
            PictureItem(difPictureItem = it)
        }
    }
}

@Composable
fun PictureItem(difPictureItem: DifPictureItem, viewModel: LevelScreenViewModel = hiltViewModel()) {
    Column(modifier = Modifier
        .padding(top = 10.dp, bottom = 10.dp)
        .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "LEVEL 0"+difPictureItem.id.toString(), style = TextStyle(
            color = Color.Black, fontSize = 18.sp
        ))
        Spacer(modifier = Modifier.height(10.dp))
        Card(
            shape = RoundedCornerShape(18.dp), modifier = Modifier.clickable {
                viewModel.onEvent(LevelScreenEvent.OnPictureChose(difPictureItem))
            }
        ) {
            Box(contentAlignment = Alignment.TopEnd) {
                Image(
                    painter = painterResource(id = difPictureItem.drawableFirstId),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.FillWidth,
                    colorFilter = if (difPictureItem.isDisabled) ColorFilter.tint(
                        DisabledColor,
                        blendMode = BlendMode.Darken
                    ) else ColorFilter.tint(Color.Transparent, BlendMode.Color)
                )
                if (difPictureItem.isDisabled) Icon(
                    painter = painterResource(id = R.drawable.disabled),
                    contentDescription = null,
                    modifier = Modifier.padding(20.dp),
                    tint = Color.LightGray
                )
            }

        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "BEST TIME "+difPictureItem.bestTime, style = TextStyle(
            color = Color.Black, fontSize = 12.sp
        ))
    }
}