package com.example.myapplication

import android.content.Intent
import android.location.Location
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.osmdroid.api.IMapController
import org.osmdroid.config.Configuration
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import android.location.LocationManager
import android.content.Context

class MapActivity : AppCompatActivity() {

    private lateinit var mapView: MapView
    private lateinit var locationManager: LocationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Убедитесь, что конфигурация загружается
        Configuration.getInstance().load(this, applicationContext.getSharedPreferences("osmdroid", MODE_PRIVATE))

        setContentView(R.layout.activity_map)

        mapView = findViewById(R.id.map)
        mapView.setMultiTouchControls(true)

        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        // Настройка карты и камеры
        val mapController: IMapController = mapView.controller
        mapController.setZoom(15.0)
        val kremlinPoint = GeoPoint(55.752023, 37.617499) // Координаты Кремля
        mapController.setCenter(kremlinPoint)

        // Добавление маркера заказа на Кремль
        val kremlinMarker = Marker(mapView)
        kremlinMarker.position = kremlinPoint
        kremlinMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
        kremlinMarker.title = "Заказ №001"
        kremlinMarker.setOnMarkerClickListener { _, _ ->
            val intent = Intent(this, OrderDetailActivity::class.java)
            startActivity(intent)
            true
        }

        val MIREAMarker = Marker(mapView)
        MIREAMarker.position = GeoPoint(55.669534, 37.481954) // Координаты Мирэа
        MIREAMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
        MIREAMarker.title = "Заказ №002"
        MIREAMarker.setOnMarkerClickListener { _, _ ->
            val intent = Intent(this, OrderDetailActivityMir::class.java)
            startActivity(intent)
            true
        }

        mapView.overlays.add(kremlinMarker)
        mapView.overlays.add(MIREAMarker)

        // Добавление маркера для текущего местоположения пользователя
        addUserLocationMarker()

        // Кнопка "Назад"
        val backButton: Button = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            finish()
        }
    }

    // Метод для добавления маркера местоположения пользователя
    private fun addUserLocationMarker() {
        try {
            val location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)

            if (location != null) {
                val userLocation = GeoPoint(location.latitude, location.longitude)
                val userMarker = Marker(mapView)
                userMarker.position = userLocation
                userMarker.setAnchor(Marker.ANCHOR_TOP, Marker.ANCHOR_BOTTOM)
                userMarker.title = "Местоположение пользователя"

                mapView.overlays.add(userMarker)
                mapView.controller.setCenter(userLocation) // Установить центр карты на местоположение пользователя
            } else {
                Toast.makeText(this, "Не удалось получить местоположение", Toast.LENGTH_SHORT).show()
            }
        } catch (e: SecurityException) {
            Toast.makeText(this, "Нет разрешения для доступа к местоположению", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }
}
