package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Chronometer

class MainActivity : AppCompatActivity() {
    //add global var
    lateinit var stopwatch: Chronometer
    var running = false
    var offset: Long = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        stopwatch = findViewById<Chronometer>(R.id.sw_timer)
        var pause_button = findViewById<Button>(R.id.bt_pause)
        var start_button = findViewById<Button>(R.id.bt_start)
        var stop_button = findViewById<Button>(R.id.bt_stop)


        start_button.setOnClickListener{
            if(!running){
                stopwatch.start()
                running = true
            }
        }

        pause_button.setOnClickListener{
            if (running){
                stopwatch.stop()
                running = false
            }
        }
        stop_button.setOnClickListener{
            offset = 0
        }
    }

}