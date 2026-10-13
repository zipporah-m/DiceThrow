package edu.temple.dicethrow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlin.random.Random

const val DIESIDE = "sidenumber"

class DieFragment : Fragment() {


    lateinit var dieTextView: TextView

    var dieSides: Int = 6
    var currRoll: Int = 0
    val CURR_ROLL = "currRoll"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            it.getInt(DIESIDE).run {
                dieSides = this
            }
        }

        savedInstanceState?.let {
            it.getInt(CURR_ROLL).run {
                currRoll = this
            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_die, container, false).apply {
            dieTextView = findViewById(R.id.dieTextView)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (currRoll == 0) {
            throwDie()
        } else {
            dieTextView.text = currRoll.toString()
        }

        view.setOnClickListener{
            throwDie()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt(CURR_ROLL, currRoll)

    }

    // no zeros in die roll
    fun throwDie() {
        currRoll = (Random.nextInt(dieSides) + 1)
        dieTextView.text = currRoll.toString()
    }

    companion object {
        fun newInstance(sides: Int) = DieFragment()
            .arguments?.apply {
                Bundle().apply {
                    putInt(DIESIDE, sides)
                }
            }
    }
}