package me.melkopisi.data.local.entities.mappers

import me.melkopisi.data.local.entities.BooksEntity
import me.melkopisi.data.remote.models.BooksResponse

/*
 * Authored by Kopisi on 10 Sep, 2022.
 * Contact Me : m.elkopisi@gmail.com
 */

fun BooksResponse.Doc.toEntity(): BooksEntity =
  BooksEntity(
    title = title,
    isbn = isbn,
    authorName = authorName
  )