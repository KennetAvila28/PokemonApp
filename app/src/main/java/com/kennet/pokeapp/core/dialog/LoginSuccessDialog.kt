package com.kennet.pokeapp.core.dialog

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.kennet.pokeapp.databinding.DialogLoginSuccessBinding

class LoginSuccessDialog : DialogFragment() {
    private var title: String = ""
    private var description: String = ""
    companion object {
        fun create(title: String = "", description: String = "",): LoginSuccessDialog = LoginSuccessDialog().apply {
            this.title = title
            this.description = description
        }
    }

    override fun onStart() {
        super.onStart()
        val window = dialog?.window ?: return

        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    @SuppressLint("InflateParams")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val binding = DialogLoginSuccessBinding.inflate(requireActivity().layoutInflater)
        binding.btnPositive.setOnClickListener { dismiss() }
        if (!title.isNullOrEmpty()){
            binding.tvTitle.text = title
            binding.tvDescription.text = description
        }

        return AlertDialog.Builder(requireActivity())
            .setView(binding.root)
            .setCancelable(true)
            .create()
    }
}
