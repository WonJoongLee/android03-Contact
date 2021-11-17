package com.ivyclub.contact.ui.main.settings.security

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.ivyclub.contact.R
import com.ivyclub.contact.databinding.FragmentPasswordBinding
import com.ivyclub.contact.ui.main.MainActivity
import com.ivyclub.contact.util.BaseFragment
import com.ivyclub.contact.util.PasswordViewType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PasswordFragment :
    BaseFragment<FragmentPasswordBinding>(R.layout.fragment_password) {

    private val viewModel: PasswordViewModel by viewModels()
    private val args: PasswordFragmentArgs by navArgs()
    private val passwordEditTextList by lazy {
        with(binding) {
            listOf(etPassword1, etPassword2, etPassword3, etPassword4)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        initPasswordViewType()
        initNumberClickListener()
        initCancelButtonClickListener()
        initMoveFragmentObserver()
        observeFocusedEditTextIndex()
    }

    private fun initPasswordViewType() {
        when (args.passwordViewType) {
            PasswordViewType.SET_PASSWORD -> {
                viewModel.initPasswordViewType(args.passwordViewType)
                viewModel.moveToReconfirmPassword.observe(viewLifecycleOwner) { password ->
                    findNavController().navigate(
                        PasswordFragmentDirections.actionSetPasswordFragmentSelf(
                            PasswordViewType.RECONFIRM_PASSWORD,
                            password
                        )
                    )
                }
            }
            PasswordViewType.RECONFIRM_PASSWORD -> {
                viewModel.initPasswordViewType(args.passwordViewType, args.password)
                binding.tvPassword.text = getString(R.string.password_reconfirm_message)

            }
            PasswordViewType.APP_CONFIRM_PASSWORD -> {
                viewModel.initPasswordViewType(args.passwordViewType)
                viewModel.finishConfirmPassword.observe(viewLifecycleOwner) {
                    val intent = Intent(context, MainActivity::class.java)
                    activity?.setResult(RESULT_OK, intent)
                    activity?.finish()
                }
            }
        }
    }

    private fun initNumberClickListener() {
        val numberButtonList = with(binding) {
            listOf(btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9)
        }

        numberButtonList.forEachIndexed { number, button ->
            button.setOnClickListener {
                viewModel.moveFocusFront(number.toString())
            }
        }
    }

    private fun initCancelButtonClickListener() {
        binding.btnCancel.setOnClickListener {
            viewModel.moveFocusBack()
        }
    }

    private fun observeFocusedEditTextIndex() {
        viewModel.focusedEditTextIndex.observe(viewLifecycleOwner) {
            passwordEditTextList[it - 1].requestFocus()
        }
    }

    private fun initMoveFragmentObserver() {
        viewModel.moveToSetPassword.observe(viewLifecycleOwner) {
            findNavController().navigate(
                PasswordFragmentDirections.actionSetPasswordFragmentSelf(
                    PasswordViewType.SET_PASSWORD
                )
            )
            Snackbar.make(binding.root, getString(R.string.password_reconfirm_fail), Snackbar.LENGTH_SHORT)
                .show()
        }
        viewModel.moveToPreviousFragment.observe(viewLifecycleOwner) {
            findNavController().popBackStack()
            Snackbar.make(binding.root, getString(R.string.password_set_success), Snackbar.LENGTH_SHORT)
                .show()
        }
    }
}