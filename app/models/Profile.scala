package models

import org.codehaus.jackson.annotate.JsonProperty
import org.mongojack.{Id, MongoCollection, ObjectId}
import java.util.Date
import com.fasterxml.jackson.databind.BeanProperty

/**
 * User: Charles
 * Date: 7/17/13
 */

@MongoCollection(name = "profiles")
class Profile(

               @ObjectId @Id val id: String,
               @BeanProperty @JsonProperty("date") val date: Date) {
  @ObjectId
  @Id def getId = id

}
