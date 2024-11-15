package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegistrationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        val loginButton: Button = findViewById(R.id.loginButton)
        val usernameInput: EditText = findViewById(R.id.usernameEditText)
        val passwordInput: EditText = findViewById(R.id.passwordEditText)

        loginButton.setOnClickListener {
            val username = usernameInput.text.toString()
            val password = passwordInput.text.toString()

            if (username == "test" && password == "1234") {
                // Успешный логин
                Toast.makeText(this, "Вход выполнен успешно", Toast.LENGTH_SHORT).show()

                val resultIntent = Intent()

                // Получаем целевую активность из Intent, если она была передана
                val targetActivity = intent.getStringExtra("target_activity")
                resultIntent.putExtra("target_activity", targetActivity)

                setResult(RESULT_OK, resultIntent)
                finish() // Закрываем RegistrationActivity и возвращаемся в MainActivity
            } else {
                // Неверный логин или пароль
                Toast.makeText(this, "Неверный логин или пароль", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
