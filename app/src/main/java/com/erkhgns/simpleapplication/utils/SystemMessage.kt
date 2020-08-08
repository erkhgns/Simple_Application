package com.erkhgns.simpleapplication.utils

import com.erkhgns.simpleapplication.constants.Constants.Companion.MESSAGE_TYPE_NORMAL

class SystemMessage(
    val title: String="Warning",
    val message: String,
    val messageType: Int = MESSAGE_TYPE_NORMAL,
    val option1Text: String="",
    val option2Text: String="",
    val iOptionsListener: IOptionsListener?=null
) {
}