package com.example.atsistemaspokemon.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.data.domain.Repo
import com.example.data.vo.Resource
import kotlinx.coroutines.Dispatchers

class FavoriteViewModel(private val repo:Repo) : ViewModel() {

    val fetchGnomesList = liveData(Dispatchers.IO){

        emit(Resource.Loading())
        try{
            emit(repo.getGnomeList())
        }catch (e: Exception){
            emit(Resource.Failure(e))

        }


    }
    fun getPokemonFavorites() = liveData(Dispatchers.IO){
        emit(Resource.Loading())
        try{
            emit(repo.getPokemonFavorites())
        }catch (e: Exception){
            emit(Resource.Failure(e))

        }

    }

}