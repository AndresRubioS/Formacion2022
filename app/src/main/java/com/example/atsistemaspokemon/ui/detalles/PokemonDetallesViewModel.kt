package com.example.atsistemaspokemon.ui.detalles

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.data.domain.Repo
import com.example.data.domain.WebService
import com.example.data.model.PokemonDetails
import com.example.data.model.PokemonEntity
import com.example.data.vo.Resource
import com.example.data.vo.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokemonDetallesViewModel(private val repo: Repo) : ViewModel() {



    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service: WebService = retrofit.create(WebService::class.java)
    val pokemonInfo = MutableLiveData<PokemonDetails>()
    fun getPokemonInfo(id: String){
        val call = service.getPokemonInfo(id)

        call.enqueue(object : Callback<PokemonDetails> {
            override fun onResponse(call: Call<PokemonDetails>, response: Response<PokemonDetails>) {
                response.body()?.let { pokemon ->
                    pokemonInfo.postValue(pokemon)
                }
            }

            override fun onFailure(call: Call<PokemonDetails>, t: Throwable) {
                call.cancel()
            }

        })
    }

    fun guardarPokemon(pokemon:PokemonEntity){
        viewModelScope.launch {
            repo.insertPokemon(pokemon)
        }

    }


}