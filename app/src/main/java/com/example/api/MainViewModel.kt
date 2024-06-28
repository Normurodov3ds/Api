package com.example.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.api.model.UIModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request

class MainViewModel : ViewModel() {

    private val _liveData = MutableLiveData<UIState>()
    val liveData: LiveData<UIState> = _liveData

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

    fun getAllModels(from: Int, to: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val list = mutableListOf<String>()
            for (a in from..<to) {
                val okHttp = OkHttpClient()
                val request = Request.Builder()
                    .url("https://http.cat/$a")
                    .build()
                okHttp.newCall(request).execute().let {
                    it.body?.let { its ->
                        list.add(its.string())

                        CoroutineScope(Dispatchers.Main).launch {
                            println(its.string())
                            _liveData.postValue(UIState.Success(list.map { uri ->
                                UIModel(uri = uri)
                            }))
                        }
                    }
                }

            }
        }
    }

}


sealed class UIState {
    data class Success(val data: List<UIModel>) : UIState()
    data class Error(val error: Throwable) : UIState()
    data object Loading : UIState()
}
