package com.example.atsistemaspokemon.ui.favorite

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.atsistemaspokemon.R
import com.example.atsistemaspokemon.databinding.FragmentFavoriteBinding


import com.example.atsistemaspokemon.ui.home.HomeFragment
import com.example.data.AppDatabase
import com.example.data.VMFactory
import com.example.data.domain.RepoImpl
import com.example.data.model.DataSource
import com.example.data.model.Pokemon
import com.example.data.vo.Resource
import kotlinx.android.synthetic.main.fragment_favorite.*


class FavoriteFragment() : Fragment() {

    private lateinit var favoriteViewModel: FavoriteViewModel
    private var _binding: FragmentFavoriteBinding? = null
    private val viewModel by activityViewModels<FavoriteViewModel>{ VMFactory(
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
        return inflater.inflate(R.layout.fragment_favorite, container, false)
        setupRecycleView()


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getPokemonFavorites().observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                }
                is Resource.Success -> {

                    Log.d("lista", "${result.data}")
                    val lista= result.data.map{
                        Pokemon(it.name,it.url)
                    }

                    rv_pokemons_favoritos.adapter = FavoritesAdapter(requireContext(),lista,this)

                    }
                is Resource.Failure -> {
                    //showToast("An error occurred ${result.exception}")
                }

                }


        })

    }
    private fun setupRecycleView(){

        rv_pokemons_favoritos.layoutManager = LinearLayoutManager(requireContext())
        rv_pokemons_favoritos.addItemDecoration(
            DividerItemDecoration(requireContext(),
                DividerItemDecoration.VERTICAL)
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }




}