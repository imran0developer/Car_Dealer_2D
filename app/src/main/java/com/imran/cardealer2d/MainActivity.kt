package com.imran.cardealer2d

import android.os.Bundle
import android.os.Handler
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var backgroundImageView: ImageView
    private lateinit var vehicleImageView: ImageView
    private lateinit var leftButton: Button
    private lateinit var rightButton: Button

    private var handler = Handler()
    private var isLeftButtonPressed = false
    private var isRightButtonPressed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        backgroundImageView = findViewById(R.id.backgroundImageView)
        vehicleImageView = findViewById(R.id.vehicleImageView)
        leftButton = findViewById(R.id.leftButton)
        rightButton = findViewById(R.id.rightButton)

        leftButton.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    isLeftButtonPressed = true
                    handler.postDelayed(leftRunnable, 0) // Adjust the delay as needed
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    isLeftButtonPressed = false
                    handler.removeCallbacks(leftRunnable)
                }
            }
            true
        }

        rightButton.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    isRightButtonPressed = true
                    handler.postDelayed(rightRunnable, 0) // Adjust the delay as needed
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    isRightButtonPressed = false
                    handler.removeCallbacks(rightRunnable)
                }
            }
            true
        }
    }

    private val leftRunnable = object : Runnable {
        override fun run() {
            if (isLeftButtonPressed) {
                moveVehicleLeft()
                handler.postDelayed(this, 100) // Adjust the delay as needed
            }
        }
    }

    private val rightRunnable = object : Runnable {
        override fun run() {
            if (isRightButtonPressed) {
                moveVehicleRight()
                handler.postDelayed(this, 100) // Adjust the delay as needed
            }
        }
    }

    private fun moveVehicleLeft() {
        val newX = vehicleImageView.x - 10 // Adjust the value as needed
        vehicleImageView.x = newX
        val newBgX = backgroundImageView.x - 10 // Adjust the value as needed
        backgroundImageView.x = newBgX
    }

    private fun moveVehicleRight() {
        val newX = vehicleImageView.x + 10 // Adjust the value as needed
        vehicleImageView.x = newX
        val newBgX = backgroundImageView.x + 10 // Adjust the value as needed
        backgroundImageView.x = newBgX
    }
}

