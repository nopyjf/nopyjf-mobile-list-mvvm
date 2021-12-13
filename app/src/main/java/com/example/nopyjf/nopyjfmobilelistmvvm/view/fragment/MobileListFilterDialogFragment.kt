package com.example.nopyjf.nopyjfmobilelistmvvm.view.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.example.nopyjf.nopyjfmobilelistmvvm.R
import com.example.nopyjf.nopyjfmobilelistmvvm.view.constant.MOBILE_LIST_FILTER_CHOICE_EXTRA
import com.example.nopyjf.nopyjfmobilelistmvvm.view.constant.MOBILE_LIST_FILTER_RESULT_EXTRA

class MobileListFilterDialogFragment : DialogFragment() {

    private var _selectedItem: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _selectedItem = arguments?.getInt(MOBILE_LIST_FILTER_CHOICE_EXTRA) ?: -1
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
                .setSingleChoiceItems(
                    R.array.array_mobile_filter_choice,
                    _selectedItem
                ) { _, choice ->
                    _selectedItem = choice
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onCancel(dialog: DialogInterface) {
        findNavController().previousBackStackEntry?.savedStateHandle?.set(
            MOBILE_LIST_FILTER_RESULT_EXTRA,
            _selectedItem
        )
        super.onCancel(dialog)
    }
}