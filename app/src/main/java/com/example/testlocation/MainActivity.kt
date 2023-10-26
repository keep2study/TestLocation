package com.example.testlocation

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.hardware.SensorManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity(),LocationListener {
    private lateinit var locationManager: LocationManager
    private lateinit var geocoder: Geocoder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        locationManager=getSystemService(Context.LOCATION_SERVICE) as LocationManager




        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            //return
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),1)
        }

        var hasGps=locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        var hasNetworkLoc=locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

        if (hasGps) {
            Log.d("myTag", "設備提供GPS")
        }
        if (hasNetworkLoc){
            Log.d("myTag", "設備提供Network Location")
        }

        if (hasGps || hasNetworkLoc) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 5.0F, this)

        }else{
            Log.d("myTag","設備未提供定位服務")
        }


    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        Log.d("myTag","code: $requestCode   Result : $grantResults")
    }

    override fun onLocationChanged(location: Location) {
        //TODO("Not yet implemented")
        Log.d("myTag",location.longitude.toString())
    }
}