package com.example.inventory.ui.Forms

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.inventory.data.Item
import com.example.inventory.data.ItemsRepository

class HomeViewModel(private val itemsRepository: ItemsRepository) : ViewModel() {

    var itemUiState by mutableStateOf(ItemUiState())
        private set

    fun updateUiState(itemDetails: ItemDetails) {
        itemUiState = ItemUiState(itemDetails = itemDetails, isEntryValid = validateInput(itemDetails))
    }

    private fun validateInput(uiState: ItemDetails = itemUiState.itemDetails): Boolean {
        return with(uiState) {
            titulo.isNotBlank() && autor.isNotBlank() && genero.isNotBlank() && ano > 0
        }
    }

    suspend fun saveItem() {
        if (validateInput()) {
            itemsRepository.insertItem(itemUiState.itemDetails.toItem())
        }
    }
}

data class ItemUiState(
    val itemDetails: ItemDetails = ItemDetails(),
    val isEntryValid: Boolean = false
)

data class ItemDetails(
    val id: Int = 0,
    val titulo: String = "",
    val autor: String = "",
    val genero: String = "",
    val ano: Int = 0,
    val anotacoes: String = ""
)

fun ItemDetails.toItem(): Item = Item(
    id = id,
    titulo = titulo,
    autor = autor,
    genero = genero,
    ano = ano,
    anotacoes = anotacoes
)

fun Item.toUiState(isEntryValid: Boolean = false): ItemUiState = ItemUiState(
    itemDetails = this.toItemDetails(),
    isEntryValid = isEntryValid
)

fun Item.toItemDetails(): ItemDetails = ItemDetails(
    id = id,
    titulo = titulo,
    autor = autor,
    genero = genero,
    ano = ano,
    anotacoes = anotacoes
)
