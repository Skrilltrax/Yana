package me.skrilltrax.notes.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.realm.Realm
import me.skrilltrax.notes.Repository
import me.skrilltrax.notes.model.NoteData

class MainActivityViewModel: ViewModel() {

    val repo: Repository = Repository(Realm.getDefaultInstance())

    private lateinit var _notesList: MutableLiveData<List<NoteData>>

    val notesList: LiveData<List<NoteData>>
        get() = _notesList

    fun getAllNotes() {
        _notesList.postValue(repo.getAllNotes())
    }
}