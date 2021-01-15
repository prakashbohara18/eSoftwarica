package com.example.esoftwarica

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.esoftwarica.fragments.AboutUsFragments
import com.example.esoftwarica.fragments.AddStudentFragment
import com.example.esoftwarica.fragments.HomeFragment
import com.example.esoftwarica.model.student
import com.google.android.material.bottomnavigation.BottomNavigationView

class dashboard_activity : AppCompatActivity() {

    private lateinit var fragContainer: LinearLayout
    private lateinit var tabLayout: BottomNavigationView

    val StudentList= arrayListOf<student>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard_activity)

        //binding

        tabLayout = findViewById(R.id.tabLayout)
        fragContainer = findViewById(R.id.fragcontainer)

        popularStudents()

        if (savedInstanceState == null) {
            loadPage(HomeFragment())
        }

        tabLayout.setOnNavigationItemReselectedListener {

            when(it.itemId) {

                R.id.homePage -> loadPage(HomeFragment())
                R.id.aboutUs -> loadPage(AboutUsFragments())
                R.id.addStudent -> loadPage(AddStudentFragment())
                else -> false

            }
        }

    }

    private fun loadPage(fragment: Fragment):Boolean {
        if (fragment is HomeFragment) {
            fragment.arguments = Bundle().apply {
                putParcelableArrayList("listStudent", StudentList)
            }
        }
        supportFragmentManager.beginTransaction().apply {
            replace(fragContainer.id, fragment)
//            addToBackStack(null)
            commit()
        }
        return true


    }

    private fun popularStudents() {
        StudentList.add(student("Laxman Kunwar","Old Baneshwor","23","Male"))
        StudentList.add(student("Deepika Kunwar","Patan","20","Female"))
        StudentList.add(student("Deepak Dhami","Abu Dhabi","24","Male"))
        StudentList.add(student("Nabina Oli","Kritipur","22","Female"))
    }

    override fun onBackPressed() {
        val dialog = AlertDialog.Builder(this).apply {
            setIcon(android.R.drawable.ic_dialog_alert)
            setTitle("Closing App")
            setMessage("Are you sure you want to exit?")
            setPositiveButton("Yes") { _,_ ->
                finish()
            }
            setNegativeButton("No", null)
            setCancelable(false)
        }.create()
        dialog.setOnShowListener {
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(this, R.color.muted))
        }
        dialog.show()
    }
}