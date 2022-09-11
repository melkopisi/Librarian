package me.melkopisi.core.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BooksUiModel(
  val numFound: Int,
  val start: Int,
  val numFoundExact: Boolean,
  val docs: List<Doc>
) : Parcelable {

  @Parcelize
  data class Doc(
    val key: String,
    val title: String,
    val isbn: List<String>?,
    val authorName: List<String>?,
    val coverId: String?
  ) : Parcelable
}