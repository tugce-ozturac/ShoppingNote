package com.tugce.shoppingnote.viewmodel

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.tugce.shoppingnote.model.Item
import com.tugce.shoppingnote.roomdb.ItemDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ItemViewModel(application: Application) : AndroidViewModel(application) {
    private val db = Room.databaseBuilder(
        getApplication(),
        ItemDatabase::class.java, "Items"
    ).build()

    private val itemDao = db.itemDao()

    val itemList = mutableStateOf<List<Item>>(emptyList())
    val selectedItem = mutableStateOf<Item?>(null)

    fun getItemList() {
        viewModelScope.launch(Dispatchers.IO) {
            val items = itemDao.getAllItems()
            itemList.value = items
        }
    }

    fun getItem(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val item = itemDao.getItemById(id)
            selectedItem.value = item
        }
    }

    fun saveItem(item: Item) {
        viewModelScope.launch(Dispatchers.IO) {
            itemDao.insert(item)
            // kayıttan sonra listeyi yenile
            val items = itemDao.getAllItems()
            itemList.value = items
        }
    }

    fun deleteItem(item: Item) {
        viewModelScope.launch(Dispatchers.IO) {
            itemDao.delete(item)
            // silindikten sonra listeyi yenile
            val items = itemDao.getAllItems()
            itemList.value = items
        }
    }
}
