package com.example.mitweather.core.base

import android.os.Bundle
import android.text.TextUtils
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding

abstract class BaseFragment<BD : ViewDataBinding, VM : BaseViewModel>(@LayoutRes id: Int) :
    BaseFragmentNotRequireViewModel<BD>(id) {

    private lateinit var viewModel: VM

    abstract fun getVM(): VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getVM()
    }

    override fun initView(savedInstanceState: Bundle?) {

    }

}