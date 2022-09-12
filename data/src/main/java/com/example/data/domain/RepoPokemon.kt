package com.example.data.domain

import com.example.data.model.PokemonDetails


interface RepoPokemon {

    suspend fun getPokemon(number: String): PokemonDetails
}