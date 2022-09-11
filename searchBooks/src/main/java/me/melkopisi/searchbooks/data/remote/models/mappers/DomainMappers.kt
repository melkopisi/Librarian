package me.melkopisi.searchbooks.data.remote.models.mappers

import me.melkopisi.searchbooks.data.remote.models.BooksResponse
import me.melkopisi.searchbooks.domain.models.BooksDomainModel

/*
 * Authored by Kopisi on 10 Sep, 2022.
 * Contact Me : m.elkopisi@gmail.com
 */

fun BooksResponse.toDomainModel(): BooksDomainModel =
  BooksDomainModel(
    numFound = numFound,
    start = start,
    numFoundExact = numFoundExact,
    docs = docs.map { it.toDomainModel() }
  )

fun BooksResponse.Doc.toDomainModel(): BooksDomainModel.Doc =
  BooksDomainModel.Doc(
    key = key,
    title = title,
    isbn = isbn,
    authorName = authorName,
    coverId = coverId
  )