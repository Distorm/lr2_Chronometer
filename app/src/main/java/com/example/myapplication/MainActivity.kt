package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.os.SystemClock
import android.widget.Button
import android.widget.Chronometer

class MainActivity : AppCompatActivity() {
    //add global var
    lateinit var stopwatch: Chronometer
    var running = false
    var offset: Long = 0

    val OFFSET_KEY = "offset"
    val RUNNING_KEY = "running"
    val BASE_KEY = "base"


    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        savedInstanceState.putInt("ansver", 42)
        savedInstanceState.putLong(OFFSET_KEY, offset)
        savedInstanceState.putBoolean(RUNNING_KEY, running)
        savedInstanceState.putLong(BASE_KEY, stopwatch.base)
        super.onSaveInstanceState(savedInstanceState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            var answer = savedInstanceState.getInt("answer")
        }


        stopwatch = findViewById<Chronometer>(R.id.sw_timer)

        if (savedInstanceState != null){
            offset =savedInstanceState.getLong(OFFSET_KEY)
            running = savedInstanceState.getBoolean(RUNNING_KEY)
            if (running){
                stopwatch.base = savedInstanceState.getLong(BASE_KEY)
                stopwatch.start()
            }
            else set_base_time()
        }

        var pause_button = findViewById<Button>(R.id.bt_pause)
        var start_button = findViewById<Button>(R.id.bt_start)
        var stop_button = findViewById<Button>(R.id.bt_stop)


        start_button.setOnClickListener{
            if(!running){
                set_base_time()
                stopwatch.start()
                running = true
            }
        }

        pause_button.setOnClickListener{
            if (running){
                save_offset()
                stopwatch.stop()
                running = false
            }
        }
        stop_button.setOnClickListener{
            set_base_time()
            offset = 0
        }



    }
    override fun onStop(){
        super.onStop()
        if (running){
            save_offset()
            stopwatch.stop()
        }
    }
    override fun onRestart(){
        super.onRestart()
        if (running){
            set_base_time()
            stopwatch.start()
            offset = 0
        }
    }

    //update time
    fun set_base_time(){
        stopwatch.base = SystemClock.elapsedRealtime()
    }
    //save offset
    fun save_offset(){
        offset = SystemClock.elapsedRealtime()
    }

}