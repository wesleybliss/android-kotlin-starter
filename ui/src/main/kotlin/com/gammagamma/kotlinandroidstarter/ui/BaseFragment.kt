package com.gammagamma.kotlinandroidstarter.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<T : ViewDataBinding>(
    @LayoutRes private val layoutId: Int
) : Fragment() {
    
    @Suppress("MemberVisibilityCanBePrivate")
    protected lateinit var binding: T
    
    @CallSuper
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        
        binding.lifecycleOwner = this
        
        return binding.root
        
    }
    
}
