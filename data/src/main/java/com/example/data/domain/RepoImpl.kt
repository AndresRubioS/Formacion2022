package com.example.data.domain

import com.example.data.model.DataSource
import com.example.data.model.Pokemon
import com.example.data.model.PokemonEntity
import com.example.data.vo.Resource


class RepoImpl(private val dataSource: DataSource): Repo {
   suspend override fun  getGnomeList(): Resource<List<Pokemon>> {
       return dataSource.getPokemonByName()
    }

    override suspend fun getPokemonFavorites(): Resource<List<PokemonEntity>> {
        return dataSource.getPokemonFavorites()
    }

    override suspend fun insertPokemon(pokemon: PokemonEntity) {
        dataSource.insertPokemonIntoRoom(pokemon)

    }

}