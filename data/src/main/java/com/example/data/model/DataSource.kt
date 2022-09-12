package com.example.data.model


import com.example.data.AppDatabase
import com.example.data.vo.Resource
import com.example.data.vo.RetrofitClient


class DataSource(private val appDatabase: AppDatabase) {


    suspend fun getPokemonByName(): Resource<List<Pokemon>>{
        return Resource.Success(RetrofitClient.webService.getPokemonByName().results)

    }

 fun insertPokemonIntoRoom(pokemon:PokemonEntity){
        appDatabase.pokemonDao().insertFavorite(pokemon )
    }

   fun getPokemonFavorites(): Resource<List<PokemonEntity>> {
        return Resource.Success(appDatabase.pokemonDao().getAllFavoritesPokemons())

    }


}


