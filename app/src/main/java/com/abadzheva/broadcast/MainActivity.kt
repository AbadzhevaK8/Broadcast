package com.abadzheva.broadcast

import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private val receiver = MyReceiver()

    private var count = 0

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<Button>(R.id.button).setOnClickListener {
            Intent(MyReceiver.ACTION_CUSTOM_BROADCAST).apply {
                putExtra(MyReceiver.EXTRA_COUNT, ++count)
                sendBroadcast(this)
            }
        }

        val intentFilter =
            IntentFilter().apply {
                addAction(Intent.ACTION_BATTERY_LOW)
                addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
                addAction(MyReceiver.ACTION_CUSTOM_BROADCAST)
            }
        registerReceiver(receiver, intentFilter, RECEIVER_EXPORTED)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }
}
