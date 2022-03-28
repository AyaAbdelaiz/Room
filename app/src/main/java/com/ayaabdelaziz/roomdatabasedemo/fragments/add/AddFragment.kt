package com.ayaabdelaziz.roomdatabasedemo.fragments.add

import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ayaabdelaziz.roomdatabasedemo.R
import com.ayaabdelaziz.roomdatabasedemo.model.User
import com.ayaabdelaziz.roomdatabasedemo.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*
import kotlinx.android.synthetic.main.fragment_list.view.*


class AddFragment : Fragment() {

    private lateinit var userViewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_add, container, false)
        (activity as AppCompatActivity).supportActionBar?.title = "Add"
        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        view.addBtn.setOnClickListener {
            addUser()
            Toast.makeText(requireContext(), "Sucessfully Added", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.listFragment)


        }
        return view
    }

    private fun addUser() {
        val firstName = editTextFirstName.text.toString()
        val secondName = editTextSecondName.text.toString()
        val age = editTextAge.text.toString()
        if (checkText(firstName, secondName, age.toInt())) {
            val user = User(firstName, secondName, age.toInt(), 0)
            userViewModel.insertUser(user)
        }


    }

    fun checkText(firstName: String, secondName: String, age: Int): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(secondName) && TextUtils.isEmpty(
            age.toString()
        ))

    }

//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) :Boolean{
////        layoutInflater.inflate(R.menu.menu_,menu)
//        return true
//    }


}