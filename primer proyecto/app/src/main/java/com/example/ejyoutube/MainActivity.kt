package com.example.ejyoutube

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        variables()
    }

    private fun variables(){
        var variable="Pablo"
        println(":)")

    }
}