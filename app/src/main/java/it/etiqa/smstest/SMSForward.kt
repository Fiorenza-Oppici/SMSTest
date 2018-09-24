package it.etiqa.smstest

import android.os.AsyncTask
import android.util.Log
import com.github.kittinunf.fuel.httpPost
import org.json.JSONObject
import java.util.*



class SMSForward : AsyncTask<String, Map<String, String>, String>() {
    val TAG = "SMSActivity"

    override fun doInBackground(vararg params: String?): String {
        val endpoint = params[0]?: ""
        val smsBody = params[1]?: ""

        val sanitizedEndpoint = endpoint.replace("localhost", "10.0.2.2")

        val bodyObj = JSONObject()
        bodyObj.put("time",  Date())
        bodyObj.put("messageBody", smsBody)

        val requestPayload = bodyObj.toString()

        val (request, response, result) = sanitizedEndpoint.httpPost().body(requestPayload).responseString()

        Log.i(TAG, response.toString())
        return response.toString()
    }
}