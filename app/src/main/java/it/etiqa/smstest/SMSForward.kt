package it.etiqa.smstest

import android.os.AsyncTask
import android.util.Log
import khttp.get

class SMSForward : AsyncTask<String, String, String>() {
    val TAG = "SMSActivity"

    override fun doInBackground(vararg params: String): String {
        Log.i(TAG, "ciaoo")
        return get(params?.get(0)).text
    }
}