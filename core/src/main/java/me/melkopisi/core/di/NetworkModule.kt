package me.melkopisi.core.di

import android.content.Context
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import me.melkopisi.core.exceptions.LibrarianException
import me.melkopisi.core.extensions.isInternetAvailable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/*
 * Authored by Kopisi on 10 Sep, 2022.
 * Contact Me : m.elkopisi@gmail.com
 */
@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

  @Singleton
  @Provides fun provideGson(): Gson = Gson()

  @Singleton
  @Provides fun provideRetrofit(
    client: OkHttpClient,
    gson: Gson,
  ): Retrofit = Retrofit.Builder()
    .baseUrl("https://openlibrary.org/")
    .addConverterFactory(GsonConverterFactory.create(gson))
    .client(client)
    .build()

  @Singleton
  @Provides fun providesOkHttpClient(
    networkInterceptor: Interceptor
  ): OkHttpClient = OkHttpClient.Builder()
    .connectTimeout(60, TimeUnit.SECONDS)
    .readTimeout(60, TimeUnit.SECONDS)
    .writeTimeout(60, TimeUnit.SECONDS)
    .addInterceptor(HttpLoggingInterceptor().apply {
      level = HttpLoggingInterceptor.Level.BODY
    })
    .addInterceptor(networkInterceptor)
    .build()

  @Singleton
  @Provides fun providesNetworkInterceptor(@ApplicationContext context: Context): Interceptor = Interceptor { chain ->
    val request = chain.request()
    if (context.isInternetAvailable().not()) throw LibrarianException.NetworkNotAvailable()
    chain.proceed(request)
  }
}