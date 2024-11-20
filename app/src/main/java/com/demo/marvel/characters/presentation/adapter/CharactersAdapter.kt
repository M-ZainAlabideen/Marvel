package com.demo.marvel.characters.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.demo.marvel.R
import com.demo.marvel.databinding.ItemCharacterBinding
import com.demo.marvel.shared.data.model.Character
import com.demo.marvel.shared.util.loadImage
import kotlin.properties.Delegates

class CharactersAdapter(
    list: List<Character> = emptyList(),
    private val onItemClick: (Character) -> Unit
) : RecyclerView.Adapter<CharactersAdapter.ViewHolder>() {

    private var list: List<Character> by Delegates.observable(list) { _, old, new ->
        DiffUtil.calculateDiff(
            object : DiffUtil.Callback() {
                override fun getOldListSize() = old.size

                override fun getNewListSize() = new.size

                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
                    old[oldItemPosition].id == new[newItemPosition].id

                override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
                    old[oldItemPosition] == new[newItemPosition]

            }
        ).also { result ->
            result.dispatchUpdatesTo(this@CharactersAdapter)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        return ViewHolder(
            ItemCharacterBinding.inflate(
                LayoutInflater.from(viewGroup.context),
                viewGroup,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position], onItemClick)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateData(newList: List<Character>) {
        list = newList
    }

    class ViewHolder(
        private val binding: ItemCharacterBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            character: Character,
            onItemClick: (Character) -> Unit
        ) {
            binding.apply {
                tvCharacterName.text = character.name
                val fullUrl = character.thumbnail.getFullUrl()
                val placeholder = R.mipmap.placeholder
                ivCharacterImage.loadImage(fullUrl, placeholder)
                clCharacterItem.setOnClickListener { onItemClick(character) }
            }
        }
    }

}
