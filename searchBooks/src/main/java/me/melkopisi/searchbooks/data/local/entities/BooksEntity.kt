package me.melkopisi.searchbooks.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/*
 * Authored by Kopisi on 10 Sep, 2022.
 * Contact Me : m.elkopisi@gmail.com
 */
@Entity(tableName = "books_table")
data class BooksEntity(
  @PrimaryKey(autoGenerate = true) val id: Int = 0,
  val key: String,
  val title: String,
  val isbn: List<String>?,
  val authorName: List<String>?,
  val coverId: String?
)