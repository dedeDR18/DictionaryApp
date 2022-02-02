package com.example.dictionaryapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.dictionaryapp.databinding.ActivityMainBinding
import com.example.dictionaryapp.feature_dictionary.presentation.WordInfoViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    private lateinit var binding: ActivityMainBinding
    private val viewModel: WordInfoViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel.onSearch("bank")


        binding.isLoading = true
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.uiState.collect {
                    when(it) {
                        is WordInfoViewModel.UiState.Success -> {
                            val data = it.wordinfo
                            Log.d(TAG, "data = ${data.size}")
                            binding.isLoading = false
                            binding.data = data[0].word
                        }
                        is WordInfoViewModel.UiState.Loading -> {
                            Log.d(TAG, "show Loading..")
                            binding.isLoading = true
                        }
                        is WordInfoViewModel.UiState.Error -> {
                            val error = it.message
                            Log.d(TAG, "error : ${error}")
                            binding.isLoading = false
                        }
                    }
                }

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

    }
}