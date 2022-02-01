package com.example.dictionaryapp.feature_dictionary.presentation

import androidx.lifecycle.ViewModel
import com.example.dictionaryapp.feature_dictionary.domain.usecase.GetWordInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WordInfoViewModel @Inject constructor(
    private val getWordInfo: GetWordInfo
): ViewModel() {

    //private val _searchQuery = mutableStateOf<String>("")

}