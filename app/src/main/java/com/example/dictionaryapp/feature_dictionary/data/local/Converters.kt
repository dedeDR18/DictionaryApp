package com.example.dictionaryapp.feature_dictionary.data.local

import androidx.room.TypeConverter
import com.example.dictionaryapp.feature_dictionary.data.util.JsonParser
import com.example.dictionaryapp.feature_dictionary.domain.model.Meaning
import com.google.gson.reflect.TypeToken

class Converters(
    private val jsonParser: JsonParser
) {
    @TypeConverter
    fun fromMeaningJson(json:String): List<Meaning>{
        return jsonParser.fromJson<ArrayList<Meaning>>(
            json,
            object : TypeToken<ArrayList<Meaning>>(){}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun toMeaningJson(meanings: List<Meaning>): String {
        return jsonParser.toJson(
            meanings,
            object : TypeToken<ArrayList<Meaning>>(){}.type
        ) ?:"[]"
    }
}