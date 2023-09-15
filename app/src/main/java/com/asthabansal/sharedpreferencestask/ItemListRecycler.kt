package com.asthabansal.sharedpreferencestask

//import android.graphics.Color
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
//import androidx.compose.ui.graphics.Color
import androidx.core.content.ContextCompat
import androidx.core.graphics.toColor
import androidx.recyclerview.widget.RecyclerView
import com.asthabansal.sharedpreferencestask.AppConstants.Companion.color1
import com.asthabansal.sharedpreferencestask.AppConstants.Companion.color2
class ItemListRecycler() : RecyclerView.Adapter<ItemListRecycler.ViewHolder>() {

    var listItems:Int = 0
    var color1 : String ?= null
    var color2 : String?=null

    fun updateValues(listItems: Int, color1: String, color2: String){
        this.color1 = color1
        this.color2 = color2
        this.listItems = listItems
        notifyDataSetChanged()
    }
    class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        var colorView = view.findViewById<TextView>(R.id.firstView)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.firstview,parent ,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 30
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        System.out.println("color1 $color1")
        if(position%2==0)
        {
            holder.colorView.setBackgroundColor(Color.parseColor("#ff0000"))
        }
        else
        {
            holder.colorView.setBackgroundColor(Color.parseColor(color1))
        }

    }
}


