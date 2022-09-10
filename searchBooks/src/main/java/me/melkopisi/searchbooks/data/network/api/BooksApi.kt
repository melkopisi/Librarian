package me.melkopisi.searchbooks.data.network.api

import me.melkopisi.searchbooks.data.remote.models.BooksResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/*
 * Authored by Kopisi on 10 Sep, 2022.
 * Contact Me : m.elkopisi@gmail.com
 */
interface BooksApi {
  companion object {
    const val SEARCH_PATH = "search.json"
  }

  @GET(SEARCH_PATH)
  suspend fun searchBooks(
    @Query("q") query: String,
    @Query("offset") offset: Int,
    @Query("limit") limit: Int
  ): Response<BooksResponse>
}