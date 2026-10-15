package edu.temple.dicethrow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlin.random.Random
import androidx.lifecycle.ViewModelProvider
const val DIESIDE = "sidenumber"

class DieFragment : Fragment() {

    // accessing view model via lazy instantiation
    // lazy holds on to what the dieViewModels getter function returns
    val dieViewModel : DieViewModel by lazy {
        ViewModelProvider(this).get(DieViewModel::class.java)
    }


    lateinit var dieTextView: TextView

    var dieSides: Int = 6
    // var currRoll: Int = 0
    val CURR_ROLL = "currRoll"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            it.getInt(DIESIDE).run {
                dieSides = this
            }
        }

//        savedInstanceState?.let {
//            it.getInt(CURR_ROLL).run {
//                currRoll = this
//            }
//        }

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
//        if (currRoll == 0) {
//            throwDie()
//        } else {
//            dieTextView.text = currRoll.toString()
//        }

        // create observer instead, something to observe changes in the live data
        dieViewModel.getRoll().observe(viewLifecycleOwner) { rollValue ->
            dieTextView.text = rollValue.toString()
        }

        view.setOnClickListener{
            (requireActivity() as DieRollInterface).rollDice()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        // outState.putInt(CURR_ROLL, currRoll)

    }

    // no zeros in die roll
    fun throwDie() {
        dieViewModel.setRoll(Random.nextInt(dieSides) + 1)
//      dieTextView.text = currRoll.toString()
    }

    companion object {
        fun newInstance(sides: Int) = DieFragment().apply {
            arguments = Bundle().apply {
                putInt(DIESIDE, sides)
            }
        }
    }

    interface DieRollInterface {
        fun rollDice()
    }
}