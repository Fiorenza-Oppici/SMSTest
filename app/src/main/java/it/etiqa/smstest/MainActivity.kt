package it.etiqa.smstest

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityCompat.shouldShowRequestPermissionRationale
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val TAG = "SMSActivity"

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkPermissions()
        checkToggle()
        loadServerUrl()
    }

    fun checkPermissions () {
        Log.i(TAG, "_____________________________________________________________")

        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.RECEIVE_SMS)
                != PackageManager.PERMISSION_GRANTED) {

            Log.i(TAG, "Permission not granted yet")

            if (shouldShowRequestPermissionRationale(this,
                            Manifest.permission.RECEIVE_SMS)) {
                Log.i(TAG, "Permission rationale required")

            } else {
                ActivityCompat.requestPermissions(this,
                        arrayOf(Manifest.permission.RECEIVE_SMS),
                        1)
                Log.i(TAG, "Permission rationale not required ")
            }
        } else {
            Log.i(TAG, "Permission given")
        }
    }

    fun checkToggle () {
        activateSend.setOnCheckedChangeListener { _, isChecked -> run {
                val sharedPref = getPreferences(Context.MODE_PRIVATE)
            with (sharedPref.edit()) {
                putBoolean("activated_state", activateSend.isChecked)
                }
            }
        }
    }

    fun loadServerUrl() {
        val sharedPref = getPreferences(Context.MODE_PRIVATE) ?: return
        val defaultValue = resources.getString(R.string.server_url)
        val serverUrl = sharedPref.getString(getString(R.string.server_url), defaultValue)

        if (serverUrl != defaultValue) {
            serverUrlInput.setText(serverUrl)
        }
    }

    fun save () {
        Log.i(TAG, "Saving Preferences")
        val serverUrl = serverUrlInput.text.toString()

        val sharedPref = getPreferences(Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()) {
            putString(getString(R.string.server_url), serverUrl)
            commit()
        }

    }
}
