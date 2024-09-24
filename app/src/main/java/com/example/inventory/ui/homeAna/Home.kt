package com.example.inventory.ui.homeAna

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.inventory.R
import com.example.inventory.data.Item
import com.example.inventory.ui.AppViewModelProvider
import com.example.inventory.ui.home.HomeViewModel

//import com.example.inventory.ui.item.formatedPrice

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateToItemEntry: () -> Unit,
    navigateToItemUpdate: (Int) -> Unit,
    viewModel: HomeViewModel = viewModel(factory= AppViewModelProvider.Factory),
    modifier: Modifier = Modifier
) {

    val homeUiState by viewModel.homeUiState.collectAsState()

    Scaffold(
        modifier = modifier,
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .background(Color(0xFFC57BC8))
                    .border(1.5.dp, Color(0xFFAC54D8))
                    .padding(horizontal = 10.dp)
                    .statusBarsPadding()
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "Logo",
                        modifier = Modifier.size(50.dp).offset(x = 10.dp)
                    )
                    Text(
                        text = "HOME",
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        style = MaterialTheme.typography.titleMedium.copy(color = Color.Black),
                        modifier = Modifier
                            .offset(x = (-5).dp)
                    )
                    Spacer(modifier = Modifier.width(40.dp))
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemEntry,
                modifier = Modifier
                    .padding(16.dp)
                    .shadow(8.dp, RoundedCornerShape( topEnd = 16.dp,
                        bottomEnd = 16.dp,
                        bottomStart = 16.dp)),
                containerColor = Color(0xFFBD67E8),
                shape = RoundedCornerShape(
                    topEnd = 16.dp,
                    bottomEnd = 16.dp,
                    bottomStart = 16.dp
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Adicionar item",
                    tint = Color.White
                )
            }
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)) {

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 8.dp)
                    .offset(y = (-90).dp)
            ) {
                // Verifica se a lista está vazia
                if (homeUiState.itemList.isEmpty()) {
                    item {
                        Spacer(modifier = Modifier.height(60.dp))

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            // Adicionando uma imagem acima do texto
                            Image(
                                painter = painterResource(id = R.drawable.logo_fundo),
                                contentDescription = "logo",
                                modifier = Modifier.size(170.dp) // Tamanho da imagem
                            )

                            Spacer(modifier = Modifier.height(30.dp)) // Espaço entre a imagem e o texto

                            Text(
                                text = "Você ainda não possui nenhuma coleção",
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.fillMaxWidth(),
                                fontSize = 20.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                } else {
                    items(homeUiState.itemList) { item ->
                        InventoryItem(item = item,
                            modifier = Modifier
                                .padding(dimensionResource(id = R.dimen.padding_small))
                                .clickable { navigateToItemUpdate(item.id) }
                        )

                    }
                }
            }
        }
    }
}

@Composable
private fun InventoryItem(
    item: Item, modifier: Modifier = Modifier
) {

    val cardBgColor = Color(0xFFF0F0F0)

    Card(
        modifier = modifier
            .fillMaxWidth()
            .border(
                BorderStroke(2.dp, Color(0xFFF7B100)),
                RoundedCornerShape(
                    topEnd = 25.dp,
                    bottomStart = 25.dp
                )
            ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(
            topEnd = 25.dp,
            bottomStart = 25.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = cardBgColor
        )
    ) {

        Column(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small))
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = item.titulo,
                    color = Color.Black,
                    fontSize = 20.sp,
                    style = MaterialTheme.typography.titleLarge,
                )
            }
            Spacer(modifier = Modifier.height(1.dp))

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text =  "Autor: " + item.autor,
                    color = Color.Black,
                    fontSize = 18.sp,
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(Modifier.weight(1f))

                Box(
                    modifier = Modifier
                        .width(100.dp)
                        .background(Color(0xFFFFD555), RoundedCornerShape(15.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)

                ) {
                    Text(
                        text = item.ano.toString(),
                        fontSize = 18.sp,
                        color = Color.Black,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.align(Alignment.Center),
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun asdfji() {
    InventoryItem(Item(
        0,"titulo", "autor", "genero", 1930, "anotações"
    ))
}