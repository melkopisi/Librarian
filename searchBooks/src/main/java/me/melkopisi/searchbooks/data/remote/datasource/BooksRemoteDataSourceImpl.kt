package me.melkopisi.searchbooks.data.remote.datasource

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import me.melkopisi.core.exceptions.LibrarianException
import me.melkopisi.core.extensions.parseResponse
import me.melkopisi.searchbooks.data.network.api.BooksApi
import me.melkopisi.searchbooks.data.remote.models.BooksResponse.Doc
import javax.inject.Inject

/*
 * Authored by Kopisi on 10 Sep, 2022.
 * Contact Me : m.elkopisi@gmail.com
 */
class BooksRemoteDataSourceImpl @Inject constructor(private val api: BooksApi) : BooksRemoteDataSource {

  override suspend fun searchBooks(query: String, offset: Int): Flow<List<Doc>> =
    flowOf(api.searchBooks(query = query, offset = offset, limit = DEFAULT_LIMIT))
      .parseResponse()
      .map { response -> response.docs.map { doc -> doc.copy(isbn = doc.isbn?.take(5)) } }
      .map { it.ifEmpty { throw LibrarianException.NoData() } }

  companion object {
    const val DEFAULT_LIMIT = 15
  }
}