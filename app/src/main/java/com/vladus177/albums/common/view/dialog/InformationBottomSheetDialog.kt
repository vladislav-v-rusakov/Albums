package com.vladus177.albums.common.view.dialog

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.vladus177.albums.R
import com.vladus177.albums.common.extension.setSingleOnClickListener
import com.vladus177.albums.databinding.DialogBottomSheetBinding

class InformationBottomSheetDialog : BottomSheetDialogFragment() {

    private var title: String = ""
    private var message: String = ""
    private var positiveButton: String = ""
    private var negativeButton: String = ""

    private val liveData: MutableLiveData<Boolean> = MutableLiveData()

    fun liveData(): LiveData<Boolean> = liveData

    override fun getTheme(): Int = R.style.BottomSheetDialogTheme

    private var _binding: DialogBottomSheetBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        arguments?.let {
            title = it.getString(KEY_TITLE).orEmpty()
            message = it.getString(KEY_MESSAGE).orEmpty()
            positiveButton = it.getString(KEY_BUTTON_POSITIVE).orEmpty()
            negativeButton = it.getString(KEY_BUTTON_NEGATIVE).orEmpty()
        }
    }

    private fun initView() {
        binding.apply {
            tvTitle.text = title
            tvMessage.text = message
            btnOk.text = positiveButton
            btnCancel.text = negativeButton
            btnCancel.setSingleOnClickListener { cancelAndClose()}
            btnOk.setSingleOnClickListener { confirmAndClose() }
        }
    }

    private fun confirmAndClose() {
        liveData.postValue(true)
        this@InformationBottomSheetDialog.dismiss()
    }

    private fun cancelAndClose() {
        liveData.postValue(false)
        this@InformationBottomSheetDialog.dismiss()
    }

    companion object {

        fun newInstance(title: String, message: String, positiveButton: String = "", negativeButton: String = "" ): InformationBottomSheetDialog =
            InformationBottomSheetDialog().apply {
                arguments = Bundle().apply {
                    putString(KEY_TITLE, title)
                    putString(KEY_MESSAGE, message)
                    putString(KEY_BUTTON_POSITIVE, positiveButton)
                    putString(KEY_BUTTON_NEGATIVE, negativeButton)
                }
            }

        private const val KEY_TITLE = "title"
        private const val KEY_MESSAGE = "message"
        private const val KEY_BUTTON_POSITIVE = "button_positive"
        private const val KEY_BUTTON_NEGATIVE = "button_negative"
    }

}