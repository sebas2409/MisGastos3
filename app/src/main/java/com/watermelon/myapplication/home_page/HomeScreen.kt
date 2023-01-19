package com.watermelon.myapplication.home_page

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.watermelon.myapplication.R
import java.text.SimpleDateFormat
import java.util.*


@SuppressLint("SimpleDateFormat", "UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen() {

    val acme = FontFamily(Font(R.font.acme_regular))
    val josefina = FontFamily(Font(R.font.josefin_salb))
    val dateFormat = SimpleDateFormat("d MMM yyyy, EE", Locale("es", "ES"))
    val dateMonthFormat = SimpleDateFormat("MMMM", Locale("es", "ES"))
    val date = dateFormat.format(Date())
    val month = dateMonthFormat.format(Date())
    val colores =
        listOf(R.color.amarillo, R.color.verde, R.color.morado, R.color.rosa, R.color.azul)

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* ... */ },
                backgroundColor = colorResource(colores.random())
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }, floatingActionButtonPosition = FabPosition.End
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFE8EBEE))
                .padding(24.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.1f)
                    .padding(top = 16.dp)
            ) {
                Text(
                    text = "Hola Felix",
                    fontFamily = acme,
                    fontSize = 30.sp,
                    color = Color(0xFF333435)
                )
            }
            Text(text = date, fontFamily = josefina, fontSize = 22.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(32.dp))
            LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 150.dp)) {
                items(10) {
                    Card(
                        modifier = Modifier
                            .padding(8.dp)
                            .width(150.dp)
                            .height(150.dp),
                        backgroundColor = colorResource(colores.random()),
                        shape = RoundedCornerShape(16.dp),
                        elevation = 8.dp,
                    ) {
                        Column(modifier = Modifier.fillMaxSize()) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight(.5f)
                                    .padding(top = 16.dp),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = month.replaceFirstChar {
                                        if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
                                    },
                                    modifier = Modifier.padding(16.dp),
                                    fontFamily = acme,
                                    fontSize = 20.sp
                                )
                            }
                            Row(
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = "1234 $",
                                    modifier = Modifier
                                        .padding(16.dp)
                                        .background(Color.White, RoundedCornerShape(8.dp))
                                        .padding(8.dp),
                                    fontFamily = acme,
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
