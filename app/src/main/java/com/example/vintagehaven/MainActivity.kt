package com.example.vintagehaven

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigation)

        // Set the default selected item for BottomNavigation
        bottomNavigationView.selectedItemId = R.id.navigation_home

        // Set listener for BottomNavigationView item selections
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    if (isUserRegistered()) {
                        loadFragment(HomeFragment()) // User is registered, navigate to Home
                    } else {
                        loadFragment(RegistrationFragment()) // User is not registered, navigate to Registration
                    }
                    true
                }
                R.id.navigation_cart -> {
                    if (isUserRegistered()) {
                        loadFragment(CartFragment())
                    } else {
                        loadFragment(RegistrationFragment()) // Redirect to Registration if not logged in
                    }
                    true
                }
                R.id.navigation_account -> {
                    if (isUserRegistered()) {
                        loadFragment(AccountFragment())
                    } else {
                        loadFragment(RegistrationFragment()) // Redirect to Registration if not logged in
                    }
                    true
                }
                else -> false
            }
        }

        // Check if the user is registered and load the appropriate fragment
        if (savedInstanceState == null) {
            checkUserRegistration()
        }
    }

    fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    // Function to check if the user is registered
    private fun checkUserRegistration() {
        val isRegistered = sharedPreferences.getBoolean("isRegistered", false)

        // Load the correct fragment based on registration status
        if (!isRegistered) {
            loadFragment(RegistrationFragment()) // If not registered, show RegistrationFragment
        } else {
            loadFragment(HomeFragment()) // If registered, show HomeFragment
        }
    }

    // Function to check user registration status
    private fun isUserRegistered(): Boolean {
        return sharedPreferences.getBoolean("isRegistered", false)
    }

    // Function to update the user registration status
    fun updateUserRegistrationStatus(isRegistered: Boolean) {
        with(sharedPreferences.edit()) {
            putBoolean("isRegistered", isRegistered)
            apply() // Apply changes asynchronously
        }

        // Reload the appropriate fragment based on updated registration status
        checkUserRegistration()
    }
}
