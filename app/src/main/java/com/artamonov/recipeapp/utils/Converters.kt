package com.artamonov.recipeapp.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class Converters {

    @TypeConverter
    fun fromStringToList(value: String?) = Gson().fromJson(value, Array<String?>::class.java).toList()

    @TypeConverter
    fun fromListToString(list: List<String?>?) = Gson().toJson(list)

}