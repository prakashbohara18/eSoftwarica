package com.example.esoftwarica.adapter

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.esoftwarica.R
import com.example.esoftwarica.model.student

class studentAdapter (

    private  val context: Context,
    private val StudentList:ArrayList<student>,
        ):RecyclerView.Adapter<studentAdapter.StudentViewHolder>() {

    class StudentViewHolder(view:View):RecyclerView.ViewHolder(view) {

        val tvfullname:TextView=view.findViewById(R.id.tvfullname)
        val tvage:TextView=view.findViewById(R.id.tvage)
        val tvaddress:TextView=view.findViewById(R.id.tvaddress)
        val tvGender:TextView=view.findViewById(R.id.tvgender)
        val profileImageView:ImageView=view.findViewById(R.id.studentimage)
        val btndelete:ImageButton=view.findViewById(R.id.Ibtndelete)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.student_layout, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = StudentList[position]
        holder.tvfullname.text = student.fullname
        holder.tvaddress.text = student.address
        holder.tvGender.text = student.gender
        holder.tvage.text = student.age

       Glide.with(context).load("https://images.assetsdelivery.com/compings_v2/blankstock/blankstock1904/blankstock190401447.jpg")
           .into(holder.profileImageView)

        holder.btndelete.setOnClickListener {
            deleteStudentWithAlert(position)
        }
        holder.itemView.setOnClickListener {
            Toast.makeText(context, "Clicked : ${student.fullname}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun deleteStudentWithAlert(position: Int) {
        val dialog = AlertDialog.Builder(context).apply {
            setIcon(android.R.drawable.ic_menu_delete)
            setTitle("Student Delete")
            setMessage("Are you sure you want to delete?")
            setPositiveButton("Delete") { _, _ ->
                StudentList.removeAt(position)
                notifyItemRemoved(position)
                notifyItemRangeChanged(position, StudentList.size)
                Toast.makeText(context, "Successfully Deleted", Toast.LENGTH_SHORT).show()
            }
            setNeutralButton("Cancel") { _,_ ->
                //
            }
            setCancelable(false)
        }.create()
        dialog.setOnShowListener {
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(context, R.color.danger))
        }
        dialog.show()

    }

    override fun getItemCount(): Int {
        return StudentList.size
    }


}