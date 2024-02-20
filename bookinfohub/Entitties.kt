package com.example.bookinfohub

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class Entitties (
    @PrimaryKey val book_id:Int,
    @ColumnInfo (name="book_name") val bookName:String,
    @ColumnInfo (name ="book_image")val bookImage:String,
    @ColumnInfo (name ="book_author")val bookAuthor:String,
    @ColumnInfo (name="book_price")val bookPrice:String,
    @ColumnInfo (name ="book_rating") val bookRating:String
)
