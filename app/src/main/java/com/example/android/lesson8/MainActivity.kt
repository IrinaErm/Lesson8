package com.example.android.lesson8

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.android.lesson8.NotificationsUtil.Companion.NOTIF_RESULT_STRING
import com.example.android.lesson8.NotificationsUtil.Companion.createLocalNotification
import com.example.android.lesson8.NotificationsUtil.Companion.createLocalNotificationsChannel
import com.example.android.lesson8.databinding.ActivityMainBinding
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createLocalNotificationsChannel(this)
        generateFCMRegistrationToken()

        intent?.extras?.getString(NOTIF_RESULT_STRING)?.let {
            binding.textView.text = it
        }

        binding.localNotifBtn.setOnClickListener {
            createLocalNotification(this, getString(R.string.joke))
        }
    }

    private fun generateFCMRegistrationToken() {
        checkGooglePlayServices()
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener {task ->
            if (!task.isSuccessful) {
                Log.w("WARNING", "No FCM registration token for you")
                return@OnCompleteListener
            }

            val token = task.result

            Log.d("TOKEN", token)
        })
    }


    private fun checkGooglePlayServices(): Boolean {
        val status = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this)

        return if (status != ConnectionResult.SUCCESS) {
            /*TODO: ask user to update Google services and manage the error */

            Log.w("WARNING", "Not connected to Google services")
            false
        } else {
            true
        }
    }

}