package me.melkopisi.core.extensions

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import me.melkopisi.core.exceptions.LibrarianException
import retrofit2.Response

/*
 * Authored by Kopisi on 10 Sep, 2022.
 * Contact Me : m.elkopisi@gmail.com
 */
fun <T> Flow<Response<T>>.parseResponse(): Flow<T> {
  return map { response ->
    if (response.isSuccessful) {
      if (response.body() != null) response.body()!! else throw LibrarianException.NoData()
    } else {
      throw LibrarianException.DataRetrievingFail()
    }
  }
}