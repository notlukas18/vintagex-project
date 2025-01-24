package com.example.vintagehaven

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.vintagehaven.databinding.FragmentAccountBinding
import kotlinx.coroutines.launch


class AccountFragment : Fragment() {

    private var _binding: FragmentAccountBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadUserProfile()
    }

    private fun loadUserProfile() {
        val sharedPreferences = requireActivity().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("token", null)

        if (token != null) {
            lifecycleScope.launch {
                try {
                    val response = RetrofitInstance.api.getProfile("Bearer $token")
                    binding.userName.text = response.userName
                    binding.userPhone.text = response.userPhone
                } catch (e: Exception) {
                    e.printStackTrace()
                    Toast.makeText(requireContext(), "Failed to load profile", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            Toast.makeText(requireContext(), "User not logged in", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}