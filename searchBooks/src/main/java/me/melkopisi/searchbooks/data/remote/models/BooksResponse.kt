package me.melkopisi.searchbooks.data.remote.models

import com.google.gson.annotations.SerializedName

data class BooksResponse(
  @SerializedName("numFound")
  val numFound: Int,
  @SerializedName("start")
  val start: Int,
  @SerializedName("numFoundExact")
  val numFoundExact: Boolean,
  @SerializedName("docs")
  val docs: List<Doc>
) {

  data class Doc(
    @SerializedName("key")
    val key: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("isbn")
    val isbn: List<String>?,
    @SerializedName("author_name")
    val authorName: List<String>?,
    @SerializedName("cover_i")
    val coverId: String?
  )
}