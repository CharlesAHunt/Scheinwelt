package models

import java.util.Date

case class Log(
                 exception: String,
                 message: String,
                 level: String,
                 trace: String,
                 logDate: Option[Date] = None)

