package me.melkopisi.searchbooks.data.local.datasource

import kotlinx.coroutines.flow.Flow
import me.melkopisi.searchbooks.data.local.entities.BooksEntity

/*
 * Authored by Kopisi on 10 Sep, 2022.
 * Contact Me : m.elkopisi@gmail.com
 */
interface BooksLocalDataSource {

   fun saveAllBooks(books: List<BooksEntity>)
   fun getAllBooks(): Flow<List<BooksEntity>>
   fun clearAllBock()
}