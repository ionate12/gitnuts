package com.example.gitnuts.ui.base

import android.widget.Toast
import androidx.fragment.app.Fragment

open class BaseFragment: Fragment() {


    open fun showError(throwable: Throwable) {
        //TODO we can manage error here to display or inject an ErrorManager class to handle error in multiple ways.
        Toast.makeText(requireContext(), throwable.localizedMessage, Toast.LENGTH_SHORT).show()
    }
}