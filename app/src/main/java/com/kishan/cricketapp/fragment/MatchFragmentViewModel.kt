package com.kishan.cricketapp.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kishan.cricketapp.R
import com.kishan.cricketapp.data.local.AppDataBase
import com.kishan.cricketapp.data.local.MatchDao
import com.kishan.cricketapp.data.local.MatchEntity
import kotlinx.coroutines.launch
import java.util.Stack

class MatchFragmentViewModel : ViewModel() {




    var name: String? = null

    var score: Int = 0
    var wicket: Int = 0
    var over: Int = 0
    var ball: Int = 0

    var totalScore = 0
    var totalWicket = 0
    var totalOver = 0
    var totalBalls = 0

    private val scoreHistory : Stack<Int> = Stack()
    private val ballHistory : Stack<Int> = Stack()
    private val overHistory : Stack<Int> = Stack()
    private val wicketHistory : Stack<Int> = Stack()

    fun addOne() {
        scoreHistory.push(score)
        score +=1
    }

    fun addTwo() {
        scoreHistory.push(score)
        score +=2
    }

    fun addThree() {
        scoreHistory.push(score)
        score +=3
    }

    fun addFour() {
        scoreHistory.push(score)
        score +=4
    }
    fun addFive() {
        scoreHistory.push(score)
        score +=5
    }

    fun addSix() {
        scoreHistory.push(score)
        score +=6
    }

    fun addBall() {
        ballHistory.push(ball)
        ball +=1
        if (ball >= 6){
            ball = 0
            overHistory.push(over)
            over++
        }
    }

    fun updateWicket() {
        if(wicket != 10){
            wicketHistory.push(wicket)
            wicket +=1
            ballHistory.push(ball)
            ball +=1
            if (ball >=6 ){
                ball = 0
                over += 1
                overHistory.push(over)
            }
        }
    }


    fun resetAll() {
        name = "Enter Team Name"
        score = 0
        wicket = 0
        over = 0
        ball = 0

        totalScore = 0
        totalOver = 0
        totalWicket = 0
        totalBalls = 0

        scoreHistory.clear()
        wicketHistory.clear()
        overHistory.clear()
        ballHistory.clear()

    }

    fun undo(){
        if (!scoreHistory.isEmpty()) {
            score = scoreHistory.pop()
        }
        if (!ballHistory.isEmpty() ){
            ball = ballHistory.pop()
        }
        if (!overHistory.isEmpty() && ball == 0 ){
            over = overHistory.pop()
        }
        if (!wicketHistory.isEmpty()){
            wicket = wicketHistory.pop()
        }
    }

    fun showTeamName(context:Context, textView: TextView){
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_enter_team_name, null)
        val teamNameEt = dialogView.findViewById<EditText>(R.id.teamNameET)

        val dialog = AlertDialog.Builder(context)
            .setView(dialogView)
            .setTitle("Enter Team Name")
            .setPositiveButton("OK"){ dialog, which ->
                    name = teamNameEt.text.toString()
                    textView.text = name
            }
            .setNegativeButton("Cancel"){ dialog, which->
                dialog.dismiss()
            }
            .create()

        dialog.show()
    }

}