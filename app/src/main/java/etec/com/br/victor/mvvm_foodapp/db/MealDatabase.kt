package etec.com.br.victor.mvvm_foodapp.db

import android.content.Context
import androidx.room.*
import etec.com.br.victor.mvvm_foodapp.pojo.Meal

@Database(entities = [Meal::class], version = 1)
@TypeConverters(MealTypeConverter::class)
abstract class MealDatabase : RoomDatabase(){

    abstract fun mealDAO(): MealDAO

    companion object{
        @Volatile
        var INSTANCE: MealDatabase? = null

        @Synchronized
        fun getInstance(context: Context):MealDatabase{
            if(INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context,
                    MealDatabase::class.java,
                    "meal.db"
                ).fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE as MealDatabase
        }
    }

}