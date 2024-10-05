package com.example.lab5

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var dollar: CheckBox
    private lateinit var euro: CheckBox
    private lateinit var funt: CheckBox
    private lateinit var input: EditText
    private lateinit var calculate: Button
    private lateinit var view: TextView

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
        input = findViewById(R.id.input)
        calculate = findViewById(R.id.Сalculates)
        //view = findViewById(R.id.textV)

        setCheckBoxListener(dollar)
        setCheckBoxListener(euro)
        setCheckBoxListener(funt)

        calculate.setOnClickListener {
            val inputString: String = input.text.toString()
            val sum: Double? = inputString.toDoubleOrNull()

            // Объявляем переменную resultMessage здесь
            val resultMessage: String = if (sum != null) {
                when {
                    funt.isChecked -> (sum * 0.0079).toString() // Конвертация в фунты
                    euro.isChecked -> (sum * 0.0096).toString() // Конвертация в евро
                    dollar.isChecked -> (sum * 0.0107).toString() // Конвертация в доллары
                    else -> "Выберите валюту для конвертации."
                }
            } else {
                "Пожалуйста, введите корректное число."
            }

            // Запускаем ResultActivity и передаём результат
            val intent = Intent(this, ResultActivity::class.java).apply {
                putExtra("RESULT", resultMessage) // Исправлено на правильное использование переменной
            }
            startActivity(intent) // Запуск новой активности

            // Отображение результата на текущем экране (если нужно)
            view.text = resultMessage // Можно оставить или убрать в зависимости от вашего желания
        }
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