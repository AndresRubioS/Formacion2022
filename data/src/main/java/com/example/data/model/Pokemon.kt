package com.example.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Pokemon(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String





) : Parcelable


data class pokemonList(
    @SerializedName("count") val count: Int,
    @SerializedName("next") val next: String,
    @SerializedName("previous") val previous: String,
    @SerializedName("results") val results: List<Pokemon>

)
@Entity
data class PokemonEntity(
    @PrimaryKey
    val name: String,

    @Expose @ColumnInfo(name ="url") val url: String,

    )