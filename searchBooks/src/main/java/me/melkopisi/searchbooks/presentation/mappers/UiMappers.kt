package me.melkopisi.searchbooks.presentation.mappers

import me.melkopisi.core.models.BooksUiModel
import me.melkopisi.searchbooks.domain.models.BooksDomainModel

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