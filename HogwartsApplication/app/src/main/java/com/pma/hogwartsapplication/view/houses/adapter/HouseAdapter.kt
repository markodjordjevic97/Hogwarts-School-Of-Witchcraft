package com.pma.hogwartsapplication.view.houses.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.pma.hogwartsapplication.R
import com.pma.hogwartsapplication.base.model.House

class HouseAdapter (
    private val houses: ArrayList<House>,
    private val context: Context
) : BaseAdapter() {

    var layoutInflater = this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int = houses.size

    override fun getItem(index: Int): Any = houses[index]

    override fun getItemId(index: Int): Long = index.toLong()

    override fun getView(index: Int, view: View?, viewGroup: ViewGroup?): View {
        var viewItem = view

        if(viewItem == null) {
            viewItem = layoutInflater.inflate(R.layout.item_house, viewGroup, false)
        }

        val houseImage = viewItem?.findViewById<ImageView>(R.id.houseImage)

        houseImage?.setImageResource(houses[index].image)

        return viewItem!!
    }
}