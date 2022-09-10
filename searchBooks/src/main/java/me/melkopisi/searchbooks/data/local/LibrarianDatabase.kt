package me.melkopisi.searchbooks.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import me.melkopisi.searchbooks.data.local.entities.BooksEntity

/*
 * Authored by Kopisi on 10 Sep, 2022.
 * Contact Me : m.elkopisi@gmail.com
 */
@Database(entities = [BooksEntity::class], version = 1, exportSchema = false)
@TypeConverters(IsbnConverters::class)
abstract class LibrarianDatabase : RoomDatabase() {

  abstract fun booksDao(): BooksDao
}