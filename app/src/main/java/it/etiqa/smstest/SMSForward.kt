package it.etiqa.smstest

import android.os.AsyncTask
import android.util.Log
import com.github.kittinunf.fuel.httpGet

class SMSForward : AsyncTask<String, String, String>() {
    val TAG = "SMSActivity"

    override fun doInBackground(vararg params: String): String {
        val (request, response, result) = params?.get(0).httpGet().responseString()
        Log.i(TAG, response.responseMessage)
        return response.toString()
    }
}