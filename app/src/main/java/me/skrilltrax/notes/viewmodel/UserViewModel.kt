package me.skrilltrax.notes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.skrilltrax.notes.util.Utils.Companion.getUserName

class UserViewModel: ViewModel() {

    private val _name = MutableLiveData<String>()

    private val name: LiveData<String> = _name
}