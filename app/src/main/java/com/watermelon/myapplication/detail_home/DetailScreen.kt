package com.watermelon.myapplication.detail_home

import android.annotation.SuppressLint
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailScreen(detailViewModel: DetailViewModel) {

    detailViewModel.getData()
    val nameViewModel = detailViewModel.name.collectAsState()
    val productsViewModel = detailViewModel.products.collectAsState()

    Scaffold(topBar = {
        TopAppBar(
            title = { Text(nameViewModel.value, color = Color.White) },
            backgroundColor = Color(0xFF52A4EC)
        )
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFE8EBEE))
                .padding(16.dp)
        ) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                itemsIndexed(
                    items = productsViewModel.value,
                    key = { _, item -> item.id }) { _, item ->
                    val dismissState = rememberDismissState(
                        confirmStateChange = {
                            if (it == DismissValue.DismissedToStart || it == DismissValue.DismissedToEnd) {
                                detailViewModel.removeProduct(item)
                            }
                            true
                        }
                    )
                    SwipeToDismiss(
                        state = dismissState,
                        background = {
                            val color by animateColorAsState(
                                when (dismissState.targetValue) {
                                    DismissValue.Default -> Color.Transparent
                                    else -> Color.Red
                                }
                            )
                            val icon = Icons.Default.Delete
                            val scale by animateFloatAsState(
                                if (dismissState.targetValue == DismissValue.Default) 0.75f else 1f
                            )
                            Box(
                                Modifier
                                    .fillMaxSize()
                                    .background(color),
                                contentAlignment = Alignment.CenterEnd
                            ) {
                                Icon(
                                    icon,
                                    contentDescription = "Delete Icon",
                                    tint = Color.White,
                                    modifier = Modifier
                                        .scale(scale)
                                        .padding(PaddingValues(end = 5.dp))
                                )
                            }
                        },
                        directions = setOf(DismissDirection.EndToStart),
                        dismissThresholds = { FractionalThreshold(if (it == DismissDirection.EndToStart) 0.1f else 0.05f) }) {
                        Card(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(vertical = 8.dp),
                            elevation = 8.dp
                        ) {
                            Column {
                                Row(modifier = Modifier.padding(20.dp)) {
                                    Text(item.name, fontSize = 20.sp)
                                    Spacer(modifier = Modifier.padding(horizontal = 50.dp))
                                    Text(
                                        item.price.toString(),
                                        fontSize = 20.sp
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}