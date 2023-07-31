package com.example.bookscan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.bookscan.databinding.FragmentScanRecordBinding
import com.example.bookscan.db.SmLog
import kotlinx.coroutines.launch

/**
 * Created by Android Studio.
 * Author: yx
 * Date: 2023/7/7 20:34
 */
class ScanRecordFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentScanRecordBinding.inflate(inflater, container, false)
        val smLogAdapter = SmLogAdapter(requireContext())
        binding.recyclerView.adapter = smLogAdapter
        lifecycleScope.launch {
            val app = requireActivity().application as App
            smLogAdapter.logList = app.db.getSmLogDao().getAllSmLog() as ArrayList<SmLog>
        }
        return binding.root
    }
}