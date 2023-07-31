package com.example.bookscan

import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.bookscan.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val fragments = listOf(
        HomeFragment(),
        ScanRecordFragment(),
        MineFragment()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            val app = application as App
            val kuFangs = app.db.getKuFangDao().getAllKuFangEntity()
            Log.e("yx", "kuFangs: $kuFangs" );
        }

        binding.viewPager.adapter = object : FragmentStateAdapter(this){
            override fun getItemCount(): Int {
                return 3
            }

            override fun createFragment(position: Int): Fragment {
                return fragments[position]
            }
        }
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                binding.navView.menu.getItem(position).isChecked = true
            }
        })

        binding.navView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.navigation_home -> binding.viewPager.currentItem = 0
                R.id.navigation_scan_record -> binding.viewPager.currentItem = 1
                R.id.navigation_mine -> binding.viewPager.currentItem = 2
            }
            return@setOnItemSelectedListener true
        }
    }
}