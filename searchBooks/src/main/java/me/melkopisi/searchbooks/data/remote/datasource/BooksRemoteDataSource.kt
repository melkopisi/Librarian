package me.melkopisi.searchbooks.data.remote.datasource

import kotlinx.coroutines.flow.Flow
import me.melkopisi.searchbooks.data.remote.models.BooksResponse.Doc

/*
 * Authored by Kopisi on 10 Sep, 2022.
 * Contact Me : m.elkopisi@gmail.com
 */
interface BooksRemoteDataSource {
  suspend fun searchBooks(query: String, offset: Int): Flow<List<Doc>>
}