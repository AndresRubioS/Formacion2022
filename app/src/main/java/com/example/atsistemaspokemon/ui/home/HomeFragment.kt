package com.example.atsistemaspokemon.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.atsistemaspokemon.R
import com.example.data.AppDatabase
import com.example.data.VMFactory

import com.example.data.domain.RepoImpl
import com.example.data.model.DataSource
import com.example.data.model.Pokemon
import com.example.data.vo.Resource
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), PokeListAdapter.OnPokemonClickListener {


    private val viewModel by viewModels<HomeViewModel> { VMFactory(RepoImpl(DataSource(AppDatabase.getDatabase(requireActivity().applicationContext)))) }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycleView()
        viewModel.fetchGnomesList.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer { result ->
                when (result) {
                    is Resource.Loading -> {
                        progresBar.visibility = View.VISIBLE

                    }
                    is Resource.Success -> {
                        progresBar.visibility = View.GONE
                        rvHome.adapter = PokeListAdapter(requireContext(),result.data,this)

                    }
                    is Resource.Failure -> {
                        progresBar.visibility = View.GONE
                        Toast.makeText(requireContext(), "error ${result.exception}", Toast.LENGTH_LONG).show()
                    }
                }
            })


    }

    private fun setupRecycleView(){

        rvHome.layoutManager = LinearLayoutManager(requireContext())
        rvHome.addItemDecoration(DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL))
    }

    override fun onPokemonClick(pokemon: Pokemon) {
        val bundle = Bundle()
        bundle.putParcelable("pokemon",pokemon)
        findNavController().navigate(R.id.action_navigation_home_to_pokemonDetalles,bundle)
    }







}