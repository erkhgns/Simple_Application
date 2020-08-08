package com.erkhgns.simpleapplication.utils

import android.view.View
import androidx.fragment.app.FragmentActivity
import com.erkhgns.simpleapplication.R
import com.erkhgns.simpleapplication.constants.Constants.Companion.MESSAGE_TYPE_NORMAL
import com.tapadoo.alerter.Alerter

class Utils {
    companion object{
        fun isCompleteFields(strings: ArrayList<String?>): Boolean{
            var isCompleteFields = true

            for(string in strings){
                if(string.isNullOrBlank()){
                    isCompleteFields = false
                }
            }
            return isCompleteFields
        }

        private fun showWarningMessageOnFragment(
            activity: FragmentActivity,
            title: String = "Warning",
            text: String
        ) {
            Alerter.create(activity).setTitle(title).setText(text).setDismissable(true)
                .setDuration(2000).setBackgroundColorRes(R.color.secondaryAccent)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setIconColorFilter(R.color.black)
                .setTitleAppearance(R.style.AlerterTitle)
                .setTextAppearance(R.style.AlerterText)
                .enableSwipeToDismiss()
                .show()
        }
        fun showSystemMessageOnFragment(activity: FragmentActivity, systemMessage: SystemMessage){
            if (systemMessage.messageType == MESSAGE_TYPE_NORMAL) {
                showWarningMessageOnFragment(activity, systemMessage.title, systemMessage.message)
            }else{
                showMessageWithOptionsOnFragment(
                    activity,
                    systemMessage.title,
                    systemMessage.message,
                    systemMessage.option1Text,
                    systemMessage.option2Text,
                    systemMessage.iOptionsListener!!
                )
            }
        }

        private fun showMessageWithOptionsOnFragment(
            activity: FragmentActivity,
            title: String = "Warning",
            text: String,
            option1Title: String,
            option2Title: String,
            iOptionsListener: IOptionsListener
        ) {
            val alerter: Alerter =
                Alerter.create(activity).setDuration(2000).setDismissable(true).setText(text)
                    .setTitle(title)
                    .setBackgroundColorRes(R.color.secondaryAccent)
                    .setTitleAppearance(R.style.AlerterTitle)
                    .setTextAppearance(R.style.AlerterText)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setIconColorFilter(R.color.black)
                    .enableSwipeToDismiss()
            alerter.addButton(option1Title, R.style.AlertButton, View.OnClickListener {
                iOptionsListener.onOption1Click()
                Alerter.hide()

            })

            alerter.addButton(option2Title, R.style.AlertButton, View.OnClickListener {
                iOptionsListener.onOption2Click()
                Alerter.hide()
            })

            alerter.show()


        }

    }
}