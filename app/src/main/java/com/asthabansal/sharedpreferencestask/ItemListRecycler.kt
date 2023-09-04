package com.asthabansal.sharedpreferencestask

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.graphics.toColor
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.NameList

class ItemListRecycler ( var nameList: ArrayList<colorclass>,var listItems:Int ): RecyclerView.Adapter<ItemListRecycler.ViewHolder>() {

    class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        var FirstView = view.findViewById<View>(R.id.firstView)
        var SecondView = view.findViewById<View>(R.id.secondView)

        //var tvhello = view.findViewById<TextView>(R.id.tvhello)
    }
    /*override fun getItemId(position: Int): Long {
        if(position % 2 == 0)
        {
            position.toColor().(AppConstants.color1.toString())
        }
        else
        {
            position.toColor().(AppConstants.color2).toString()
        }
        return super.getItemId(position)

    }*/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerdesign,parent ,false)
        return ViewHolder(view)
        //getItemId(position = 1)
    }

    override fun getItemCount(): Int {
        return listItems
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.FirstView.setBackgroundColor(nameList[position].firstViewColor.toString().toInt())
        holder.SecondView.setBackgroundColor(nameList[position].firstViewColor.toString().toInt())

    }
}


