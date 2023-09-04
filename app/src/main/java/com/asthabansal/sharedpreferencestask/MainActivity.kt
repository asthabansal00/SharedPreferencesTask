package com.asthabansal.sharedpreferencestask

import android.app.Dialog
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.github.dhaval2404.colorpicker.ColorPickerDialog
import com.github.dhaval2404.colorpicker.model.ColorShape
import com.asthabansal.sharedpreferencestask.databinding.ActivityMainBinding
import com.asthabansal.sharedpreferencestask.databinding.CustomDialogBinding

class MainActivity : AppCompatActivity() {

    //binding
    //declaration
    var binding: ActivityMainBinding? = null
    var namelist = arrayListOf<colorclass>()
    var adapter: ItemListRecycler?=null
   var listItems:Int?=null
    var layoutManager:LinearLayoutManager?=null

    //shared preferences and editor to save data in custom dialog
    //declaration
//    var sharedPreferences:SharedPreferences?=null
//    var editor:SharedPreferences.Editor?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //binding initialization
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        //adapter = ItemListRecycler(namelist)

        binding?.recycler?.adapter = ItemListRecycler(namelist,AppConstants.listCount.toInt())

        layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        //  shared preferences initialization
        Singleton.sharedPreferences?.initPrefs(this)

        /*binding?.btnclear?.setOnClickListener {
        }*/

//        sharedPreferences = getSharedPreferences(resources.getString(R.string.app_name),
//            MODE_PRIVATE)
//        //editor initialization
//        editor =sharedPreferences?.edit()
        binding?.btnclear?.setOnClickListener {
            namelist.clear()
            adapter?.notifyDataSetChanged()
        }

        binding?.fab?.setOnClickListener {
            var dialog = Dialog(this)
            var dialogbinding = CustomDialogBinding.inflate(layoutInflater)

            //used to set the size of custom dialog.
            dialog.window?.setLayout(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            dialog.setContentView(dialogbinding.root)
            dialogbinding.etColor1.setText( Singleton.sharedPreferences?.getColor(AppConstants.color1))
            dialogbinding.etColor2.setText( Singleton.sharedPreferences?.getColor(AppConstants.color2))
            dialogbinding.etListCount.setText( (Singleton.sharedPreferences?.getListCount(AppConstants.listCount)).toString())
            dialog.show()

            dialogbinding.etColor1.setOnClickListener {
                ColorPickerDialog
                    .Builder(this)                        // Pass Activity Instance
                    .setTitle("Pick Theme")            // Default "Choose Color"
                    .setColorShape(ColorShape.SQAURE)   // Default ColorShape.CIRCLE
                    .setDefaultColor(R.color.white)     // Pass Default Color
                    .setColorListener { color, colorHex ->
                        dialogbinding.etColor1.setText(colorHex.toString())
                        // Handle Color Selection
                        System.out.println("color $color colorHex $colorHex")
                    }
                    .show()

                dialogbinding.etColor2.setOnClickListener {
                    ColorPickerDialog
                        .Builder(this)                        // Pass Activity Instance
                        .setTitle("Pick Theme")            // Default "Choose Color"
                        .setColorShape(ColorShape.SQAURE)   // Default ColorShape.CIRCLE
                        .setDefaultColor(R.color.white)     // Pass Default Color
                        .setColorListener { color, colorHex ->
                            dialogbinding.etColor2.setText(colorHex.toString())
                            // Handle Color Selection
                            System.out.println("color $color colorHex $colorHex")
                        }
                        .show()

                    /*dialogbinding.etListCount.setOnClickListener {

                    }*/

                    System.out.println("Hello "+ Singleton.sharedPreferences?.getColor(AppConstants.color1))

                    dialogbinding.btnsave.setOnClickListener {
                        Singleton.sharedPreferences?.saveColor(AppConstants.color1,dialogbinding.etColor1.text.toString())
                        Singleton.sharedPreferences?.saveColor(AppConstants.color2,dialogbinding.etColor2.text.toString())
                        Singleton.sharedPreferences?.saveListCount(AppConstants.listCount.toString(),dialogbinding.etListCount.text.toString().toInt())
                        listItems = AppConstants.listCount.toString().toInt()
                        namelist.add(colorclass(Singleton?.sharedPreferences?.getColor(AppConstants.color1).toString().toColorInt(),
                                                 Singleton?.sharedPreferences?.getColor(AppConstants.color2).toString().toColorInt()))
                        adapter?.notifyDataSetChanged()

                        dialog.show()
                        dialog.dismiss()
                    }

                }

            }

        }

    }

}

