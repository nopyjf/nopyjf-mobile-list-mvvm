package com.example.nopyjf.nopyjfmobilelistmvvm.view.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import com.example.nopyjf.nopyjfmobilelistmvvm.R

class MobileListFilterDialogFragment : DialogFragment() {

    private var selectedItem: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        selectedItem = arguments?.getInt(CHOICE_SELECTED_EXTRA) ?: -1
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
                .setSingleChoiceItems(
                    R.array.array_mobile_filter_choice,
                    selectedItem
                ) { _, choice ->
                    selectedItem = choice
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onCancel(dialog: DialogInterface) {
        setFragmentResult(
            MOBILE_LIST_FILTER_RESULT_EXTRA,
            bundleOf(MOBILE_LIST_FILTER_EXTRA to selectedItem)
        )
        super.onCancel(dialog)
    }

    companion object {
        const val MOBILE_LIST_FILTER_DIALOG_FM_TAG = "MOBILE_LIST_FILTER_DIALOG_FM_TAG"
        const val MOBILE_LIST_FILTER_EXTRA = "MOBILE_LIST_FILTER_EXTRA"
        const val MOBILE_LIST_FILTER_RESULT_EXTRA = "MOBILE_LIST_FILTER_RESULT_EXTRA"
        private const val CHOICE_SELECTED_EXTRA = "CHOICE_SELECTED_EXTRA"

        fun createDialog(choiceSelected: Int): MobileListFilterDialogFragment {
            return MobileListFilterDialogFragment().apply {
                arguments = bundleOf(Pair(CHOICE_SELECTED_EXTRA, choiceSelected))
            }
        }
    }
}