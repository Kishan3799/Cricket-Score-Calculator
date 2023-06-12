package com.kishan.cricketapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.kishan.cricketapp.data.local.MatchEntity
import com.kishan.cricketapp.databinding.SaveMatchItemLayoutBinding
import com.kishan.cricketapp.model.Match

class SaveMatchItemAdapter(val context: Context, var list: List<MatchEntity> ) : RecyclerView.Adapter<SaveMatchItemAdapter.SaveMatchItemViewHolder>() {

    fun updateData(newMatchList: List<MatchEntity>){
        list = newMatchList
        notifyDataSetChanged()
    }

    inner class SaveMatchItemViewHolder(val binding:SaveMatchItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(match:MatchEntity){
            binding.teamNameTv.text = match.matchTeamName
            binding.scoreTv.text = "${match.matchScore}/${match.matchWicket}"
            binding.OverTv.text = "Over : ${match.matchOvers}.${match.matchBall}"
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): SaveMatchItemViewHolder {
        val binding = SaveMatchItemLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return SaveMatchItemViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: SaveMatchItemViewHolder,
        position: Int,
    ) {
        val data = list[position]
        holder.bind(data)

    }

    override fun getItemCount(): Int {
        return list.size
    }


}