package me.melkopisi.searchbooks.presentation.models.mappers

import me.melkopisi.searchbooks.domain.models.BooksDomainModel
import me.melkopisi.searchbooks.presentation.models.BooksUiModel

/*
 * Authored by Kopisi on 11 Sep, 2022.
 * Contact Me : m.elkopisi@gmail.com
 */

fun BooksDomainModel.Doc.toUiModel(): BooksUiModel.Doc =
  BooksUiModel.Doc(
    key = key,
    title = title,
    isbn = isbn,
    authorName = authorName,
    coverId = coverId
  )