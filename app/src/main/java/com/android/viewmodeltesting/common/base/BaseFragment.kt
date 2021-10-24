package com.android.viewmodeltesting.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.android.viewmodeltesting.R
import com.android.viewmodeltesting.databinding.FragmentBaseBinding

abstract class BaseFragment : Fragment() {

    private lateinit var binding: FragmentBaseBinding
    private var rootView: View? = null

    abstract fun getLayoutId(): Int
    abstract fun bindScreen()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_base,
            container,
            false
        )
        rootView = binding.root
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindScreen()
    }

    protected fun <T : ViewDataBinding> setBinding(): T {
        val b: T = DataBindingUtil.inflate(layoutInflater, getLayoutId(), binding.root, true)
        b.lifecycleOwner = this
        return b
    }
}