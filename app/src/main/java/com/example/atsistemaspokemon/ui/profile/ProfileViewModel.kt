package com.example.atsistemaspokemon.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.atsistemaspokemon.common.base.BaseViewModel
import com.example.data.domain.ProfileRepository
import kotlinx.coroutines.runBlocking

class ProfileViewModel(private val profileRepository: ProfileRepository) : BaseViewModel() {

    val obsSurname: LiveData<String> = profileRepository.getUserSurname().asLiveData()


    fun onActionNameWritten(name: String) {
        runBlocking {
            profileRepository.saveUserName(name)
        }

    }

    fun onActionSurnameWritten(surname: String) {
        runBlocking {
            profileRepository.saveUserSurname(surname)
        }
    }

}