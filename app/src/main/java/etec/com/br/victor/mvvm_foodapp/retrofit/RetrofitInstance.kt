package etec.com.br.victor.mvvm_foodapp.retrofit

import etec.com.br.victor.mvvm_foodapp.pojo.MealList
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitInstance {

    val api:MealApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MealApi::class.java)
    }
    /* funciona da mesma forma:
    lateinit var api:MealApi
    init {

    }*/
}