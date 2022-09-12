package com.example.atsistemaspokemon.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.atsistemaspokemon.R
import com.example.brastlewark.base.BaseViewHolder

import com.example.data.model.Pokemon
import kotlinx.android.synthetic.main.item_home.view.*


class PokeListAdapter(private val context: Context, private val pokemonList:List<Pokemon>, private val itemClickLister: HomeFragment): RecyclerView.Adapter<BaseViewHolder<*>>() {
    interface OnPokemonClickListener{
        fun onPokemonClick(pokemon: Pokemon)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return MainViewHolder(LayoutInflater.from(context).inflate(R.layout.item_home,parent,false))
    }


    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is MainViewHolder -> holder.bind(pokemonList[position],position)
        }
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    inner class MainViewHolder(itemView: View): BaseViewHolder<Pokemon>(itemView){
        override fun bind(item: Pokemon, position: Int){


            itemView.tvItemHomeName.text = "#${position + 1} - ${item.name}"


            itemView.setOnClickListener{itemClickLister.onPokemonClick(item)}

        }





    }
}

