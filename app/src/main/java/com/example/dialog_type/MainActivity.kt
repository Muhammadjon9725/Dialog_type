package com.example.dialog_type

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.CalendarView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import com.example.dialog_type.databinding.ActivityMainBinding
import com.example.dialog_type.databinding.CustomItemBinding
import com.example.dialog_type.databinding.FragmentDialogBinding
import com.example.dialog_type.databinding.ItemButtomSheetDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import java.util.Calendar
import kotlin.math.log
import kotlin.math.min

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private var selectedHour = 0
    private var selectedMinutes = 0

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.apply {

            alertButtom.setOnClickListener {
                val dialog = AlertDialog.Builder(this@MainActivity)
                    .setTitle("Ogohlantirish!")
                    .setMessage("Bu maʻlumotlar qayta tiklanmaydi!!")
                    .setPositiveButton("Ha",object : DialogInterface.OnClickListener{
                        override fun onClick(p0: DialogInterface?, p1: Int) {
                            Toast.makeText(this@MainActivity, "Siz Ha tugmasini bosdingiz", Toast.LENGTH_SHORT).show()
                        }
                    })
                    .setNegativeButton("Yoʻq",object : DialogInterface.OnClickListener{
                        override fun onClick(p0: DialogInterface?, p1: Int) {
                            Toast.makeText(this@MainActivity, "Siz yoʻq tugmasini bosdingiz", Toast.LENGTH_SHORT).show()
                        }
                    })
                    .setNeutralButton("Qaytish",object : DialogInterface.OnClickListener{
                        override fun onClick(p0: DialogInterface?, p1: Int) {
                            Toast.makeText(this@MainActivity, "Siz qaytish tugmasini bosdingiz", Toast.LENGTH_SHORT).show()
                        }
                    })
                    .create()
                    .show()

            }

            customButtom.setOnClickListener {
                val dialogViewBinding = CustomItemBinding.inflate(layoutInflater,null,false)
                val dialog  = AlertDialog.Builder(this@MainActivity)
                    .setView(dialogViewBinding.root)
                    .setPositiveButton("Sign in") {_,_->
                        Log.d(
                            "dialog",
                            "${dialogViewBinding.edtEmailDialog.text} ${dialogViewBinding.edtPasswordDialog.text}")
                    }
                    .setNegativeButton("Cancel"){_,_->}
                    .create()
                    .show()
            }

            fragmentButtom.setOnClickListener {
                val dialogFragment = Dialog_Fragment()
                dialogFragment.show(supportFragmentManager.beginTransaction(),"key")

                
            }

            val calendar = Calendar.getInstance()
            datapickerButtom.setOnClickListener {
                val dataPickerDialog = DatePickerDialog(this@MainActivity,0,
                    DatePickerDialog.OnDateSetListener { _, year, month, day ->
                        Toast.makeText(this@MainActivity, "$day.$month.$year", Toast.LENGTH_SHORT).show()
                },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH))
                dataPickerDialog.show()


            }
            timepickerButtom.setOnClickListener {
                selectedMinutes = calendar.get(Calendar.MINUTE)
                selectedHour = calendar.get(Calendar.HOUR)
                val timePickerDialog = TimePickerDialog(this@MainActivity,
                    TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                        Toast.makeText(this@MainActivity, "$hour : ${minute}", Toast.LENGTH_SHORT).show()
                    },
                    selectedHour,
                    selectedMinutes,
                    true
                )
                timePickerDialog.show()
            }
            buttomsheetButtom.setOnClickListener {
                val bottomSheetDialog = BottomSheetDialog(this@MainActivity)
                bottomSheetDialog.setContentView(layoutInflater.inflate(R.layout.item_buttom_sheet_dialog, null, false))
                val itemButtonButtomSheetDialogBinding = ItemButtomSheetDialogBinding.inflate(layoutInflater)
                itemButtonButtomSheetDialogBinding.btnClose.setOnClickListener {
                    Toast.makeText(this@MainActivity, "Bosildi", Toast.LENGTH_SHORT).show()
                }
                bottomSheetDialog.show()

            }

            snackbarButtom.setOnClickListener {
                val snackbar = Snackbar.make(it, "Salom snackbar", Snackbar.LENGTH_LONG)

                snackbar.setAction("Click", object : View.OnClickListener {
                    override fun onClick(v: View?) {
                        Toast.makeText(this@MainActivity, "Click", Toast.LENGTH_SHORT).show()
                    }
                })
                snackbar.show()
            }


        }
    }

    override fun onBackPressed() {
            val dialog = AlertDialog.Builder(this@MainActivity)
                .setTitle("Ogohlantirish!")
                .setMessage("Haqiqatdan ham ilovadan chiqmoqchimisz!!")
                .setPositiveButton("Ha",object : DialogInterface.OnClickListener{
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        finish()
                    }
                })
                .setNegativeButton("Yoʻq",object : DialogInterface.OnClickListener{
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                    }
                })
                .create()
                .show()


    }
}