package co.idwall.iddog.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import co.idwall.iddog.R
import co.idwall.iddog.databinding.FragmentLoginBinding
import co.idwall.iddog.extensions.Validation.isValidEmail
import co.idwall.iddog.util.Resource
import co.idwall.iddog.viewmodel.login.LoginViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginButton.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            if (email.isValidEmail()) {
                viewModel.login(email)
            } else {
                binding.emailEditText.text = null
                val message = getString(R.string.email_invalid)
                showSnackbar(view, message)
            }
        }

        observe(view)
    }

    private fun showProgressBar() {
        TODO("Not yet implemented")
    }

    private fun observe(view: View) {
        viewModel.user.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Loading -> showProgressBar()
                is Resource.Error -> handleError(view, response.message)
                is Resource.Success -> handleSuccess()
            }
        }
    }

    private fun handleSuccess() {
        findNavController().navigate(R.id.action_login_to_feed)
    }

    private fun handleError(view: View, message: String?) {
        hideProgressBar()
        message?.let {
            showSnackbar(view, it)
        }
    }

    private fun hideProgressBar() {
        TODO("Not yet implemented")
    }

    private fun showSnackbar(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}