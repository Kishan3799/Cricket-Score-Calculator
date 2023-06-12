package com.kishan.cricketapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.kishan.cricketapp.adapter.SaveMatchItemAdapter
import com.kishan.cricketapp.data.local.AppDataBase
import com.kishan.cricketapp.data.local.MatchDao
import com.kishan.cricketapp.databinding.FragmentSaveMatchBinding
import kotlinx.coroutines.launch

class SaveMatchFragment : Fragment() {
    private lateinit var binding : FragmentSaveMatchBinding
    private lateinit var dao : MatchDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dao = AppDataBase.getInstance(requireContext()).matchDao()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentSaveMatchBinding.inflate(layoutInflater)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = SaveMatchItemAdapter(requireContext(), emptyList())



        lifecycleScope.launch {
            dao.getAllMatchScore().observe(viewLifecycleOwner , Observer {matchList->
                adapter.updateData(matchList)
            })

        }

        binding.recyclerView.adapter = adapter
        return binding.root
    }




}