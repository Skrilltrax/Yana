package me.skrilltrax.notes.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.realm.Realm
import me.skrilltrax.notes.PreferenceUtils
import me.skrilltrax.notes.Repository
import me.skrilltrax.notes.model.NoteData

class MainActivityViewModel: ViewModel() {

    private val repo: Repository = Repository(Realm.getDefaultInstance())

    private var _notesList: MutableLiveData<List<NoteData>> = MutableLiveData()
    private var _userName: MutableLiveData<String> = MutableLiveData("User")

    val notesList: LiveData<List<NoteData>>
        get() = _notesList
    val userName: LiveData<String>
        get() = _userName

    init {
        getUserName()
    }

    private fun getUserName() {
        _userName.postValue(PreferenceUtils.getUserName())
    }

    fun changeUserName(name: String) {
        _userName.postValue(name)
    }

    fun getAllNotes() {
        _notesList.postValue(repo.getAllNotes())
    }

    fun saveNote() {
        repo.addNotes(NoteData(0, "hey", "yo", 1))
    }
}