package com.example.simplevpn

import android.content.Intent
import android.net.VpnService
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var status: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        status = findViewById(R.id.status)

        findViewById<Button>(R.id.connect).setOnClickListener {
            val intent = VpnService.prepare(this)

            if (intent != null) {
                startActivityForResult(intent, 100)
            } else {
                startVpn()
            }
        }

        findViewById<Button>(R.id.disconnect).setOnClickListener {
            stopService(Intent(this, MyVpnService::class.java))
            status.text = "Статус: Отключено 🔴"
        }
    }

    private fun startVpn() {
        startService(Intent(this, MyVpnService::class.java))
        status.text = "Статус: Подключено 🟢"
    }
}
