package com.example.guessgameapp

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import com.example.guessgameapp.databinding.ActivityMainBinding
import com.example.guessgameapp.databinding.FragmentGameBinding


class GameFragment : Fragment() {
    private lateinit var binding: FragmentGameBinding
    private var score: Int = 0
    var word = ""
    private lateinit var wordList: MutableList<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val countDownTimer = object : CountDownTimer(60000, 1000) {
            // override object functions here, do it quicker by setting cursor on object, then type alt + enter ; implement members
            override fun onTick(millisUntilFinished: Long) {
                binding.tvCountdown.text =  (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                gameFinished()
            }
        }
        score = 0
        countDownTimer.start()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false)
        binding.apply {
            correctButton.setOnClickListener { correctAnswer() }
            skipButton.setOnClickListener { skipAnswer() }
        }

        resetList()
        nextWord()
        updateWordText()

        return binding.root
    }

    private fun correctAnswer() {
        score++
        nextWord()
    }

    private fun skipAnswer() {
        score--
        nextWord()
    }

    private fun resetList() {
        wordList = mutableListOf(
            "queen",
            "hospital",
            "basketball",
            "cat",
            "change",
            "snail",
            "soup",
            "calendar",
            "sad",
            "desk",
            "guitar",
            "home",
            "railway",
            "zebra",
            "jelly",
            "car",
            "crow",
            "trade",
            "bag",
            "roll",
            "bubble"
        )
        wordList.shuffle()

    }

    private fun nextWord() {
        if (wordList.isEmpty()) {
            gameFinished()
        } else {
            word = wordList.removeAt(0)
        }
        updateWordText()
        updateScoreText()
    }

    private fun gameFinished() {

        val args : Bundle =  Bundle()
        args.putString("END_SCORE", score.toString())
        view?.post{
            findNavController().navigate(R.id.action_gameFragment_to_scoreFragment, args)
        }
        fragmentManager?.fragments?.remove(this)
    }

    private fun updateScoreText() {
        binding.tvScore.text = score.toString()
    }
    private fun updateWordText(){
        binding.tvWord.text = word.toString()
    }

}