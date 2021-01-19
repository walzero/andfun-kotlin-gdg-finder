package com.example.android.gdgfinder

import android.view.View
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView

fun View.setOnClickNavigation(navDirection: NavDirections) {
    setOnClickListener { it?.findNavController()?.navigate(navDirection) }
}

fun Fragment.navigateTo(navDirection: NavDirections) {
    findNavController().navigate(navDirection)
}

fun AppCompatActivity.bindActionBarToNavigation(
    navController: NavController,
    drawer: DrawerLayout,
    navView: NavigationView
) {
    NavigationUI.setupActionBarWithNavController(this, navController, drawer)
    NavigationUI.setupWithNavController(navView, navController)
}