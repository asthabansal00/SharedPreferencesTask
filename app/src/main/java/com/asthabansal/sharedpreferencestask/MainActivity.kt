package com.asthabansal.sharedpreferencestask

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.dhaval2404.colorpicker.ColorPickerDialog
import com.github.dhaval2404.colorpicker.model.ColorShape
import com.asthabansal.sharedpreferencestask.databinding.ActivityMainBinding
import com.asthabansal.sharedpreferencestask.databinding.CustomDialogBinding

class MainActivity : AppCompatActivity() {

    var binding: ActivityMainBinding? = null
    var adapter: ItemListRecycler = ItemListRecycler()
    var listItems = 0
    var color1 : String = "#fffff"
    var color2 : String = "#fffff"
    var layoutManager :LinearLayoutManager?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        binding?.recycler?.layoutManager = layoutManager
        binding?.recycler?.adapter = adapter//,AppConstants.listCount.toString().toInt())

        //  shared preferences initialization
        Singleton.sharedPreferences?.initPrefs(this)

        updateValues()

        binding?.btnclear?.setOnClickListener {
            adapter?.notifyDataSetChanged()
        }

        binding?.fab?.setOnClickListener {
            var dialog = Dialog(this)
            var dialogbinding = CustomDialogBinding.inflate(layoutInflater)

            //used to set the size of custom dialog.
            dialog.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            dialog.setContentView(dialogbinding.root)
            dialogbinding.etColor1.setText( Singleton.sharedPreferences?.getColor(AppConstants.color1))
            dialogbinding.etColor2.setText( Singleton.sharedPreferences?.getColor(AppConstants.color2))
            dialogbinding.etListCount.setText( (Singleton.sharedPreferences?.getListCount(AppConstants.listCount)).toString())
            System.out.println("color1 "+color1)
            System.out.println("color2"+color2)

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
            }
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

                    //to check whether data saves or not
                    System.out.println("Hello " + Singleton.sharedPreferences?.getColor(AppConstants.color1))
                }
                    dialogbinding.btnsave.setOnClickListener {
                        Singleton.sharedPreferences?.saveColor(AppConstants.color1,dialogbinding.etColor1.text.toString())
                        Singleton.sharedPreferences?.saveColor(AppConstants.color2,dialogbinding.etColor2.text.toString())
                        Singleton.sharedPreferences?.saveListCount(AppConstants.listCount,dialogbinding.etListCount.text.toString().toInt())

                        updateValues()

                        dialog.dismiss()
                    }

        }

    }
    //error = didnt store the value of color in the variables
    //didnt update the value of list count and colors due to which adapter wasnt able to take and set them
    fun updateValues(){
        color1 =  Singleton.sharedPreferences?.getColor(AppConstants.color1) ?:"#ffffff"
        color2 =  Singleton.sharedPreferences?.getColor(AppConstants.color2) ?:"#ffffff"
        listItems =  Singleton.sharedPreferences?.getListCount(AppConstants.listCount) ?: 0
        adapter.updateValues(listItems, color1, color2)
    }

}

