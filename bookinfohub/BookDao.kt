package com.example.bookinfohub

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BookDao {
    @Insert
    fun insertBook(Entity:Entitties)

    @Delete
    fun deleteBook(Entity: Entitties)

    @Query("SELECT * FROM books;")
    fun getAllBooks():List<Entitties>

    @Query("SELECT * FROM books WHERE book_id==:bookId;")
    fun getBookById(bookId:String):Entitties
}