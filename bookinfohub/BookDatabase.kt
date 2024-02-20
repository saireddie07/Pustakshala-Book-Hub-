package com.example.bookinfohub

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Entitties::class], version=1)
abstract class BookDatabase: RoomDatabase() {
    abstract fun bookDao(): BookDao
}