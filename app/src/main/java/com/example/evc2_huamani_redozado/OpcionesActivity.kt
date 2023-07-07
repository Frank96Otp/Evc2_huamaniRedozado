package com.example.evc2_huamani_redozado

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.evc2_huamani_redozado.databinding.ActivityOpcionesBinding

class OpcionesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOpcionesBinding
    private lateinit var openCameraLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityOpcionesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCamara.setOnClickListener {
            if(permissionValited()){
                takePhoto()
            }
        }

        openCameraLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
            if (result.resultCode == RESULT_OK){
                val photoBipmap = result.data?.extras?.get("data") as Bitmap
                binding.imgPhoto.setImageBitmap(photoBipmap)
            }

        }

        binding.btnMapa.setOnClickListener {
            val intent = Intent(this,AddressActivity::class.java)
            startActivity(intent)
        }
    }

    private fun takePhoto() {
        val cameraIntent =  Intent()
        cameraIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE)
        openCameraLauncher.launch(cameraIntent)
    }

    private fun permissionValited(): Boolean {
        val camaraPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)

        val permissionList: MutableList<String> =  mutableListOf()

        if (camaraPermission != PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.CAMERA)
        }

        if(permissionList.isNotEmpty()){
            ActivityCompat.requestPermissions(this, permissionList.toTypedArray(),1000)
            return false
        }

        return true
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode){
            1000 ->{
                if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
                    takePhoto()
                }
            }
        }
    }


}