package com.example.myapplication

import android.os.Bundle
import android.widget.Spinner
import android.widget.ArrayAdapter
import android.content.Intent
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val languageSpinner: Spinner = findViewById(R.id.languageSpinner)
        val backgroundSpinner: Spinner = findViewById(R.id.backgroundSpinner)
        val backToMenuButton: Button = findViewById(R.id.backToMenuButton)


        // Список языков для выбора
        val languages = arrayOf("Русский", "English")
        val languageAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, languages)
        languageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        languageSpinner.adapter = languageAdapter

        // Список фоновых изображений для выбора
        val backgrounds = arrayOf("Фон 1", "Фон 2", "Фон 3")
        val backgroundAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, backgrounds)
        backgroundAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        backgroundSpinner.adapter = backgroundAdapter

        backToMenuButton.setOnClickListener {
            // Возвращаемся на главную активность (MainActivity)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Закрываем текущую активность
        }
        // Логика изменения языка и фона будет добавлена позже
    }
}
