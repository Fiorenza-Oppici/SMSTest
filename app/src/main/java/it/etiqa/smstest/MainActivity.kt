package it.etiqa.smstest

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val TAG = "SMSActivity"

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkPermissions()
        configureSwitch()
        loadServerUrl()
    }

    private fun checkPermissions () {
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

    private fun configureSwitch () {
        val sharedPref = getSharedPreferences(TAG, Context.MODE_PRIVATE)
        val activateLabel = getString(R.string.activated_state_label)

        // remember status from sharedproperties
        activateSend.isChecked = sharedPref.getBoolean(activateLabel, false)

        activateSend.setOnCheckedChangeListener { _, isChecked -> run {

            with (sharedPref.edit()) {
                    putBoolean(activateLabel, isChecked)
                commit()
                }
            }
        }
    }

    private fun loadServerUrl() {
        val sharedPref = getSharedPreferences(TAG, Context.MODE_PRIVATE) ?: return
        val defaultValue = resources.getString(R.string.server_url_placeholder)
        val serverUrl = sharedPref.getString(getString(R.string.server_url_label), defaultValue)

        if (serverUrl != defaultValue) {
            serverUrlInput.setText(serverUrl)
        }
    }

    fun saveTargetUrl (view: View) {
        Log.i(TAG, "Saving Preferences - target server")
        val serverUrl =  if (serverUrlInput.text.toString()?.length != 0)  serverUrlInput.text.toString() else resources.getString(R.string.server_url_placeholder)

        val sharedPref = getSharedPreferences(TAG, Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()) {
            putString(getString(R.string.server_url_label), serverUrl)
            commit()
        }

    }
}
