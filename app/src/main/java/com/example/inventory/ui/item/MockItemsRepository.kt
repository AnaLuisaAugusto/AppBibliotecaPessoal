package com.example.inventory.ui.item

import com.example.inventory.data.Item
import com.example.inventory.data.ItemsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class MockItemsRepository : ItemsRepository {
    private val books = mutableListOf<Item>()

    override suspend fun insertItem(item: Item) {
        books.add(item) // Adiciona o item à lista
        println("Item adicionado: $item")
    }

    override fun getAllItemsStream(): Flow<List<Item>> {
        return flowOf(books)
    }

    override fun getItemStream(id: Int): Flow<Item?> {
        val book = books.find { it.id == id }
        return flowOf(book)
    }

    override suspend fun deleteItem(item: Item) {
        books.remove(item) // Implementação mockada para remover o item
    }

    override suspend fun updateItem(item: Item) {
        val index = books.indexOfFirst { it.id == item.id }
        if (index != -1) {
            books[index] = item // Atualiza o item na lista
        }
    }
}
