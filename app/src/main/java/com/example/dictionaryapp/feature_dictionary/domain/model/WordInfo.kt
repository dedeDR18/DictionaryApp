package com.example.dictionaryapp.feature_dictionary.domain.model

import com.example.dictionaryapp.feature_dictionary.data.remote.dto.MeaningDto
import com.example.dictionaryapp.feature_dictionary.data.remote.dto.PhoneticDto

data class WordInfo(
    val meanings: List<Meaning>,
    val origin: String,
    val phonetic: String,
    val word: String
)
