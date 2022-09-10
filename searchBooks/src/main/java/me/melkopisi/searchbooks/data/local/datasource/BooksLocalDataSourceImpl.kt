package me.melkopisi.searchbooks.data.local.datasource

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import me.melkopisi.core.exceptions.LibrarianException
import me.melkopisi.searchbooks.data.local.BooksDao
import me.melkopisi.searchbooks.data.local.entities.BooksEntity
import javax.inject.Inject

/*
 * Authored by Kopisi on 10 Sep, 2022.
 * Contact Me : m.elkopisi@gmail.com
 */
class BooksLocalDataSourceImpl @Inject constructor(private val booksDao: BooksDao) : BooksLocalDataSource {
  override suspend fun saveAllBooks(books: List<BooksEntity>) = booksDao.insertBooks(books)

  override suspend fun getAllBooks(): Flow<List<BooksEntity>> =
    flowOf(booksDao.getBooks())
      .map { it.ifEmpty { throw LibrarianException.NoLocalData() } }
}