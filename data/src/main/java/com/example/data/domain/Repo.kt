package com.example.data.domain



import com.bumptech.glide.load.engine.Resource
import com.example.data.model.Pokemon
import com.example.data.model.PokemonEntity

interface Repo {
   suspend fun getGnomeList(): com.example.data.vo.Resource<List<Pokemon>>
   suspend fun getPokemonFavorites(): com.example.data.vo.Resource<List<PokemonEntity>>
   suspend fun insertPokemon(pokemon:PokemonEntity)


}