package etec.com.br.victor.mvvm_foodapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import etec.com.br.victor.mvvm_foodapp.pojo.Meal

@Dao
interface MealDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertMeal(meal: Meal)

    /* com o onConflict já temos o update
    @Update
    suspend fun updateMeal(meal: Meal)*/

    @Delete
    suspend fun deleteMeal(meal: Meal)

    @Query("SELECT * FROM mealInformation")
    fun getAllMeals(): LiveData<List<Meal>>
}