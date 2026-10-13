package edu.temple.dicethrow

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // automatically creates a fragment which makes it mess up everytime you rotate the device

        // use if statement to see if a fragment already made inside of the contatainer

        // only a concern if you add fragments via transactions

        // dont use .replace waste of memory


        if (supportFragmentManager.findFragmentById(R.id.fragmentContainerView) == null)
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainerView, DieFragment.newInstance(20))
                .commit()

        findViewById<Button>(R.id.rollDiceButton).setOnClickListener {
            val fragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as? DieFragment
            fragment?.throwDie()
        }
    }
}