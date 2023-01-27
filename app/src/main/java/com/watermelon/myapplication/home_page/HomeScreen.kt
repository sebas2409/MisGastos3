package com.watermelon.myapplication.home_page

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.watermelon.myapplication.R
import com.watermelon.myapplication.detail_home.DetailViewModel
import com.watermelon.myapplication.navigation.Routes
import java.text.SimpleDateFormat
import java.util.*


@SuppressLint("SimpleDateFormat", "UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    detailViewModel: DetailViewModel,
    navController: NavHostController
) {
    homeViewModel.getData()

    val showViewModel = homeViewModel.showDialog.collectAsState()
    val nameViewModel = homeViewModel.name.collectAsState()
    val priceViewModel = homeViewModel.price.collectAsState()
    val productsViewModel = homeViewModel.products.collectAsState()

    val context = LocalContext.current

    val acme = FontFamily(Font(R.font.acme_regular))
    val josefina = FontFamily(Font(R.font.josefin_salb))
    val dateFormat = SimpleDateFormat("d MMM yyyy, EE", Locale("es", "ES"))
    val date = dateFormat.format(Date())

    val colores =
        listOf(R.color.amarillo, R.color.verde, R.color.morado, R.color.rosa, R.color.azul)

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { homeViewModel.changeDialogState(true) }, //Mostrar Alert Dialog
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
                    text = "Hola Familia",
                    fontFamily = acme,
                    fontSize = 30.sp,
                    color = Color(0xFF333435)
                )
            }
            Text(text = date, fontFamily = josefina, fontSize = 22.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(32.dp))

            LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 150.dp)) {
                items(productsViewModel.value.size) {
                    Card(
                        modifier = Modifier
                            .padding(8.dp)
                            .width(150.dp)
                            .height(150.dp)
                            .clickable {
                                detailViewModel.changeName(
                                    productsViewModel.value.entries
                                        .map { entry ->
                                            entry.key
                                        }
                                        .reversed()
                                        .toList()[it]
                                        .replaceFirstChar {
                                            if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
                                        })
                                navController.navigate(Routes.Detail.route)
                            },
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
                                    text = productsViewModel.value.entries.map { entry ->
                                        entry.key.split(
                                            " "
                                        )[0]
                                    }
                                        .reversed()
                                        .toList()[it]
                                        .replaceFirstChar {
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
                                    text = productsViewModel.value.entries.map { entry -> entry.value }
                                        .toList()
                                        .reversed()[it]
                                        .sumOf { it.price }
                                        .toString(),
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

    if (showViewModel.value) {
        AlertDialog(
            onDismissRequest = { homeViewModel.changeDialogState(false) },
            title = { Text("Ingresar Producto") },
            text = {
                Column {
                    OutlinedTextField(
                        value = nameViewModel.value,
                        onValueChange = { homeViewModel.changeNameState(it) },
                        label = {
                            Text(
                                text = "Nombre"
                            )
                        })
                    Spacer(modifier = Modifier.height(16.dp))
                    OutlinedTextField(
                        value = priceViewModel.value,
                        onValueChange = {
                            homeViewModel.changePriceState(it)
                        },
                        label = {
                            Text(
                                text = "Precio"
                            )
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Done,
                            capitalization = KeyboardCapitalization.None
                        ),
                    )
                }

            },
            confirmButton = {
                Button(onClick = { homeViewModel.storeProduct(context) }) {
                    Text("Guardar")
                }
            }
        )
    }
}


