package com.example.myapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.slider.Slider
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    companion object {
        private var numWeight = 0
        private var numAge = 0
        private var numHeight: Float = 0.0f
    }

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val incrementWeight = findViewById<ImageButton>(R.id.Increment_weight)
        val decrementWeight = findViewById<ImageButton>(R.id.Decrement_weight)
        val incrementAge = findViewById<ImageButton>(R.id.Increment_age)
        val decrementAge = findViewById<ImageButton>(R.id.Decrement_age)
        val weight = findViewById<TextView>(R.id.Weight)
        val age = findViewById<TextView>(R.id.Age)
        val height = findViewById<TextView>(R.id.Height)
        val slider = findViewById<Slider>(R.id.continuousSlider)
        val bmi = findViewById<Button>(R.id.calculate)


        incrementWeight.setOnClickListener {
            numWeight++
            weight.text = numWeight.toString()
        }
        decrementWeight.setOnClickListener {
            if (numWeight > 0) {
                numWeight--
                weight.text = numWeight.toString()
            }
        }
        incrementAge.setOnClickListener {
            numAge++
            age.text = numAge.toString()
        }
        decrementAge.setOnClickListener {
            if (numAge > 0) {
                numAge--
                age.text = numAge.toString()
            }
        }

        slider.addOnSliderTouchListener(object : Slider.OnSliderTouchListener {
            override fun onStartTrackingTouch(slider: Slider) {
                // Responds to when slider's touch event is being started

            }

            override fun onStopTrackingTouch(slider: Slider) {
                // Responds to when slider's touch event is being stopped
            }
        })

        slider.addOnChangeListener { slider, value, fromUser ->
            // Responds to when slider's value is changed
            numHeight = value / 100
            height.text = value.toInt().toString()
        }
        bmi.setOnClickListener {
            if (numWeight != 0 && numAge != 0 && numHeight != 0.0f) {
                val s: Float = numHeight.pow(2)
                val bmiNumber = numWeight / s
                //   Toast.makeText(this, "$bmiNumber", Toast.LENGTH_LONG).show()
                //   Log.d("bmi", "$bmiNumber")
                val alertDialogBuilder = MaterialAlertDialogBuilder(this)
                alertDialogBuilder.setTitle("BMIgit").show()
                when {
                    bmiNumber < 18.5 -> {
                        alertDialogBuilder.setMessage("your Bmi :$bmiNumber\nyour Status :Under Weight")
                            .show()
                    }
                    bmiNumber in 18.5..24.9 -> {
                        alertDialogBuilder.setMessage("your Bmi :$bmiNumber\nyour Status :Normal")
                            .show()
                    }
                    bmiNumber in 25.0..29.9 -> {
                        alertDialogBuilder.setMessage("your Bmi :$bmiNumber\nyour Status :Over Weight")
                            .show()
                    }
                    bmiNumber in 30.0..34.9 -> {
                        alertDialogBuilder.setMessage("your Bmi :$bmiNumber\nyour Status :Obese")
                            .show()
                    }
                    bmiNumber > 35 -> {
                        alertDialogBuilder.setMessage("your Bmi :$bmiNumber\nyour Status :Extremely Obese")
                            .show()
                    }
                }

            } else {
                Toast.makeText(this, "Please fill All parameters", Toast.LENGTH_LONG).show()
            }
        }

    }
}