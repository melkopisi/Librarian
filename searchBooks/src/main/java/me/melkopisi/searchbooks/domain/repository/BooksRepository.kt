package me.melkopisi.searchbooks.domain.repository

import kotlinx.coroutines.flow.Flow
import me.melkopisi.searchbooks.domain.models.BooksDomainModel.Doc

/*
 * Authored by Kopisi on 10 Sep, 2022.
 * Contact Me : m.elkopisi@gmail.com
 */
interface BooksRepository {
  suspend fun searchBooks(query: String, offset: Int, isNewQuery: Boolean): Flow<List<Doc>>
}