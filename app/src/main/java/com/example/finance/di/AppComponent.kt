package com.example.finance.di

import android.content.Context
import com.example.finance.presentation.ui.*
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [CurrencyModule::class, NetworkModule::class, UserModule::class])
@Singleton
interface AppComponent {

    fun injectHomeFragment(homeFragment: HomeFragment)

    fun injectCurrencyFragment(infoFragment: InfoFragment)

    fun injectProfileFragment(profileFragment: ProfileFragment)

    fun injectCashFragment(cashFragment: CashFragment)

    fun injectLoginFragment(loginFragment: LoginFragment)

    fun injectTransfersFragment(transfersFragment: TransfersFragment)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }
}