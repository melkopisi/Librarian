package me.melkopisi.searchbooks.presentation.models

data class BooksUiModel(
  val numFound: Int,
  val start: Int,
  val numFoundExact: Boolean,
  val docs: List<Doc>
) {

  data class Doc(
    val key: String,
    val title: String,
    val isbn: List<String>?,
    val authorName: List<String>?,
    val coverId: String?
  )
}