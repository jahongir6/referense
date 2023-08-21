package com.example.referense.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.referense.R
import com.example.referense.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        binding.apply {
            btnEmployee.setOnClickListener {
                findNavController().navigate(R.id.employeeFragment)
            }
            btnCustomer.setOnClickListener {
                findNavController().navigate(R.id.customerFragment)
            }
            btnOrder.setOnClickListener {
                findNavController().navigate(R.id.orderFragment)
            }
        }
        return binding.root
    }
}