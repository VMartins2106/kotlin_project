package etec.com.br.victor.mvvm_foodapp.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import etec.com.br.victor.mvvm_foodapp.db.MealDatabase
import etec.com.br.victor.mvvm_foodapp.pojo.Meal
import etec.com.br.victor.mvvm_foodapp.pojo.MealList
import etec.com.br.victor.mvvm_foodapp.retrofit.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealViewModel(
    val mealDatabase: MealDatabase
): ViewModel() {

    private var mealDetailsLiveData = MutableLiveData<Meal>()
    private var favoritesMealsLiveData = mealDatabase.mealDAO().getAllMeals()

    fun getMealDetail(id: String){
        RetrofitInstance.api.getMealDetails(id).enqueue(object : Callback<MealList>{
            override fun onResponse(call: Call<MealList>, response: Response<MealList>) {
                if(response.body() != null){
                    mealDetailsLiveData.value = response.body()!!.meals[0]
                }else{
                    return
                }
            }

            override fun onFailure(call: Call<MealList>, t: Throwable) {
                Log.d("MealActivity", t.message.toString())
            }
        })
    }

    fun observerMealDetailLiveData(): LiveData<Meal>{
        return mealDetailsLiveData
    }

    fun insertMeal(meal:Meal){
        viewModelScope.launch {
            mealDatabase.mealDAO().upsertMeal(meal)
        }
    }

    fun observeFavoritesMealLiveData(): LiveData<List<Meal>>{
        return favoritesMealsLiveData
    }



}