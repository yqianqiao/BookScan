package com.example.bookscan

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bookscan.databinding.FragmentHomeBinding

/**
 * Created by Android Studio.
 * Author: yx
 * Date: 2023/7/7 20:33
 */
class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.ivUpdata.setOnClickListener {
            startActivity(Intent(requireContext(), CoffersInfoActivity::class.java))
        }
        binding.ivMove.setOnClickListener {
            startActivity(Intent(requireContext(), CoffersInfoActivity::class.java))
        }
        return binding.root
    }
}