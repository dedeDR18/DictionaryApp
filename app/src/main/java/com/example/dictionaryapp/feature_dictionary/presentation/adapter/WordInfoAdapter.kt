package com.example.dictionaryapp.feature_dictionary.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionaryapp.R
import com.example.dictionaryapp.databinding.WordInfoListItemBinding
import com.example.dictionaryapp.feature_dictionary.domain.model.WordInfo
import java.time.temporal.TemporalAccessor

class WordInfoAdapter : RecyclerView.Adapter<WordInfoAdapter.WordInfoViewHolder>(){

    private val listData = ArrayList<WordInfo>()

    fun setData(data: List<WordInfo>?) {
        if (data!!.isNotEmpty()) {
            listData.clear()
            listData.addAll(data)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = WordInfoViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.word_info_list_item, parent, false)
    )

    override fun onBindViewHolder(holder: WordInfoAdapter.WordInfoViewHolder, position: Int) {
        val currentData = listData[position]
        holder.bind(currentData)
    }

    override fun getItemCount() = listData.size

    inner class WordInfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val binding =  WordInfoListItemBinding.bind(itemView)
        fun bind(data: WordInfo){
            with(binding){
                word = data.word
                phonetic = data.phonetic
                origin = data.origin

                val txt = data.meanings.map { meaning ->
                   val definition = meaning.definitions.mapIndexed { index, definition ->
                       val no = index+1
                       "$no. ".plus(definition.definition.plus("\n").plus("example : ").plus(definition.example).plus("\n"))
                   }.joinToString("\n")
//                val definition = meaning.definitions.joinToString(prefix = "- ",separator = "- ") { definition ->
//                    Log.d("MainActivity", "cek")
//                    definition.definition.plus("\n").plus("example : ").plus(definition.example).plus("\n")
//                }
                "${meaning.partOfSpeech} \n${definition}"
            }.joinToString("\n")

                meaning = txt
            }
        }

    }
}