package edu.ecu.cs.pirateplaces

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import android.content.Intent as Intent


private const val EXTRA_pirate_places =  "edu.ecu.cs.pirateplaces.check_in"

class CheckActivity : AppCompatActivity() {
    private lateinit var CheckInbutton: Button
    private lateinit var checkPlaceTextView: TextView
    private lateinit var checkStatusTextView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check)
        checkPlaceTextView = findViewById(R.id.Check_Place_view)
        checkStatusTextView = findViewById(R.id.status_text_view)
        CheckInbutton= findViewById(R.id.Check_button)
        val settings = Intent(this, PiratePlacesActivity::class.java)
        checkPlaceTextView.setText(getIntent().getStringExtra("PirateText"))
        checkStatusTextView.setText(getIntent().getStringExtra("stat"))
        CheckInbutton.setOnClickListener { view: View ->

            if (checkStatusTextView.getText() == "Checked In")
            {
                Toast.makeText(
                    this,
                    R.string.Check_toast,
                    Toast.LENGTH_SHORT)
                    .show()
            }else {
                checkStatusTextView.setText("Checked In")
                val status = "Checked In"
                settings.putExtra("status",status)
                setResult(Activity.RESULT_OK,settings)
                 finish()




            }

        }
    }

    companion object {
        fun newIntent(packageContext: Context, pirateText: Class<CheckActivity>): Intent {
            return Intent(packageContext, CheckActivity::class.java).apply {
                putExtra(EXTRA_pirate_places, pirateText)
            }
        }
    }


}
