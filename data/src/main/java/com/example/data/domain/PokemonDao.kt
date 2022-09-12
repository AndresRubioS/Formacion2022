package com.example.data.domain

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.model.PokemonEntity
@Dao
interface PokemonDao {

    @Query("SELECT * FROM PokemonEntity")
     fun getAllFavoritesPokemons():List<PokemonEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
   fun insertFavorite(pokemon:PokemonEntity)
}