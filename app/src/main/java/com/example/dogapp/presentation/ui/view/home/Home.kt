package com.example.dogapp.presentation.ui.view.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.dogapp.R


@Composable
fun Home(modifier: Modifier = Modifier, viewModel: HomeViewModel = viewModel(factory = HomeViewModel.Factory) ) {
    val list = viewModel.dogList.observeAsState()
    LazyVerticalGrid(columns = GridCells.Fixed(2), verticalArrangement = Arrangement.SpaceAround, horizontalArrangement = Arrangement.SpaceAround) {
        list.value?.forEach() {
            dog->
            item {
                DogItem(modifier = Modifier.padding(10.dp), name = dog.name, breedGroup = dog.bredFor?:"Unknown", imageUrl = dog.url)
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview(showBackground = true)
@Composable
fun DogItem(modifier: Modifier = Modifier, name: String = "Unknown", breedGroup: String = "Unknown", imageUrl: String = "https://raw.githubusercontent.com/DevTides/DogsApi/master/1.jpg") {
    var isClick by remember {
        mutableStateOf(false)
    }
    Card(modifier = modifier) {
        Column {
            AsyncImage(model = imageUrl, contentDescription = null, modifier = Modifier.fillMaxSize().aspectRatio(1f), contentScale = ContentScale.Crop)
            Row(modifier = Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
                Text(text = name, fontWeight = FontWeight.Bold, fontSize = 20.sp, modifier = Modifier
                    .padding(top = 5.dp, bottom = 5.dp)
                    .weight(1f).basicMarquee(), maxLines = 1)
                IconButton(onClick = { isClick = !isClick }) {
                    val tintColor = if (isClick) {
                        Color.Red
                    }
                    else {
                        Color.Gray
                    }
                    Icon(imageVector = ImageVector.vectorResource(id = R.drawable.heart_icon), contentDescription = null, modifier = Modifier.size(20.dp), tint = tintColor)
                }
            }
            Text(text = breedGroup, color = Color.Gray, modifier = Modifier
                .padding(start = 5.dp, end = 5.dp, bottom = 10.dp).basicMarquee(), maxLines = 1)
        }
    }

}