package utils;

import com.mongodb.BasicDBObject;

import java.util.ArrayList;
import java.util.List;

public class DBObjectUtil {


      public static List<Object> toList(BasicDBObject dbObject) {

          List<Object> values = new ArrayList<Object>();

          for(Object value : dbObject.values()){
            values.add(value);
          }

          return values;
      }

}
