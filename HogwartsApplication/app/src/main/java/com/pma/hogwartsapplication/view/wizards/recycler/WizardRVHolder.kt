package com.pma.hogwartsapplication.view.wizards.recycler

import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pma.hogwartsapplication.R
import com.pma.hogwartsapplication.base.model.Wizard
import kotlinx.android.synthetic.main.item_wizard.view.*

class WizardRVHolder (view: View) : RecyclerView.ViewHolder(view) {

    private val colors: HashMap<String, String> = hashMapOf(
        "Slytherin" to "#1A472A",
        "Ravenclaw" to "#0E1A40",
        "Hufflepuff" to "#FFD800",
        "Gryffindor" to "#740001"

    )

    fun bind(wizard: Wizard, onItemClicked: (String) -> Unit) {
        itemView.view.setBackgroundColor(Color.parseColor(colors[wizard.house]))
        itemView.wizardName.text = wizard.name
        itemView.wizardHouse.text = wizard.house

        Glide.with(itemView).load(wizard.image).placeholder(R.drawable.noimageavailable).dontAnimate().into(itemView.wizardImage)

        itemView.setOnClickListener { onItemClicked.invoke(wizard.name) }
    }
}