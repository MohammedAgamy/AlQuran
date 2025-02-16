package com.example.alquran.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.bumptech.glide.Glide
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.alquran.DataStateQuran
import com.example.alquran.Intent
import com.example.alquran.data.datapages.Page
import com.example.alquran.model.QuranViewModel

@Composable
fun AlQuranScreen(surahNumber: Int) {

    val viewModel: QuranViewModel = viewModel() // Automatically provides the context
    val state by viewModel.state.collectAsState()

// Trigger API Call
    LaunchedEffect(Unit) {
        viewModel.handelIntent(Intent.DataIntent)
        Log.d("ScreenState", "State updated: $state")
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        when (state) {
            is DataStateQuran.Loading -> CircularProgressIndicator()
            is DataStateQuran.Success -> QuranBookView((state as DataStateQuran.Success).message)
            is DataStateQuran.Error -> Text("Error: ${(state as DataStateQuran.Error).error}")
            else -> {}
        }
    }
}

@Composable
fun QuranBookView(pages: List<Page>) {
    val viewModel: QuranViewModel = viewModel() // Automatically provides the context
    val pagerState = rememberPagerState(
        initialPage = viewModel.lastPageIndex.collectAsState().value,
        pageCount = { (pages.size + 1) / 2 })


    LaunchedEffect(pagerState.currentPage) {
        viewModel.saveLastPage(pagerState.currentPage) // Save page when changed
    }

    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxSize()
    ) { pageIndex ->
        val leftPage = pages.getOrNull(pageIndex)

        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            leftPage?.let {
                QuranPageItem(it, Modifier.fillMaxSize())
            }

        }
    }
}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun QuranPageItem(page: Page, modifier: Modifier) {
    GlideImage(
        model = page.page_url,
        contentDescription = "Quran Page ${page.page_number}",
        modifier = modifier
            .width(200.dp) // Set custom width
            .height(300.dp) // Set custom height
            .padding(4.dp)
            .clip(RoundedCornerShape(8.dp)),
        contentScale = ContentScale.FillBounds // Ensures it fills the given size
    )
}


