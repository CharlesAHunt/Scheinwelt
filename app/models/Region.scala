package models

import utils.DatabaseService
import org.bson.types.ObjectId
import com.novus.salat.dao.SalatDAO
import utils.LogicContext._

case class Region (_id: ObjectId = new ObjectId,
                    name: String
                   )

object RegionDAO extends SalatDAO[Region, ObjectId] (
  collection = DatabaseService.getCollection("regions"))(manifest[Region],manifest[ObjectId],ctx)