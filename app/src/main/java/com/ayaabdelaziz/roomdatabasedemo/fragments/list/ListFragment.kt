package com.ayaabdelaziz.roomdatabasedemo.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ayaabdelaziz.roomdatabasedemo.R
import com.ayaabdelaziz.roomdatabasedemo.fragments.adapter.ListAdapter
import com.ayaabdelaziz.roomdatabasedemo.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*

class ListFragment : Fragment(), SearchView.OnQueryTextListener {

    private lateinit var userViewModel: UserViewModel
    val listAdapter = ListAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        (activity as AppCompatActivity).supportActionBar?.title = "Home"
        setHasOptionsMenu(true)
        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        val recyclerData = view.listRecyclerView
        recyclerData.adapter = listAdapter
        view.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.addFragment)
        }
        userViewModel.readData.observe(this, {
            listAdapter.addList(it)
        })

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_, menu)
        val search = menu.findItem(R.id.search)
        val searchView = search?.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(this)
        //        object:SearchView.OnQueryTextListener{
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                if (query != null) {
//                    searchIn(query)
//                }
//                return true
//            }
//            override fun onQueryTextChange(query: String?): Boolean {
//                if (query != null) {
//                    searchIn(query)
//                }
//                return true
//            }
//        })

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.delete) {
            deleteAllUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            userViewModel.deleteAllUser()
        }
        builder.setNegativeButton("No") { _, _ ->

        }
        builder.setTitle("Delate All User")
        builder.setMessage("Are you sure you want to delete all user")
        builder.create().show()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
//        Toast.makeText(requireContext(), "nnnnnnininii", Toast.LENGTH_SHORT).show()
//        if (query != null) {
//            searchIn(query)
//        }
        return false
    }

    override fun onQueryTextChange(query: String?): Boolean {
        Toast.makeText(requireContext(), "nnnnnnininii", Toast.LENGTH_SHORT).show()
        if (query != null) {
            searchIn(query)
        }
        return true
    }
    private fun searchIn(query: String) {
        val searchQuery = "?$query?"
        userViewModel.searchInList(searchQuery).observe(this, {
            listAdapter.addList(it)
//            Log.d("myyyyyyyy", "searchIn:+${it.get(11).firstName.toString()} ")
        }
//            { list ->
//            list.let {
//                listAdapter.addList(it)
//            }
//        }
        )}


}