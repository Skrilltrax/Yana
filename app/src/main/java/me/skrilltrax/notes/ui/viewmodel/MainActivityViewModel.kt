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

    private lateinit var _notesList: MutableLiveData<List<NoteData>>
    private lateinit var _userName: MutableLiveData<String>

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

    fun getAllNotes() {
        _notesList.postValue(repo.getAllNotes())
    }
}