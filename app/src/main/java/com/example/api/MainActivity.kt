package com.example.api

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.api.databinding.ActivityMainBinding
import com.example.api.model.UIModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by
    lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setup()
    }

    private var count = 0
    private val adapter by lazy {
        val action: (url: String) -> Unit = {
            val dialog = Dialog(this@MainActivity)
            dialog.setContentView(R.layout.item)
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            val diestance = this@MainActivity.resources.displayMetrics.density
            dialog.window?.setLayout((diestance * 350).toInt(), (diestance * 450).toInt())
            dialog.findViewById<ImageView>(R.id.tv_image).load(it)
            dialog.show()
        }
        val loading: () -> Unit = {
            // it is very bad :(
        }
        Adapter(action, loading)
    }
    val array = arrayOf(
        100,
        101,
        102,
        103,
        200,
        201,
        202,
        203,
        204,
        205,
        206,
        207,
        208,
        226,
        300,
        301,
        302,
        303,
        304,
        305,
        306,
        307,
        308,
        400,
        401,
        402,
        403,
        404,
        405,
        406,
        407,
        408,
        409,
        410,
        411,
        412,
        413,
        414,
        415,
        416,
        417,
        418,
        421,
        422,
        423,
        424,
        425,
        426,
        428,
        429,
        431,
        451,
        500,
        501,
        502,
        503,
        504,
        505,
        506,
        507,
        508,
        510,
        511,
    )

    private fun setup() = with(binding) {
        rv.adapter = adapter
        val list = mutableListOf<UIModel>()
        for (a in array) {
            list.add(UIModel(uri = "https://http.cat/$a"))
        }
        adapter.submitList(list)

//        viewModel.getAllModels(count, 10)
//        count += 10;
//        rv.adapter = adapter
//        viewModel.liveData.observe(this@MainActivity) {
//            when (it) {
//                is UIState.Error -> {
//                    println(it.error)
//                    progress.isVisible = false
//                }
//
//                UIState.Loading -> {
//                    progress.isVisible = true
//                }
//
//                is UIState.Success -> {
//                    progress.isVisible = false
//                    adapter.submitList(it.data.toList())
//                }
//            }
//        }
    }
}