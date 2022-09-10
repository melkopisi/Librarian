package me.melkopisi.data.remote.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import me.melkopisi.data.remote.datasource.BooksRemoteDataSource
import me.melkopisi.data.remote.models.mappers.toDomainModel
import me.melkopisi.domain.models.BooksDomainModel.Doc
import me.melkopisi.domain.repository.BooksRepository
import javax.inject.Inject

/*
 * Authored by Kopisi on 10 Sep, 2022.
 * Contact Me : m.elkopisi@gmail.com
 */
class BooksRepositoryImpl @Inject constructor(
  private val booksRemoteDataSource: BooksRemoteDataSource
) : BooksRepository {
  override suspend fun searchBooks(query: String, offset: Int): Flow<List<Doc>> =
    booksRemoteDataSource.searchBooks(query = query, offset = offset).map { it.map { doc -> doc.toDomainModel() } }
}