package com.example.dictionaryapp.feature_dictionary.data.local

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dictionaryapp.feature_dictionary.data.local.entity.WordInfoEntity

interface WordInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWordInfos(Infos: List<WordInfoEntity>)

    @Query("DELETE FROM wordinfoentity WHERE word IN(:words)")
    suspend fun deleteWordInfos(words: List<String>)

    @Query("SELECT * FROM wordinfoentity WHERE word LIKE '%' || :word || '%'")
    suspend fun getWordInfos(word: String): List<WordInfoEntity>
}