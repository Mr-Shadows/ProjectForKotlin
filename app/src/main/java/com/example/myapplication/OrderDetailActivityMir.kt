package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class OrderDetailActivityMir : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_detail)

        val backToOrdersButton: Button = findViewById(R.id.backToOrdersButton)
        backToOrdersButton.setOnClickListener {
            finish() // Закрывает OrderDetailActivity и возвращает на OrdersActivity
        }
    }
}
