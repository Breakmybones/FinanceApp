package com.example.finance.presentation.navigation

import android.util.Log
import androidx.navigation.NavController
import com.example.finance.R

class Navigator {

    private var navController: NavController? = null

    fun initialize(navController: NavController) {
        this.navController = navController
    }

    fun attachNavController(navController: NavController, graph: Int) {
        navController.setGraph(graph)
        this.navController = navController
    }

    fun detachNavController(navController: NavController) {
        if (this.navController == navController) {
            this.navController = null
        }
    }

    fun openHome() {
        navController?.navigate(R.id.homeFragment)
    }

    fun openCash() {
        navController?.navigate(R.id.cashFragment)
        Log.e("navogator", navController.toString())
    }

    fun openInfo() {
        navController?.navigate(R.id.infoFragment)
    }

}