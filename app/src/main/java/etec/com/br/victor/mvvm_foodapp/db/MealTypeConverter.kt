package etec.com.br.victor.mvvm_foodapp.db

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.util.jar.Attributes

@TypeConverters
class MealTypeConverter {

    @TypeConverter
    fun fromAnyToString(attributes: Any?): String{
        if(attributes==null)
            return ""
        return attributes as String
    }

    @TypeConverter
    fun fromStringToAny(attributes: String?): Any{
        if(attributes==null)
            return ""
        return attributes
    }

}