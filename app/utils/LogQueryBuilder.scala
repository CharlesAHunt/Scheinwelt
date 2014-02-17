package utils

import com.mongodb.casbah.commons.MongoDBObject
import scala.collection.mutable


case class LogQueryBuilder(app: String,env: String,region: String,level: String,exception: String,message: String,beforeDate: String,afterDate: String) {


  def buildQuery(app: String,env: String,region: String,level: String,exception: String,message: String,beforeDate: String,afterDate: String):mutable.Builder = {

    val builder = MongoDBObject.newBuilder
    builder += "foo" -> "bar"
    builder += "baz" -> "qux"
    builder.result

  }


}
