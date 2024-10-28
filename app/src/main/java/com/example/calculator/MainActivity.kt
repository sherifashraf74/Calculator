package com.example.calculator

import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    var lastNumber: Double = 0.0
    var currentOperation: Operations? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addCallBacks()
    }

    private fun addCallBacks() {
        binding.clear.setOnClickListener {
            clearInput()
        }
        binding.dua.setOnClickListener{
            duaPlay()
        }
        binding.backspace.setOnClickListener {
            val currentText = binding.result.text.toString()
            if (currentText.isNotEmpty()) {
                // Remove the last character
                binding.result.text = currentText.dropLast(1)
            }
        }
        binding.mod.setOnClickListener {
            prepareOperations(Operations.Modules)
        }
        binding.divide.setOnClickListener {
            prepareOperations(Operations.Division)
        }
        binding.multiply.setOnClickListener {
            prepareOperations(Operations.Multiply)
        }
        binding.plus.setOnClickListener {
            prepareOperations(Operations.Plus)
        }
        binding.minus.setOnClickListener {
            prepareOperations(Operations.Minus)
        }
        binding.equals.setOnClickListener {
            val result = calCurrentOperation()
            binding.result.text = result.toString()
        }
    }

    private fun calCurrentOperation(): Double {
        val secondNumber = binding.result.text.toString().toDouble()
        return when (currentOperation) {
            Operations.Minus -> lastNumber - secondNumber
            Operations.Plus -> lastNumber + secondNumber
            Operations.Multiply -> lastNumber * secondNumber
            Operations.Division -> lastNumber / secondNumber
            Operations.Modules -> lastNumber % secondNumber
            null -> 0.00
        }
    }

    private fun prepareOperations(operation: Operations) {
        lastNumber = binding.result.text.toString().toDouble()
        clearInput()
        currentOperation = operation
    }

    private fun clearInput() {
        binding.result.text = ""
    }

    fun onClickNumber(view: View) {
        val newDigit = (view as TextView).text.toString()
        val oldDigit = binding.result.text.toString()
        val newTextNum = oldDigit + newDigit
        binding.result.text = newTextNum
    }
    fun duaPlay(){
        var mMediaPlayer: MediaPlayer? = null
        val dua : ImageButton = binding.dua
        dua.setOnClickListener{
            if (mMediaPlayer == null) {
                mMediaPlayer = MediaPlayer.create(this, R.raw.pronunciationastghfirullah)
                mMediaPlayer!!.isLooping = false
                mMediaPlayer!!.start()
            } else mMediaPlayer!!.start()
        }

    }
}
