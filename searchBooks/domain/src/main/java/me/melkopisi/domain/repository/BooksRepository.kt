package me.melkopisi.domain.repository

import kotlinx.coroutines.flow.Flow
import me.melkopisi.domain.models.BooksDomainModel.Doc

/*
 * Authored by Kopisi on 10 Sep, 2022.
 * Contact Me : m.elkopisi@gmail.com
 */
interface BooksRepository {
  suspend fun searchBooks(query: String, offset: Int): Flow<List<Doc>>
}