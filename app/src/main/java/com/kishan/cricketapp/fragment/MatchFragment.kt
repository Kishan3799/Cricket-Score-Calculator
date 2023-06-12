package com.kishan.cricketapp.fragment

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.kishan.cricketapp.data.local.AppDataBase
import com.kishan.cricketapp.data.local.MatchEntity
import com.kishan.cricketapp.databinding.FragmentMatchBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MatchFragment: Fragment() {
    private lateinit var binding: FragmentMatchBinding
    private lateinit var viewModel: MatchFragmentViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentMatchBinding.inflate(layoutInflater)

        viewModel = ViewModelProvider(this)[MatchFragmentViewModel::class.java]

        binding.teamNameTv.setOnClickListener {
            viewModel.showTeamName(requireContext(), binding.teamNameTv)
        }

        binding.scoreTv.text = "${viewModel.score}/${viewModel.wicket}"
        binding.overTv.text = "Over : (${viewModel.over}.${viewModel.ball})"

        binding.btn1.setOnClickListener {
            buttonHover(binding.btn1)
            viewModel.addOne()
            viewModel.addBall()
            updateScore()
            updateBall()

        }

        binding.btn2.setOnClickListener {
            buttonHover(binding.btn2)
            viewModel.addTwo()
            viewModel.addBall()
            updateScore()
            updateBall()
        }

        binding.btn3.setOnClickListener {
            buttonHover(binding.btn3)
            viewModel.addThree()
            viewModel.addBall()
            updateScore()
            updateBall()
        }

        binding.btn4.setOnClickListener {
            buttonHover(binding.btn4)
            viewModel.addFour()
            viewModel.addBall()
            updateScore()
            updateBall()
        }

        binding.btn5.setOnClickListener {
            buttonHover(binding.btn5)
            viewModel.addFive()
            viewModel.addBall()
            updateScore()
            updateBall()
        }

        binding.btn6.setOnClickListener {
            buttonHover(binding.btn6)
            viewModel.addSix()
            viewModel.addBall()
            updateScore()
            updateBall()
        }

        binding.btnDot.setOnClickListener {
            buttonHover(binding.btnDot)
            viewModel.addBall()
            updateBall()
        }

        binding.btnOut.setOnClickListener {
            buttonHover(binding.btnOut)
            viewModel.updateWicket()
            updateScore()
            updateBall()
            if(viewModel.wicket == 10){
                Toast.makeText(requireContext(), "All Out!" , Toast.LENGTH_SHORT).show()
            }
            Toast.makeText(requireContext(), "It's a Out!" , Toast.LENGTH_SHORT).show()
        }

        binding.btnReset.setOnClickListener {
            buttonHover(binding.btnReset)
            viewModel.resetAll()
            updateScore()
            updateBall()
            binding.teamNameTv.text = viewModel.name
        }

        binding.btnWide1.setOnClickListener {
            buttonHover(binding.btnWide1)
            viewModel.addOne()
            updateScore()
            Toast.makeText(requireContext(), "It's a Wide" , Toast.LENGTH_SHORT).show()
        }

        binding.btnNoBall1.setOnClickListener {
            buttonHover(binding.btnNoBall1)
            viewModel.addOne()
            updateScore()
            Toast.makeText(requireContext(), "No Ball" , Toast.LENGTH_SHORT).show()
        }
        binding.btnNoBall2.setOnClickListener {
            buttonHover(binding.btnNoBall2)
            viewModel.addTwo()
            updateScore()
            Toast.makeText(requireContext(), "No Ball" , Toast.LENGTH_SHORT).show()
        }
        binding.btnNoBall4.setOnClickListener {
            buttonHover(binding.btnNoBall4)
            viewModel.addFour()
            updateScore()
            Toast.makeText(requireContext(), "No Ball" , Toast.LENGTH_SHORT).show()
        }
        binding.btnNoBall6.setOnClickListener {
            buttonHover(binding.btnNoBall6)
            viewModel.addSix()
            updateScore()
            Toast.makeText(requireContext(), "No Ball" , Toast.LENGTH_SHORT).show()
        }

        binding.btnUndo.setOnClickListener {
            buttonHover(binding.btnUndo)
            viewModel.undo()
            updateScore()
            updateBall()
        }

        binding.saveData.setOnClickListener {
            buttonHover(binding.saveData)
           val dao = AppDataBase.getInstance(requireContext()).matchDao()
            lifecycleScope.launch {
                dao.saveMatchScore(
                    MatchEntity(
                        matchTeamName = viewModel.name,
                        matchScore = viewModel.totalScore,
                        matchWicket = viewModel.totalWicket,
                        matchBall = viewModel.totalBalls,
                        matchOvers = viewModel.totalOver
                    )
                )
            }
            Toast.makeText(requireContext(), "Match Saved successfully", Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }

    private fun buttonHover(btn: Button) {
        btn.alpha = 0f
        val animate = ObjectAnimator.ofFloat(btn, "alpha", 0f, 1f)
        animate.duration = 500
        animate.start()
    }

    private fun updateScore() {
        viewModel.totalScore = viewModel.score
        viewModel.totalWicket = viewModel.wicket
        binding.scoreTv.text = "${viewModel.totalScore}/${viewModel.totalWicket}"
    }

    private fun updateBall() {
        viewModel.totalBalls = viewModel.ball
        viewModel.totalOver = viewModel.over
        binding.overTv.text = "Over : (${viewModel.totalOver}.${viewModel.totalBalls})"
    }


}