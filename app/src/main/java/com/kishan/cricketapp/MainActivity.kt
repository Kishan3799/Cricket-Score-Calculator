package com.kishan.cricketapp

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.kishan.cricketapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

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
                Toast.makeText(this, "All Out!" , Toast.LENGTH_SHORT).show()
            }
            Toast.makeText(this, "It's a Out!" , Toast.LENGTH_SHORT).show()
        }

        binding.btnReset.setOnClickListener {
            buttonHover(binding.btnReset)
            viewModel.resetAll()
            updateScore()
            updateBall()
        }

        binding.btnWide1.setOnClickListener {
            buttonHover(binding.btnWide1)
            viewModel.addOne()
            updateScore()
            Toast.makeText(this, "It's a Wide" , Toast.LENGTH_SHORT).show()
        }

        binding.btnNoBall1.setOnClickListener {
            buttonHover(binding.btnNoBall1)
            viewModel.addOne()
            updateScore()
            Toast.makeText(this, "No Ball" , Toast.LENGTH_SHORT).show()
        }
        binding.btnNoBall2.setOnClickListener {
            buttonHover(binding.btnNoBall2)
            viewModel.addTwo()
            updateScore()
            Toast.makeText(this, "No Ball" , Toast.LENGTH_SHORT).show()
        }
        binding.btnNoBall4.setOnClickListener {
            buttonHover(binding.btnNoBall4)
            viewModel.addFour()
            updateScore()
            Toast.makeText(this, "No Ball" , Toast.LENGTH_SHORT).show()
        }
        binding.btnNoBall6.setOnClickListener {
            buttonHover(binding.btnNoBall6)
            viewModel.addSix()
            updateScore()
            Toast.makeText(this, "No Ball" , Toast.LENGTH_SHORT).show()
        }

        binding.btnUndo.setOnClickListener {
            viewModel.undo()
            updateScore()
            updateBall()
        }

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