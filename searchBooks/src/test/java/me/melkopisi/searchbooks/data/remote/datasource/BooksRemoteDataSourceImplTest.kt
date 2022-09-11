package me.melkopisi.searchbooks.data.remote.datasource

import app.cash.turbine.test
import com.google.common.truth.Truth
import com.google.gson.Gson
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import me.melkopisi.searchbooks.data.network.api.BooksApi
import me.melkopisi.searchbooks.data.utils.MockResponseFileReader
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/*
 * Authored by Kopisi on 11 Sep, 2022.
 * Contact Me : m.elkopisi@gmail.com
 */
internal class BooksRemoteDataSourceImplTest {

  private val server: MockWebServer = MockWebServer()
  private val MOCK_WEBSERVER_PORT = 8000
  lateinit var githubReposApi: BooksApi
  lateinit var remoteDataSource: BooksRemoteDataSource

  @Before
  fun setup() {
    server.start(MOCK_WEBSERVER_PORT)

    githubReposApi = Retrofit.Builder()
      .baseUrl(server.url("/"))
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .addConverterFactory(GsonConverterFactory.create(Gson()))
      .build()
      .create(BooksApi::class.java)

    remoteDataSource = BooksRemoteDataSourceImpl(githubReposApi)
  }

  @After
  fun shutdown() {
    server.shutdown()
  }

  @OptIn(ExperimentalCoroutinesApi::class)
  @Test
  fun `search books API parse success and key is not null`() = runTest {
    server.apply {
      enqueue(
        MockResponse().setBody(
          MockResponseFileReader(
            "SearchBooksSuccess.json"
          ).content
        )
      )
    }
    remoteDataSource.searchBooks("test", 1).test {
      val item = awaitItem()
      Truth.assertThat(item[0].key).isNotNull()
      awaitComplete()
    }
  }
}