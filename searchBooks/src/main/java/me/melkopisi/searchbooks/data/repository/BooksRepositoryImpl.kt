package me.melkopisi.searchbooks.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import me.melkopisi.core.exceptions.LibrarianException
import me.melkopisi.searchbooks.data.local.datasource.BooksLocalDataSource
import me.melkopisi.searchbooks.data.local.entities.mappers.toDomainModel
import me.melkopisi.searchbooks.data.local.entities.mappers.toEntity
import me.melkopisi.searchbooks.data.remote.datasource.BooksRemoteDataSource
import me.melkopisi.searchbooks.data.remote.models.mappers.toDomainModel
import me.melkopisi.searchbooks.domain.models.BooksDomainModel
import me.melkopisi.searchbooks.domain.repository.BooksRepository
import javax.inject.Inject

/*
 * Authored by Kopisi on 10 Sep, 2022.
 * Contact Me : m.elkopisi@gmail.com
 */
class BooksRepositoryImpl @Inject constructor(
  private val booksRemoteDataSource: BooksRemoteDataSource,
  private val booksLocalDataSource: BooksLocalDataSource
) : BooksRepository {

  @OptIn(FlowPreview::class)
  override suspend fun searchBooks(query: String, offset: Int, isNewQuery: Boolean): Flow<List<BooksDomainModel.Doc>> {
    return try {

      booksRemoteDataSource.searchBooks(query = query, offset = offset).flatMapMerge { docs ->
        withContext(Dispatchers.IO) {
          if (isNewQuery) booksLocalDataSource.clearAllBooks()
          booksLocalDataSource.saveAllBooks(docs.map { it.toEntity() })
          flowOf(docs.map { it.toDomainModel() })
        }
      }
    } catch (throwable: LibrarianException) {
      if (throwable is LibrarianException.NetworkNotAvailable) {
        withContext(Dispatchers.IO) {
          booksLocalDataSource.getAllBooks().map { it.map { it.toDomainModel() } }
        }
      } else {
        throw throwable
      }
    }
  }
}