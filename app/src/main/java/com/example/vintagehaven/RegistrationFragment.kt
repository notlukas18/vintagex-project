package com.example.vintagehaven

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.vintagehaven.databinding.FragmentRegistrationBinding
import kotlinx.coroutines.launch

class RegistrationFragment : Fragment() {
    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.registerButton.setOnClickListener {
            validateAndRegister()
        }
    }

    private fun validateAndRegister() {
        val name = binding.nameInput.text.toString().trim()
        val phoneNumber = binding.phoneNumberInput.text.toString().trim()
        val password = binding.passwordInput.text.toString().trim()

        // Basic validation
        if (name.isEmpty()) {
            binding.nameInput.error = "Name is required"
            return
        }

        if (phoneNumber.isEmpty()) {
            binding.phoneNumberInput.error = "Phone number is required"
            return
        }

        if (password.isEmpty()) {
            binding.passwordInput.error = "Password is required"
            return
        }

        // Proceed with registration
        registerUser(name, phoneNumber, password)
    }

    private fun registerUser(name: String, phoneNumber: String, password: String) {
        lifecycleScope.launch {
            try {
                val registerRequest = RegisterRequest(name, phoneNumber, password)
                val response = RetrofitInstance.api.register(registerRequest)
                if (response.success) {
                    Toast.makeText(requireContext(), "Registration successful", Toast.LENGTH_SHORT).show()
                    (activity as MainActivity).updateUserRegistrationStatus(true)
                    (activity as MainActivity).loadFragment(AccountFragment())
                } else {
                    Toast.makeText(requireContext(), response.message, Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(requireContext(), "Registration failed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}