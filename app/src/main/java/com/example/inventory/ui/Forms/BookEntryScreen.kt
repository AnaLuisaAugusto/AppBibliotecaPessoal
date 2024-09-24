package com.example.inventory.ui.Forms

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.inventory.R
import com.example.inventory.data.ItemsRepository
import com.example.inventory.data.Item
import com.example.inventory.ui.item.ItemDetails
//import com.example.inventory.ui.item.ItemEntryBody
import com.example.inventory.ui.item.ItemEntryViewModel
import com.example.inventory.ui.item.ItemUiState
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.inventory.ui.AppViewModelProvider
import com.example.inventory.ui.item.MockItemsRepository
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddBook(
    onStartClick: () -> Unit,
    viewModel: ItemEntryViewModel = viewModel(factory = AppViewModelProvider.Factory)

) {
    val itemUiState = viewModel.itemUiState

    Scaffold(
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
                        text = "ADICIONAR LIVRO",
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        style = MaterialTheme.typography.titleMedium.copy(color = Color.Black),
                        modifier = Modifier
                            .offset(x = (-5).dp)
                    )
                    Spacer(modifier = Modifier.width(40.dp))
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(horizontal = 16.dp)
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(30.dp))

            TextField(
                value = itemUiState.itemDetails.titulo,
                onValueChange = { viewModel.updateUiState(itemUiState.itemDetails.copy(titulo = it)) },
                placeholder = { Text("Título do livro") },
                modifier = Modifier.fillMaxWidth()
                    .border(1.5.dp, Color(0xFFF7B100), RoundedCornerShape(55.dp))
                    .shadow(6.dp, RoundedCornerShape(55.dp)),
                leadingIcon = {
                    IconWithBackground(R.drawable.book, "Ícone de livro")
                }
            )

            Spacer(modifier = Modifier.height(30.dp))

            TextField(
                value = itemUiState.itemDetails.autor,
                onValueChange = { viewModel.updateUiState(itemUiState.itemDetails.copy(autor = it)) },
                placeholder = { Text("Autor") },
                modifier = Modifier.fillMaxWidth()
                    .border(1.5.dp, Color(0xFFF7B100), RoundedCornerShape(55.dp))
                    .shadow(6.dp, RoundedCornerShape(55.dp)),
                leadingIcon = {
                    IconWithBackground(R.drawable.quill_pens, "Pena de Tinta")
                }
            )

            Spacer(modifier = Modifier.height(30.dp))

            TextField(
                value = itemUiState.itemDetails.genero,
                onValueChange = { viewModel.updateUiState(itemUiState.itemDetails.copy(genero = it)) },
                placeholder = { Text("Gênero") },
                modifier = Modifier.fillMaxWidth()
                    .border(1.5.dp, Color(0xFFF7B100), RoundedCornerShape(55.dp))
                    .shadow(6.dp, RoundedCornerShape(55.dp)),
                leadingIcon = {
                    IconWithBackground(R.drawable.etiqueta, "Etiqueta")
                }
            )

            Spacer(modifier = Modifier.height(30.dp))

            Spacer(modifier = Modifier.height(30.dp))

            // Campo Ano
            TextField(
                value = itemUiState.itemDetails.ano.toString(), // Converte para String
                onValueChange = { newValue ->
                    val newAno = newValue.toIntOrNull() ?: 0 // Converte String para Int, valor padrão 0
                    viewModel.updateUiState(itemUiState.itemDetails.copy(ano = newAno))
                },
                placeholder = { Text("Ano da Publicação") },
                modifier = Modifier.fillMaxWidth()
                    .border(1.5.dp, Color(0xFFF7B100), RoundedCornerShape(55.dp))
                    .shadow(6.dp, RoundedCornerShape(55.dp)),
                leadingIcon = {
                    IconWithBackground(R.drawable.calendar, "Ícone de calendário")
                }
            )

            Spacer(modifier = Modifier.height(30.dp))

            TextField(
                value = itemUiState.itemDetails.anotacoes,
                onValueChange = { viewModel.updateUiState(itemUiState.itemDetails.copy(anotacoes = it)) },
                placeholder = { Text("Anotações") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .border(1.5.dp, Color(0xFFF7B100), RoundedCornerShape(20.dp))
                    .shadow(6.dp, RoundedCornerShape(15.dp)),
                leadingIcon = {
                    IconWithBackground(R.drawable.chat_balloon, "Ícone de chat")
                }
            )

            Spacer(modifier = Modifier.height(60.dp))

            val coroutineScope = rememberCoroutineScope()

            Button(
                onClick = {
                    coroutineScope.launch {
                        viewModel.saveItem()
                        onStartClick()
                    }
                },
                modifier = Modifier
                    .width(200.dp)
                    .height(55.dp),
                shape = RoundedCornerShape(15.dp),
                border = BorderStroke(2.dp, Color(0xFFF7B100)),
                colors = ButtonDefaults.buttonColors(
                    containerColor =  Color(0xFFFFD555),
                    disabledContainerColor = Color(0xFFFFD555),
                    contentColor = Color.Black,
                    disabledContentColor = Color.DarkGray
                ),
                enabled = itemUiState.isEntryValid
            ){
                Text(
                    text = "FINALIZAR",
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Normal
                )
            }
        }
    }
}

@Composable
fun IconWithBackground(iconId: Int, contentDescription: String) {
    Box(
        modifier = Modifier
            .size(35.dp)
            .background(Color(0x80C180DB), shape = RoundedCornerShape(topStart = 16.dp, bottomStart = 16.dp))
            .padding(4.dp),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = contentDescription,
            tint = Color.Black,
            modifier = Modifier.size(30.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ItemEntryScreenPreview() {
    val mockRepository = MockItemsRepository()
    val viewModel = ItemEntryViewModel(mockRepository)

    AddBook(
        onStartClick = {},
        viewModel = viewModel
    )
}

