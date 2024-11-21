package com.demo.marvel.characters.presentation.view

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.marvel.characters.presentation.adapter.CharactersAdapter
import com.demo.marvel.characters.presentation.viewModel.CharactersViewModel
import com.demo.marvel.databinding.ActivityCharactersBinding
import com.demo.marvel.shared.data.model.Character
import com.demo.marvel.shared.util.Logger.logError
import com.demo.marvel.shared.util.disableShimmer
import com.demo.marvel.shared.util.enableShimmer
import com.demo.marvel.shared.util.longToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharactersActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCharactersBinding
    private val charactersViewModel: CharactersViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharactersBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)
        initCharactersRv()
        observeCharactersList()
        observeLoadingProgress()
        charactersViewModel.getCharacters()
        observeFailure()
    }

    private fun initCharactersRv() {
        binding.rvCharacters.apply {
            layoutManager = LinearLayoutManager(this@CharactersActivity)
            adapter = CharactersAdapter(ArrayList(), ::onItemClick)
        }
    }

    private fun observeCharactersList() {
        lifecycleScope.launch {
            charactersViewModel.charactersList.collect { list ->
                if (!list.isNullOrEmpty()) {
                    getCharactersAdapter()?.updateData(list)
                }
            }

        }
    }

    private fun observeLoadingProgress() {
        lifecycleScope.launch {
            charactersViewModel.loading.collect { loading ->
                if (loading) binding.shimmerContainer.enableShimmer()
                else binding.shimmerContainer.disableShimmer()
            }
        }
    }

    private fun getCharactersAdapter() =
        binding.rvCharacters.adapter as? CharactersAdapter

    private fun onItemClick(character: Character) {
        //item click will start the second activity and pass the characterId
        //call characterDetails Api and show shimmerLayout till data come
        //show character Details with bottom sections(comics,events,...etc)
        //use clean architecture and MVVM Design architecture like first screen
        //when click the image of any item in bottom sections wen can use any ImageViewer library to show image such as: StfalconImageViewer library
        this.longToast("Character Details:\n Character Name: " + character.name + "\n Character Description: " + character.description)
    }

    private fun observeFailure() {
        lifecycleScope.launch {
            charactersViewModel.errorMessage.collect { message ->
                if (!message.isNullOrEmpty())
                    logError(message)
            }
        }
    }
}