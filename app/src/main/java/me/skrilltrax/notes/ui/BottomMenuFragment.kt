package me.skrilltrax.notes.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.skrilltrax.notes.databinding.FragmentBottomSheetBinding
import me.skrilltrax.notes.databinding.ItemBottomSheetHeaderBindingImpl
import me.skrilltrax.notes.helpers.AccountHelper
import me.skrilltrax.notes.helpers.DriveService

class BottomMenuFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (AccountHelper.firebaseUser != null) {
            binding.user = AccountHelper.firebaseUser
            binding.bottomSheetHeader.imageSync.setOnClickListener {
                CoroutineScope(Dispatchers.IO).launch {
                    DriveService.createFile("Realm DB")
                    val files = DriveService.getFiles()
                    withContext(Dispatchers.Main) {
                        Log.d("Bottom", "lalalala")
                        files?.forEach {
                            it.trashed = true
                            if (it.contains("Realm")) {
                                Log.d("Data", it.toString())
                            }
                            Log.d("CScope", it.name)
                        }
                    }
                }
            }
        }
    }
}