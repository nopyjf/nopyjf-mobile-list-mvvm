package com.example.nopyjf.nopyjfmobilelistmvvm.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.nopyjf.nopyjfmobilelistmvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityMainBinding.inflate(layoutInflater).root)
    }
}