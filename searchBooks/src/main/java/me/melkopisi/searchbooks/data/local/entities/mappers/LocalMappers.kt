package me.melkopisi.searchbooks.data.local.entities.mappers

import me.melkopisi.searchbooks.data.local.entities.BooksEntity
import me.melkopisi.searchbooks.data.remote.models.BooksResponse
import me.melkopisi.searchbooks.domain.models.BooksDomainModel

/*
 * Authored by Kopisi on 10 Sep, 2022.
 * Contact Me : m.elkopisi@gmail.com
 */

fun BooksResponse.Doc.toEntity(): BooksEntity =
  BooksEntity(
    key = key,
    title = title,
    isbn = isbn,
    authorName = authorName,
    coverId = coverId
  )

fun BooksEntity.toDomainModel(): BooksDomainModel.Doc =
  BooksDomainModel.Doc(
    key = key,
    title = title,
    isbn = isbn,
    authorName = authorName,
    coverId = coverId
  )