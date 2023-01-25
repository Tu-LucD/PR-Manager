package com.example.prmanager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView

class ExerciseRVAdapter (
    var list: List<ExerciseItems>,
    val exerciseItemClickInterface: ExerciseItemClickInterface
    ): RecyclerView.Adapter<ExerciseRVAdapter.ExerciseViewHolder>(){

    inner class ExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val nameTV = itemView.findViewById<TextView>(R.id.idTVItemName)
        val repsTV = itemView.findViewById<TextView>(R.id.idTVNbReps)
        val poundsTV = itemView.findViewById<TextView>(R.id.idTVNbPounds)
        val deleteIV = itemView.findViewById<ImageView>(R.id.idTVDelete)
    }
    interface ExerciseItemClickInterface{
        fun onItemClick(exerciseItems: ExerciseItems) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.exercise_rv_item,parent,false)
        return ExerciseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        holder.nameTV.text = list.get(position).itemName
        holder.repsTV.text = list.get(position).numberReps.toString()
        holder.poundsTV.text = list.get(position).nbPounds.toString()
        holder.deleteIV.setOnClickListener{
            exerciseItemClickInterface.onItemClick(list.get(position))
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}