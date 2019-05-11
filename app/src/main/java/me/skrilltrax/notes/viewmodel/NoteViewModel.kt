package me.skrilltrax.notes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NoteViewModel : ViewModel() {

    private val _noteTitle = MutableLiveData<String>()
    private val _noteDetail = MutableLiveData<String>()

    private val noteTitle: LiveData<String> = _noteTitle
    private val noteDetail: LiveData<String> = _noteDetail
}