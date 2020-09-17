package com.example.primeraclase

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.primeraclase.R.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_principal)
    }

    override fun onRestart() {
        super.onRestart()
    }
}