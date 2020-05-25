package edu.ecu.cs.pirateplaces

import android.util.Log
import androidx.lifecycle.ViewModel
private const val TAG = "PirateViewModel"

class PirateViewModel : ViewModel() {
    private val questionBank = listOf(
        Places(textResId = R.string.Pirate_place_ScTech, names = "Roger",status = "Not Checked In"),
        Places(R.string.Pirate_place_Library, "Sally",status = "Not Checked In" ),
        Places(R.string.Pirate_place_Monument, "Rebecca",status = "Checked In")
    )
     var currentIndex = 0

    val currentpiratestatus : String
        get() = questionBank[currentIndex].status

    val currentpirateplace: Int
        get() = questionBank[currentIndex].textResId
    val currentpiratenames :String
    get() = questionBank[currentIndex].names

    fun moveToNext() {
        currentIndex = (currentIndex + 1) % questionBank.size
    }
}