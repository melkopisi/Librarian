package me.melkopisi.searchbooks.domain.models

data class BooksDomainModel(
  val numFound: Int,
  val start: Int,
  val numFoundExact: Boolean,
  val docs: List<Doc>
) {

  data class Doc(
    val title: String,
    val isbn: List<String>?,
    val authorName: List<String>
  )
}