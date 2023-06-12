package com.kishan.cricketapp

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kishan.cricketapp.adapter.ViewPager2Adapter
import com.kishan.cricketapp.databinding.ActivityMainBinding
import com.kishan.cricketapp.fragment.MatchFragment
import com.kishan.cricketapp.fragment.SaveMatchFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragmentArrayList = ArrayList<Fragment>()

        fragmentArrayList.add(MatchFragment())
        fragmentArrayList.add(SaveMatchFragment())

        val adapter = ViewPager2Adapter(this, supportFragmentManager, fragmentArrayList)

        binding.viewpager2.adapter = adapter

        binding.tabs.setupWithViewPager(binding.viewpager2)


    }






}