package com.myanmarpeople.trustus.repository.netowrk

import android.content.Context
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkService private constructor(context: Context) {
    val url = "sdlfjks"
    var apiService: ApiService
    var retrofit: Retrofit

    init {
        val interceptor = Interceptor { chain ->
            val request = chain.request().newBuilder()
                ?.addHeader("Content-Type", "application/json")
                ?.addHeader("Accept", "application/json")
                ?.build()
            chain.proceed(request)
        }
        val http: HttpLoggingInterceptor = HttpLoggingInterceptor()
        http.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(http)
            .build()
        val gson = GsonBuilder()
            .setLenient()
            .create()

        retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .baseUrl(url)
            .build()

        apiService = retrofit.create(ApiService::class.java)
    }

    companion object : SingletonHolder<NetworkService, Context>(::NetworkService)
}


open class SingletonHolder<out T, in A>(creator: (A) -> T) {
    private var creator: ((A) -> T)? = creator

    @Volatile
    private var instance: T? = null

    fun setDestory() {
        this.creator = null
        this.instance = null
    }

    fun getInstance(arg: A): T {
        val i = instance
        if (i != null) {
            return i
        }

        return synchronized(this) {
            val i2 = instance
            if (i2 != null) {
                i2
            } else {
                val created = creator!!(arg)
                instance = created
                creator = null
                created
            }
        }
    }
}
