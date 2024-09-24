package com.example.inventory.ui.item

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.inventory.InventoryTopAppBar
import com.example.inventory.R
import com.example.inventory.ui.AppViewModelProvider
import com.example.inventory.ui.Forms.AddBook
import com.example.inventory.ui.Forms.IconWithBackground
import com.example.inventory.ui.navigation.NavigationDestination
import com.example.inventory.ui.theme.InventoryTheme
import kotlinx.coroutines.launch

object ItemEditDestination : NavigationDestination {
    override val route = "item_edit"
    override val titleRes = R.string.edit_item_title
    const val itemIdArg = "itemId"
    val routeWithArgs = "$route/{$itemIdArg}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditBook(
    onStartClick: () -> Unit,
    viewModel: ItemEditViewModel = viewModel(factory = AppViewModelProvider.Factory)

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
                        text = "EDITAR LIVRO",
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

            // Dentro da sua função AddBook
            val coroutineScope = rememberCoroutineScope()

            Button(
                onClick = {
                    coroutineScope.launch {
                        viewModel.updateItem()
                        onStartClick()
                    }
                },
                modifier = Modifier
                    .width(200.dp)
                    .height(55.dp),
                shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFD555)),
                border = BorderStroke(2.dp, Color(0xFFF7B100)),
                enabled = itemUiState.isEntryValid // Habilita o botão apenas se os dados forem válidos
            ) {
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


