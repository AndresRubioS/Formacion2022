package com.example.data.domain



import com.example.data.model.pokemonList
import com.example.data.model.PokemonDetails
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface WebService {
    @GET("pokemon/{id}")
    fun getPokemonInfo(@Path("id") id: String): Call<PokemonDetails>


    @GET("pokemon")
    suspend fun getPokemonByName() : pokemonList

}