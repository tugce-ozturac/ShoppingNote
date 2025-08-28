package com.tugce.shoppingnote.roomdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.tugce.shoppingnote.model.Item

@Dao
interface ItemDao {
    @Query("SELECT * FROM item")
    suspend fun getAllItems(): List<Item>

    @Query("SELECT * FROM item WHERE id = :id")
    suspend fun getItemById(id : Int) : Item?

    @Insert
    suspend fun insert(item : Item)

    @Delete
    suspend fun delete(item:Item)
}
