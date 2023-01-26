package com.example.prmanager

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), ExerciseRVAdapter.ExerciseItemClickInterface {
    lateinit var itemsRV: RecyclerView
    lateinit var addFAB: FloatingActionButton
    lateinit var list:List<ExerciseItems>
    lateinit var exerciseRVAdapter: ExerciseRVAdapter
    lateinit var exerciseViewModel: ExerciseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        itemsRV = findViewById(R.id.idRVItems)
        addFAB = findViewById(R.id.idFABAdd)
        list = ArrayList<ExerciseItems>()
        exerciseRVAdapter = ExerciseRVAdapter(list,this)
        itemsRV.layoutManager = LinearLayoutManager(this)
        itemsRV.adapter = exerciseRVAdapter
        val exerciseRepository = ExerciseRepository(ExerciseDatabase(this))
        val factory = ExerciseViewModelFactory(exerciseRepository)
        exerciseViewModel = ViewModelProvider(this,factory).get(ExerciseViewModel::class.java)
        exerciseViewModel.getAllExerciseItems().observe(this, Observer {
            exerciseRVAdapter.list = it
            exerciseRVAdapter.notifyDataSetChanged()
        })

        addFAB.setOnClickListener{
            openDialog()
        }
    }

    private fun openDialog(){
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.exercise_add_dialog)
        val cancelBtn = dialog.findViewById<Button>(R.id.idBtnCancel)
        val addBtn = dialog.findViewById<Button>(R.id.idBtnAdd)
        val itemNameEdit = dialog.findViewById<EditText>(R.id.idEditItemName)
        val itemRepsEdit = dialog.findViewById<EditText>(R.id.idEditNumberReps)
        val itemPoundsEdit = dialog.findViewById<EditText>(R.id.idEditNumberPounds)
        cancelBtn.setOnClickListener{
            dialog.dismiss()
        }
        addBtn.setOnClickListener{
            val itemName : String = itemNameEdit.text.toString()
            val itemReps : String = itemRepsEdit.text.toString()
            val itemPounds : String = itemPoundsEdit.text.toString()
            val nbReps : Int = itemReps.toInt()
            val nbPounds : Float = itemPounds.toFloat()
            if(itemName.isNotEmpty() && itemReps.isNotEmpty() && itemPounds.isNotEmpty()){
                val items = ExerciseItems(itemName,nbReps,nbPounds)
                exerciseViewModel.insert(items)
                Toast.makeText(applicationContext, "Exercise Added", Toast.LENGTH_SHORT).show()
                exerciseRVAdapter.notifyDataSetChanged()
                dialog.dismiss()
            }
            else{
                Toast.makeText(applicationContext,"Missing Information", Toast.LENGTH_SHORT).show()
            }
        }
        dialog.show()
    }

    override fun onItemClick(exerciseItems: ExerciseItems) {
        exerciseViewModel.delete(exerciseItems)
        exerciseRVAdapter.notifyDataSetChanged()
        Toast.makeText(applicationContext,"Item Deleted..", Toast.LENGTH_SHORT).show()
    }
}