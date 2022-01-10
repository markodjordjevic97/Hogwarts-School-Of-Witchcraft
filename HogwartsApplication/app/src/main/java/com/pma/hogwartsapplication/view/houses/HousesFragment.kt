package com.pma.hogwartsapplication.view.houses

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pma.hogwartsapplication.R
import com.pma.hogwartsapplication.base.ICoordinator
import com.pma.hogwartsapplication.base.model.House
import com.pma.hogwartsapplication.view.houses.adapter.HouseAdapter
import kotlinx.android.synthetic.main.fragment_houses.*

class HousesFragment : Fragment() {

    private val houseMap = hashMapOf<String, Int>(
        "gryffindor" to R.drawable.gryffindor,
        "hufflepuff" to R.drawable.hufflepuff,
        "ravenclaw" to R.drawable.ravenclaw,
        "slytherin" to R.drawable.slytherin
    )
    private val houseImageList:ArrayList<House>  = arrayListOf(
        House("gryffindor", houseMap.getValue("gryffindor").toInt()),
        House("slytherin", houseMap.getValue("slytherin").toInt()),
        House("ravenclaw", houseMap.getValue("ravenclaw").toInt()),
        House("hufflepuff", houseMap.getValue("hufflepuff").toInt()),
    )
    private lateinit var contextActivity : Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        contextActivity = container?.context!!
        return inflater.inflate(R.layout.fragment_houses, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showCharactersList(contextActivity)
    }

    private fun showCharactersList(context: Context) {

        val houseImageAdapter = HouseAdapter(houseImageList, context)
        houseGridView.adapter = houseImageAdapter

        houseGridView.setOnItemClickListener {adapterView, view, i, l ->
            (activity as ICoordinator).showWizardListFragment(houseImageList[i].name)
        }
    }
}