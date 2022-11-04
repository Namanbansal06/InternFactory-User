package com.example.internfactory.Activities.Auth

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.internfactory.R
import com.example.internfactory.modules.LoginResponse
import com.example.internfactory.modules.User
import com.example.internfactory.modules.UserDetails
import com.example.internfactory.server.RetrofitApi
import com.example.internfactory.server.ServiceBuilder
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResetPassword_Fragment : Fragment() {

    lateinit var btn:Button

    private lateinit var pass_input: TextInputEditText
    private lateinit var pass : TextInputLayout
    private lateinit var conf_pass : TextInputLayout
    private lateinit var conf_pass_inp : TextInputEditText
    private lateinit var reset_btn : Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragement_reset_password, container, false)

        val fm : FragmentManager = parentFragmentManager
        val ft : FragmentTransaction = fm.beginTransaction()

        pass_input = view.findViewById(R.id.password_form_inp)
        conf_pass_inp= view.findViewById(R.id.conf_password_form_inp)
        btn= view.findViewById(R.id.reset_btn)

//        btn.setOnClickListener {
//            val user = User(null, null, emailin.text.toString(), passwordin.text.toString())
//            val retrofitAPI = ServiceBuilder.buildService(RetrofitApi::class.java)
//            val call = retrofitAPI.login(user)
//
//            call.enqueue(object : Callback<LoginResponse> {
//                override fun onResponse(
//                    call: Call<LoginResponse>,
//                    response: Response<LoginResponse>
//                ) {
//                    if (response.code() == 200) {
//                        Toast.makeText(
//                            view?.context,
//                            response.body()?.toString(),
//                            Toast.LENGTH_SHORT
//                        ).show()
//                        runBlocking { view?.let { UserDetails(it.context).storeUserData(response.body()?.authToken.toString()) } }
//                        Log.i("Naman", response.code().toString().toString())
//                    } else {
//                        Toast.makeText(
//                            view?.context,
//                            response.code().toString(),
//                            Toast.LENGTH_SHORT
//                        ).show()
//
//                    }
//                }
//
//                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
//                    Toast.makeText(
//                        view?.context,
//                        "Please check your internet connection",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                    Log.i("Naman", "Please check your internet connection")
//                }
//            })
//        }

        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pass_input = requireView().findViewById(R.id.password_form_inp)
        pass = requireView().findViewById(R.id.password_inp)
        conf_pass= requireView().findViewById(R.id.conf_pass)
        conf_pass_inp= requireView().findViewById(R.id.conf_password_form_inp)
        reset_btn= requireView().findViewById(R.id.reset_btn)

        pass_input.addTextChangedListener {
            pass.helperText = validPass()
            reset_btn.isEnabled = (pass.helperText == null) and (conf_pass.helperText == null)
        }

        conf_pass_inp.addTextChangedListener {
            if(conf_pass_inp.text.toString() == pass_input.text.toString()){
                conf_pass.helperText = null
                reset_btn.isEnabled = true
            }
            else{
                conf_pass.helperText = "Password and Confirm Password Must be Same"
                reset_btn.isEnabled = false
            }
        }
    }

    private fun validPass(): String? {
        val pass_txt = pass_input.text.toString()
        if(pass_txt.length<8){
            return "Minimum 8 characters Required"
        }
        if(!pass_txt.contains(Regex("[A-Z]"))){
            return "At least 1 UpperCase Alphabet Required"
        }
        if(!pass_txt.contains(Regex("[a-z]"))){
            return "At least 1 LowerCase Alphabet Required"
        }
        if(!pass_txt.contains(Regex("[@#\$%^&*+=]"))){
            return "At least 1  Special Character Required"
        }
        return null
    }


}