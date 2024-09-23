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

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val homeUiState by viewModel.homeUiState.collectAsState()

    Scaffold(
        modifier = modifier,
        topBar = {
            // Navbar personalizada
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .background(Color(0xFFC57BC8)) // Cor de fundo
                    .border(1.5.dp, Color(0xFFAC54D8)) // Borda
                    .padding(horizontal = 8.dp)
                    .statusBarsPadding()
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo), // Imagem do logo
                        contentDescription = "Logo",
                        modifier = Modifier.size(60.dp)
                    )
                    Text(
                        text = "HOME",
                        textAlign = TextAlign.Center,
                        fontSize = 18.sp,
                        style = MaterialTheme.typography.titleMedium.copy(color = Color.Black)
                    )
                    Spacer(modifier = Modifier.width(40.dp))
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemEntry,
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Adicionar item")
            }
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)) {
            Spacer(modifier = Modifier.height(38.dp)) // Espaço entre a topBar e o primeiro item

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 8.dp)
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
                        InventoryItem(item = item)
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
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
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
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(Modifier.weight(1f))
                Text(
                    text = item.autor,
                    style = MaterialTheme.typography.titleMedium
                )
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
