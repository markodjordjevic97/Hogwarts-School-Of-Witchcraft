package com.pma.hogwartsapplication.view.wizarddetails

import android.content.Context
import android.graphics.Color
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.pma.hogwartsapplication.R
import com.pma.hogwartsapplication.base.model.Wizard
import kotlinx.android.synthetic.main.item_wizard_details.view.*

class WizardDetailView (context: Context) : ConstraintLayout(context) {

    private val view = View.inflate(context, R.layout.item_wizard_details, this)

    private val colorsBackground: HashMap<String, String> = hashMapOf(
        "Slytherin" to "#1A472A",
        "Ravenclaw" to "#0E1A40",
        "Hufflepuff" to "#FFD800",
        "Gryffindor" to "#740001"

    )
    private val colors: HashMap<String, String> = hashMapOf(
        "Slytherin" to "#aaaaaa",
        "Ravenclaw" to "#946B2D",
        "Hufflepuff" to "#000000",
        "Gryffindor" to "#D3A625"

    )

    fun bind(wizard: Wizard) {
        view.setBackgroundColor(Color.parseColor(colorsBackground[wizard.house]))

        view.details_header.setTextColor(Color.parseColor(colors[wizard.house]))
        view.details_header.text = wizard.house + " house"

        Glide.with(view).load(wizard.image).placeholder(R.drawable.noimageavailable).dontAnimate()
            .into(view.details_image)

        view.wizardName.text = wizard.name
        view.wizardName.setTextColor(Color.parseColor(colors[wizard.house]))

        view.actorName.text = wizard.actor
        view.actorName.setTextColor(Color.parseColor(colors[wizard.house]))

        view.wizardHouse.text = wizard.house
        view.wizardHouse.setTextColor(Color.parseColor(colors[wizard.house]))

        view.wizardAncestry.text = wizard.ancestry
        view.wizardAncestry.setTextColor(Color.parseColor(colors[wizard.house]))

        view.wizardWand.text = wizard.wand.wood
        view.wizardWand.setTextColor(Color.parseColor(colors[wizard.house]))

        view.wizardPatronus.text = wizard.patronus
        view.wizardPatronus.setTextColor(Color.parseColor(colors[wizard.house]))

        view.wizardSpecies.text = wizard.species
        view.wizardSpecies.setTextColor(Color.parseColor(colors[wizard.house]))
    }
}