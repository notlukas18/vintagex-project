package com.example.vintagehaven

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.vintagehaven.databinding.FragmentLoginBinding
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginButton.setOnClickListener {
            val email = binding.emailInput.text.toString().trim()
            val password = binding.passwordInput.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireContext(), "Please enter both email and password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            loginUser(email, password)
        }
    }

    private fun loginUser(email: String, password: String) {
        lifecycleScope.launch {
            try {
                val loginRequest = LoginRequest(email, password)
                val response = RetrofitInstance.api.login(loginRequest)
                if (response.success) {
                    val sharedPreferences = requireActivity().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
                    sharedPreferences.edit().putBoolean("isRegistered", true).apply()
                    (activity as? MainActivity)?.loadFragment(HomeFragment())
                    Toast.makeText(requireContext(), "Login successful", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), response.message, Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(requireContext(), "Login failed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}