package me.melkopisi.core.exceptions

import java.io.IOException

/*
 * Authored by Kopisi on 10 Sep, 2022.
 * Contact Me : m.elkopisi@gmail.com
 */
sealed class LibrarianException(msg: String) : IOException(msg) {
  class NetworkNotAvailable : LibrarianException("Network is not available.")
  class DataRetrievingFail : LibrarianException("Error getting data from server.")
  class NoData : LibrarianException("No data.")
  class NoLocalData : LibrarianException("No local data.")
}
