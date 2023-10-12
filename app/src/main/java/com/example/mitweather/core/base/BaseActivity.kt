package com.example.mitweather.core.base

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import com.example.mitweather.core.base.BaseActivityNotRequireViewModel
import com.example.mitweather.core.base.BaseViewModel

abstract class BaseActivity<BD : ViewDataBinding, VM : BaseViewModel> :
    BaseActivityNotRequireViewModel<BD>() {

    private lateinit var viewModel: VM

    abstract fun getVM(): VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getVM()
    }
}