package com.pma.hogwartsapplication.view.wizards.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pma.hogwartsapplication.R
import com.pma.hogwartsapplication.base.model.Wizard

class WizardRVAdapter (
    private val wizards: List<Wizard>,
    private val onItemClicked: (String) -> Unit
) : RecyclerView.Adapter<WizardRVHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = WizardRVHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_wizard, parent, false)
    )

    override fun onBindViewHolder(holder: WizardRVHolder, position: Int) {
        holder.bind(wizards[position], onItemClicked)
    }

    override fun getItemCount() = wizards.size
}