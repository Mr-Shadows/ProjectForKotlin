package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var isLoggedIn = false // Переменная для отслеживания логина пользователя

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val orderListButton: Button = findViewById(R.id.orderListButton)
        val mapButton: Button = findViewById(R.id.mapButton)
        val settingsButton: Button = findViewById(R.id.settingsButton)
        val loginTextView: TextView = findViewById(R.id.loginTextView)

        // Логика для кнопки "Список заказов"
        orderListButton.setOnClickListener {
            if (!isLoggedIn) {
                // Если не залогинен, открываем экран логина и передаем информацию о запросе для открытия OrdersActivity
                val intent = Intent(this, RegistrationActivity::class.java)
                intent.putExtra("target_activity", "OrdersActivity")
                startActivityForResult(intent, 1)
            } else {
                // Открываем OrdersActivity
                val intent = Intent(this, OrdersActivity::class.java)
                startActivity(intent)
            }
        }

        // Логика для кнопки "Карта"
        mapButton.setOnClickListener {
            if (!isLoggedIn) {
                // Если не залогинен, открываем экран логина и передаем информацию о запросе для открытия MapActivity
                val intent = Intent(this, RegistrationActivity::class.java)
                intent.putExtra("target_activity", "MapActivity")
                startActivityForResult(intent, 1)
            } else {
                // Открываем MapActivity
                val intent = Intent(this, MapActivity::class.java)
                startActivity(intent)
            }
        }

        // Логика для кнопки "Настройки"
        settingsButton.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

        // Логика для "Вход в аккаунт" или "Выход из аккаунта"
        loginTextView.setOnClickListener {
            if (isLoggedIn) {
                // Логика выхода из аккаунта
                isLoggedIn = false
                loginTextView.text = "Вход в аккаунт"
            } else {
                // Открываем страницу логина
                val intent = Intent(this, RegistrationActivity::class.java)
                startActivityForResult(intent, 1)
            }
        }
    }

    // Обработка результата логина
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK) {
            // Если логин успешен
            isLoggedIn = true
            val loginTextView: TextView = findViewById(R.id.loginTextView)
            loginTextView.text = "Выход из аккаунта"

            // Открытие запрошенной активности, если она была передана
            val targetActivity = data?.getStringExtra("target_activity")
            when (targetActivity) {
                "OrdersActivity" -> {
                    val intent = Intent(this, OrdersActivity::class.java)
                    startActivity(intent)
                }
                "MapActivity" -> {
                    val intent = Intent(this, MapActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}
