package me.melkopisi.searchbooks.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/*
 * Authored by Kopisi on 03 Sep, 2022.
 * Contact Me : m.elkopisi@gmail.com
 */

class IsbnConverters {

  @TypeConverter
  fun fromJson(json: String?): List<String>? = Gson().fromJson(json, object : TypeToken<List<String>?>() {}.type)

  @TypeConverter
  fun toJson(isbn: List<String>?): String = Gson().toJson(isbn)
}

