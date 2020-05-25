package edu.ecu.cs.pirateplaces

import androidx.annotation.StringRes

data class Places(
    @StringRes val textResId: Int, val names :String,val status: String  )
