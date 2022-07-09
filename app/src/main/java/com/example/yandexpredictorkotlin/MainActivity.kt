package com.example.yandexpredictorkotlin

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.yandexpredictorkotlin.databinding.ActivityMainBinding
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var ourWord: NextWord
    private lateinit var nextWordApi: NextWordApi
    lateinit var binding: ActivityMainBinding
    val BASE_URL = "https://predictor.yandex.net/"
    val KEY = "pdct.1.1.20220331T130719Z.0938ca6016e67151.099c8db6c5b988647bd11cfd18c42dc9f13cd802"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        val gson = GsonBuilder().create()
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        nextWordApi = retrofit.create(NextWordApi::class.java)

        binding.editText.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                doSearch(binding.editText.text.toString())
            }

        });

    }

    fun doSearch(text: String){
        nextWordApi.getDataAnekdot(KEY, text, "en",5)
            ?.enqueue(object : Callback<NextWord?> {

                override fun onResponse(call: Call<NextWord?>, response: Response<NextWord?>) {
                    if (response.code() == 200) {
                        if (response.body()!=null){
                        ourWord = response.body()!!
                            var i = 0
                            var string = ""
                            while (i < 5 ){
                            string += response.body()!!.text[i]
                            string += " "
                            i++}


                        binding.textView.text = string
                        }

                        Log.d("AAAA", Integer.toString(response.code()))
                    }

                    Log.d("fff", Integer.toString(response.code()))
                }

                override fun onFailure(call: Call<NextWord?>, t: Throwable) {
                    Log.d("RRRR", t.message!!)
                }
            })
    }
}