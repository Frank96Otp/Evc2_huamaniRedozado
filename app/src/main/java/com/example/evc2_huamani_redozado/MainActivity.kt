package com.example.evc2_huamani_redozado


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.evc2_huamani_redozado.databinding.ActivityMainBinding
import com.example.evc2_huamani_redozado.databinding.ActivityOpcionesBinding

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGoToMain.setOnClickListener {
            val intent = Intent(this,OpcionesActivity::class.java)
            startActivity(intent)
        }


    }
}