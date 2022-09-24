package com.omaradev.movieapp.domain.model.all_movies

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class TypeConverterMovie {
    val gson : Gson = Gson()
    @TypeConverter
    fun stringToSomeObjectList(data: String?) :Movie? {
        if(data == null)return null
        val listType: Type = object :TypeToken<Movie?>() {}.type
        return gson.fromJson<Movie?>(data, listType)
    }

    @TypeConverter
    fun someObjectListToString(someobject: Movie?): String?
    {
        return gson.toJson(someobject)
    }
}
