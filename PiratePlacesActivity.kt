package edu.ecu.cs.pirateplaces

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import java.lang.reflect.Array.set

private const val TAG = "PiratePlacesActivity"
private const val KEY_INDEX = "index"

class PiratePlacesActivity() : AppCompatActivity(), Parcelable {

    private lateinit var Previousbutton: Button
    private lateinit var Nextbutton: Button

    private var currentIndex = 0


    private lateinit var PiratePlaceTextView: TextView
    private lateinit var PiratePersonTextView: TextView

    private val pirateViewModel: PirateViewModel by lazy {
        ViewModelProviders.of(this).get(PirateViewModel::class.java)
    }
    constructor(parcel: Parcel) : this() {
        currentIndex = parcel.readInt()
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")
        setContentView(R.layout.activity_pirate_places)
        val currentIndex = savedInstanceState?.getInt(KEY_INDEX, 0) ?: 0
        pirateViewModel.currentIndex = currentIndex


        Previousbutton = findViewById(R.id.Previous_button)
        Nextbutton = findViewById(R.id.Next_button)
        PiratePlaceTextView = findViewById(R.id.Pirate_Place_view)
        PiratePersonTextView = findViewById(R.id.Pirate_Person_view)

        Previousbutton.setOnClickListener { view: View ->

             if (pirateViewModel.currentpiratenames =="Roger")
             {
                 Toast.makeText(
                     this,
                     R.string.Prev_toast,
                     Toast.LENGTH_SHORT)
                     .show()
             }else{
                 pirateViewModel.moveToNext()


                 val PlaceTextResId = pirateViewModel.currentpirateplace
            val PersonTextResId = pirateViewModel.currentpiratenames


            PiratePlaceTextView.setText(PlaceTextResId)
            PiratePersonTextView.setText(PersonTextResId)

        }

        }
             PiratePlaceTextView.setOnClickListener{
                    val pirateText = pirateViewModel.currentpirateplace
                   val intent = CheckActivity.newIntent(this, CheckActivity::class.java)
                  val nameText = PiratePlaceTextView.text
                   intent.putExtra( "PirateText" ,nameText)
                    intent.putExtra("stat",pirateViewModel.currentpiratestatus)
                     startActivityForResult(intent,1)


             }




        Nextbutton.setOnClickListener { view: View ->

            if (pirateViewModel.currentpiratenames == "Rebecca")
            {
                Toast.makeText(
                    this,
                    R.string.Next_toast,
                    Toast.LENGTH_SHORT)
                    .show()
            }else {
                pirateViewModel.moveToNext()

                val PlaceTextResId = pirateViewModel.currentpirateplace
                val PersonTextResId = pirateViewModel.currentpiratenames

                PiratePlaceTextView.setText(PlaceTextResId)
                PiratePersonTextView.setText(PersonTextResId)

            }

        }

        val questionTextResId = pirateViewModel.currentpirateplace
        PiratePlaceTextView.setText(questionTextResId)
        val PersonText = pirateViewModel.currentpiratenames
        PiratePersonTextView.setText(PersonText)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == -1)
        {
            if(resultCode == Activity.RESULT_OK)
            {




                val status = data?.getCharExtra("status", 'C')

                Toast.makeText(
                    this,
                    R.string.Status,
                    Toast.LENGTH_SHORT)
                    .show()

                }
            }
        }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        Log.i(TAG, "onSaveInstanceState")
        savedInstanceState.putInt(KEY_INDEX, pirateViewModel.currentIndex)
    }
    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(currentIndex)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PiratePlacesActivity> {
        override fun createFromParcel(parcel: Parcel): PiratePlacesActivity {
            return PiratePlacesActivity(parcel)
        }

        override fun newArray(size: Int): Array<PiratePlacesActivity?> {
            return arrayOfNulls(size)
        }
    }
}
