package it.etiqa.smstest

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import android.telephony.SmsMessage
import android.util.Log

class SmsListener : BroadcastReceiver() {

    val TAG = "SMSActivity"

    override fun onReceive(context: Context, intent: Intent) {
        Log.i(TAG,"Sms received!________________________________________")
        if (Telephony.Sms.Intents.SMS_RECEIVED_ACTION.equals(intent.getAction())) {
            for (smsMessage: SmsMessage in Telephony.Sms.Intents.getMessagesFromIntent(intent)) {
                val messageBody = smsMessage.messageBody
                Log.i(TAG,messageBody)
            }
        }
    }
}
