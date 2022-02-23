package jp.ac.it_college.std.s20001.muscletraining

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(private val record: MutableList<MutableMap<String, String>>)
    : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val menuText: TextView = view.findViewById(R.id.menu)
        val dateText: TextView = view.findViewById(R.id.date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.menuText.text = record[position]["menu"]
        holder.dateText.text = record[position]["date"]
    }

    override fun getItemCount() = record.size
}