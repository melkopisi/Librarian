package me.melkopisi.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import me.melkopisi.data.local.entities.BooksEntity

/*
 * Authored by Kopisi on 10 Sep, 2022.
 * Contact Me : m.elkopisi@gmail.com
 */
@Dao
interface BooksDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE) fun insertBooks(books: List<BooksEntity>)

  @Query("SELECT * FROM books_table")
  suspend fun getBooks(): List<BooksEntity>
}