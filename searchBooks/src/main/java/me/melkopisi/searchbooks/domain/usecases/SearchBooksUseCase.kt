package me.melkopisi.searchbooks.domain.usecases

import kotlinx.coroutines.flow.Flow
import me.melkopisi.searchbooks.domain.models.BooksDomainModel.Doc
import me.melkopisi.searchbooks.domain.repository.BooksRepository
import javax.inject.Inject

/*
 * Authored by Kopisi on 10 Sep, 2022.
 * Contact Me : m.elkopisi@gmail.com
 */
class SearchBooksUseCase @Inject constructor(private val booksRepository: BooksRepository) {
  suspend operator fun invoke(query: String, offset: Int): Flow<List<Doc>> = booksRepository.searchBooks(query = query, offset = offset)
}