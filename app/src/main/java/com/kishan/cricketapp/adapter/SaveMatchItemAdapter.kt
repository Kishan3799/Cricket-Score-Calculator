package com.kishan.cricketapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Lifecycle

import androidx.recyclerview.widget.RecyclerView
import com.kishan.cricketapp.data.local.AppDataBase

import com.kishan.cricketapp.data.local.MatchEntity
import com.kishan.cricketapp.databinding.SaveMatchItemLayoutBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class SaveMatchItemAdapter(val context: Context, var list: MutableList<MatchEntity> ) : RecyclerView.Adapter<SaveMatchItemAdapter.SaveMatchItemViewHolder>() {

    fun updateData(newMatchList: MutableList<MatchEntity>){
        list = newMatchList
        notifyDataSetChanged()
    }

    fun deleteData(position: Int){
        val match = list[position]
        list.removeAt(position)
        notifyItemRemoved(position)
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
        holder.binding.deleteScore.setOnClickListener {
            Toast.makeText(context,"Delete is clicked", Toast.LENGTH_SHORT).show()
            val dao = AppDataBase.getInstance(context).matchDao()
            GlobalScope.launch {
                dao.deleteMatchScore(match = MatchEntity(
                        matchId = data.matchId,
                        matchTeamName = data.matchTeamName,
                        matchScore = data.matchScore,
                        matchWicket = data.matchWicket,
                        matchOvers = data.matchOvers,
                        matchBall = data.matchBall
                    )
                )
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }


}