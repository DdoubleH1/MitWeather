package com.example.mitweather.ui.splash

import androidx.fragment.app.viewModels
import com.example.mitweather.R
import com.example.mitweather.core.base.BaseFragment
import com.example.mitweather.databinding.FragmentSplashBinding
import com.example.mitweather.navigation.AppNavigation
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding, SplashViewModel>(R.layout.fragment_splash) {

    @Inject
    lateinit var appNavigation: AppNavigation
    private val viewModel: SplashViewModel by viewModels()

    override fun getVM() = viewModel

    override fun bindingAction() {
        super.bindingAction()
        viewModel.actionSPlash.observe(viewLifecycleOwner){
            appNavigation.openSplashToHome()
        }
    }

}