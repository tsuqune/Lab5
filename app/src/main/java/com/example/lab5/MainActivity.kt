package com.example.lab5

import android.os.Bundle
import android.widget.CheckBox
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var dollar: CheckBox
    private lateinit var euro: CheckBox
    private lateinit var funt: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        dollar = findViewById(R.id.dollar)
        euro = findViewById(R.id.euro)
        funt = findViewById(R.id.funt)
        setCheckBoxListener(dollar)
        setCheckBoxListener(euro)
        setCheckBoxListener(funt)
    }
    private fun setCheckBoxListener(checkBox: CheckBox) {
        checkBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // Сбрасываем другие чекбоксы
                if (checkBox != dollar) dollar.isChecked = false
                if (checkBox != euro) euro.isChecked = false
                if (checkBox != funt) funt.isChecked = false
            }
        }
    }
}