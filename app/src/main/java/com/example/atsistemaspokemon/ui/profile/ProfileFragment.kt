package com.example.atsistemaspokemon.ui.profile

import android.content.Context
import android.os.Bundle
import android.os.UserManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.atsistemaspokemon.R
import com.example.atsistemaspokemon.databinding.FragmentFavoriteBinding
import com.example.atsistemaspokemon.databinding.FragmentProfileBinding
import com.example.atsistemaspokemon.ui.favorite.FavoriteViewModel
import com.example.atsistemaspokemon.ui.favorite.FavoritesAdapter
import com.example.atsistemaspokemon.ui.home.PokeListAdapter
import com.example.data.AppDatabase
import com.example.data.VMFactory
import com.example.data.domain.RepoImpl
import com.example.data.model.DataSource
import com.example.data.model.Pokemon
import com.example.data.model.PokemonEntity
import com.example.data.vo.Resource
import kotlinx.android.synthetic.main.fragment_detalles.*
import kotlinx.android.synthetic.main.fragment_favorite.*
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment() : Fragment() {

    //Intente crearlo con DataStore pero por una complicacion he usado SharedPreference

    private lateinit var favoriteViewModel: ProfileViewModel
    private var _binding: FragmentFavoriteBinding? = null
    private val viewModel by activityViewModels<ProfileViewModel>{ VMFactory(
        RepoImpl(
            DataSource(AppDatabase.getDatabase(requireActivity().applicationContext))
        )
    )
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)





    }
    private fun loadData() {

        val nameProfile = activity?.getSharedPreferences("preferences",Context.MODE_PRIVATE)
        val savedString = nameProfile?.getString("STRING_KEY",null)

        tv_name_profile.text = savedString


    }

    private fun saveData() {
        val insertedText = editTextTextPersonName.text.toString()
        tv_name_profile.text = insertedText

        val nameProfile = activity?.getSharedPreferences("preferences",Context.MODE_PRIVATE)
        val editor = nameProfile?.edit()
        editor?.apply {
            putString("STRING_KEY",insertedText)

        }?.apply()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()


        btn_save_profile.setOnClickListener {
            val insertedText = editTextTextPersonName.text.toString()
            tv_name_profile.text = insertedText


            saveData()

        }






    }


}