package com.example.evc2_huamani_redozado

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions

class AddressActivity : AppCompatActivity() , OnMapReadyCallback {

    private lateinit var  map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.fragmenMaps) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(p0: GoogleMap) {
        map = p0
        val lima = LatLng(-12.0431800,-77.0282400)
        val idat = LatLng(-12.1575133,-76.9839574)
        val parqueHuascar = LatLng(-12.2267122,-76.9451662)
        map.addMarker(MarkerOptions().position(lima).title("Lima, capital de peru"))
        map.addMarker(MarkerOptions().position(idat).title("Instituto idat"))
        map.addMarker(MarkerOptions().position(parqueHuascar).title("Parque Huazkar"))
        val boundBuilder  = LatLngBounds.builder()
            .include(lima)
            .include(idat)
            .include(parqueHuascar)

        map.animateCamera(CameraUpdateFactory.newLatLngBounds(boundBuilder.build(), 30))

        //map.moveCamera(CameraUpdateFactory.newLatLngZoom(lima,16f))
    }
}