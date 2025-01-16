package com.example.audiobooks.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.HtmlCompat
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.audiobooks.PodcastsViewModel
import com.example.audiobooks.R

@Composable
fun PodcastDetailScreen(
    viewModel: PodcastsViewModel,
    podCastId: String,
    navController: NavHostController,
) {
    val podcast by viewModel.podcast.collectAsState()

    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(horizontal = 15.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Row(
            modifier = Modifier
                .height(50.dp)
                .align(Alignment.Start)
                .clickable { navController.popBackStack() },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back"
            )
            Text(text = "Back", fontWeight = FontWeight.Bold)
        }
        if (podcast.id == "") {
            CircularProgressIndicator()
        } else {
            Text(
                text = podcast.title ?: "",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 20.dp)
            )
            Text(
                text = podcast.publisher ?: "",
                fontSize = 13.sp,
                color = Color.Gray,
                fontStyle = FontStyle.Italic,
                modifier = Modifier
                    .padding(5.dp)
            )
            AsyncImage(
                model = podcast.image,
                contentDescription = null,
                modifier = Modifier
                    .padding(10.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .height(250.dp)
            )
            Button(
                onClick = { viewModel.updateFavouritePodcast(podCastId) },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(R.color.favorite),
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = if (podcast.favourite) "Favourited" else "Favourite",
                    fontSize = 13.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                )
            }

            val spannedText = HtmlCompat.fromHtml(podcast.description ?: "", 0)
            val description = buildAnnotatedString {
                append(spannedText)
            }
            Text(
                text = description,
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                color = Color.Gray,
                modifier = Modifier
                    .padding(top = 15.dp)
            )
        }
    }
}