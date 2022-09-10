package me.melkopisi.data.remote.models.mappers

import me.melkopisi.data.remote.models.BooksResponse
import me.melkopisi.domain.models.BooksDomainModel

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
    title = title,
    isbn = isbn,
    authorName = authorName
  )