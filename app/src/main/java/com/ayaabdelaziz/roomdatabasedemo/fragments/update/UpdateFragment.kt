package com.ayaabdelaziz.roomdatabasedemo.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ayaabdelaziz.roomdatabasedemo.R
import com.ayaabdelaziz.roomdatabasedemo.model.User
import com.ayaabdelaziz.roomdatabasedemo.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*


class UpdateFragment : Fragment() {
    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var userViewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).supportActionBar?.title = "Update"
        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        view.editTextAgeUpdate.setText(args.currentUser.age.toString())
        view.editTextFirstNameUpdate.setText(args.currentUser.firstName)
        view.editTextSecondNameUpdate.setText(args.currentUser.secondName)
        view.updateBtn.setOnClickListener {
            updateUser()
            Toast.makeText(requireContext(), "updated Successfully", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.listFragment)
        }
        return view
    }

    private fun updateUser() {
        val firstName = editTextFirstNameUpdate.text.toString()
        val secondName = editTextSecondNameUpdate.text.toString()
        val age = editTextAgeUpdate.text.toString()
        if (checkText(firstName,secondName,age.toInt())){
            val user = User(firstName, secondName, age.toInt(),args.currentUser.id)
            userViewModel.updateUser(user)
        }

    }

    fun checkText(firstName: String, secondName: String, age: Int): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(secondName) && TextUtils.isEmpty(
            age.toString()
        ))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId== R.id.delete){
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
        val builder= AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_,_ ->
            userViewModel.deleteUser(args.currentUser)
            Toast.makeText(requireContext(), "${args.currentUser.firstName}Successfully Removed", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.listFragment)
        }
        builder.setNegativeButton("No"){_,_ ->

        }
        builder.setTitle("Delete ${args.currentUser.firstName}?")
        builder.setMessage("Are You sure you to want to delete ${args.currentUser.firstName}")
        builder.create().show()


    }

}