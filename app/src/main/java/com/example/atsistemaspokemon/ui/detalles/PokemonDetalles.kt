package com.example.atsistemaspokemon.ui.detalles

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.atsistemaspokemon.R
import com.example.data.AppDatabase
import com.example.data.VMFactory
import com.example.data.domain.RepoImpl
import com.example.data.model.DataSource
import com.example.data.model.Pokemon
import com.example.data.model.PokemonEntity
import kotlinx.android.synthetic.main.fragment_detalles.*

class PokemonDetalles: Fragment() {
    private lateinit var pokemon1: Pokemon

    private val viewModelInfo by activityViewModels<PokemonDetallesViewModel>{ VMFactory(
        RepoImpl(
            DataSource(AppDatabase.getDatabase(requireActivity().applicationContext))
        )
    )
    }





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().let {
            pokemon1 = it.getParcelable("pokemon")!!
            Log.d("DETALLES", "$pokemon1")
            //  val nombre = repo.getPokemonInfo(gnome.name)


        }


    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detalles, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelInfo.getPokemonInfo(pokemon1.name)
        viewModelInfo.pokemonInfo.observe(viewLifecycleOwner, Observer { pokemon ->
            tv_detalle_name.text = pokemon.name

            tv_detalle_heigth.text = "Altura: ${pokemon.height}"
            tv_detalle_weight.text = "Peso: ${pokemon.weight}"
            Glide.with(this).load(pokemon.sprites.frontDefault).centerCrop().fitCenter().into(img_detalles)


            btn_guardar_fav.setOnClickListener {
                viewModelInfo.guardarPokemon(PokemonEntity(pokemon1.name,pokemon1.url))
                Toast.makeText(requireContext(),"Se guardo en favoritos", Toast.LENGTH_SHORT).show()
            }



        })




    }


}
