package com.example.esoftwarica.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.esoftwarica.R
import com.example.esoftwarica.dashboard_activity
import com.example.esoftwarica.model.student

class AddStudentFragment : Fragment() {

    private lateinit var etfullname: EditText
    private lateinit var etage: EditText
    private lateinit var etaddress: EditText
    private lateinit var tvGender: TextView
    private lateinit var rbgender: RadioGroup
    private lateinit var btnsave: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_student, container, false)

        // Binding

        etfullname = view.findViewById(R.id.etfullname)
        etaddress = view.findViewById(R.id.etaddress)
        etage = view.findViewById(R.id.etage)
        tvGender = view.findViewById(R.id.tvGender)
        rbgender = view.findViewById(R.id.rbgender)
        btnsave = view.findViewById(R.id.btnsave)

        btnsave.setOnClickListener {
            if (!validator())
                return@setOnClickListener

            val dash = activity as dashboard_activity
            dash.StudentList.add(
                student(
                    etfullname.text.toString().trimEnd(' '),
                    etaddress.text.toString().trimEnd(' '),
                    etage.text.toString().trimEnd(' '),
                    view.findViewById<RadioButton>(rbgender.checkedRadioButtonId)?.text.toString()


                )
            )

            studentFormClear()
            Toast.makeText(context, "Successfully Saved", Toast.LENGTH_LONG).show()

        }
        rbgender.setOnCheckedChangeListener { _,_ ->

            tvGender.setError(null)
        }
        return view
    }


    private fun studentFormClear() {
        etfullname.setText("")
        etaddress.setText("")
        etage.setText("")
        rbgender.clearCheck()
    }

    private fun validator(): Boolean {

        var validation=true
        if (etfullname.text.isEmpty()){
            etfullname.setError("Username must require")
            validation=false
        }

        if (etaddress.text.isEmpty()){
            etaddress.setError("Address must require")
            validation=false
        }
        if (etage.text.isEmpty()){
            etage.setError("Age must require")
            validation=false
        }

        if (rbgender.checkedRadioButtonId== -1){
            tvGender.setError("Gender must require")
            validation=false
        }
        return validation
    }


}