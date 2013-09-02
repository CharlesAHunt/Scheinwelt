package utils

import com.mongodb.BasicDBObject

import java.util.List

object DBObjectUtil {


     def toList(dbObject: BasicDBObject ):List[Any] = {

          val values = List()



          for(value <- dbObject.values()){
            values.add(value)
          }

          values
      }

}
