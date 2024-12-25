package com.abadzheva.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyReceiver : BroadcastReceiver() {
    override fun onReceive(
        context: Context?,
        intent: Intent?,
    ) {
        val action = intent?.action
        when (action) {
            ACTION_CUSTOM_BROADCAST -> {
                val count = intent.getIntExtra(EXTRA_COUNT, 0)
                Toast
                    .makeText(context, "Custom broadcast received. Count: $count", Toast.LENGTH_SHORT)
                    .show()
            }

            Intent.ACTION_BATTERY_LOW -> {
                Toast
                    .makeText(context, "Battery low", Toast.LENGTH_SHORT)
                    .show()
            }

            Intent.ACTION_AIRPLANE_MODE_CHANGED -> {
                val turnedOn = intent.getBooleanExtra("state", false)
                Toast
                    .makeText(
                        context,
                        "Airplane mode changed. Turned on: $turnedOn",
                        Toast.LENGTH_SHORT,
                    ).show()
            }
        }
    }

    companion object {
        const val ACTION_CUSTOM_BROADCAST =
            "com.example.broadcast.ACTION_CUSTOM_BROADCAST"
        const val EXTRA_COUNT = "count"
    }
}
