package com.example.atsistemaspokemon.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.viewbinding.ViewBinding

abstract class BaseFragment <T:ViewBinding, VM: BaseViewModel> : Fragment() {

    private var _binding  : T? = null

    protected val binding
        get() =  _binding!!
    protected abstract val vm:VM




    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = provideBinding(inflater,container)
        return binding.root
    }
    abstract fun provideBinding(inflater: LayoutInflater, container: ViewGroup?):T
    @CallSuper
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    protected fun <LD>observeData(liveData: LiveData<LD>, action:(LD)->Unit){
        liveData.observe(viewLifecycleOwner){
            action.invoke(it)
        }
    }
}
