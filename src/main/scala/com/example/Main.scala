package com.example

import java.time.OffsetDateTime

import scala.collection.mutable

object Main extends App {


  val v1 = ("key1", "value11")
  val v2 = ("key1", "value12")
  val v3 = ("key1", "value13")
  val v4 = ("key2", "value21")
  val v5 = ("key2", "value22")
  val v6 = ("key2", "value23")
  val v7 = ("key2", "value24")
  val v8 = ("key2", "value25")
  val v9 = ("key3", "value31")
  val v10 = ("key4", "value41")


  KeyValueImplementation.put("key1", "value11")
  KeyValueImplementation.put("key1", "value12")
  KeyValueImplementation.put("key1", "value13")
  KeyValueImplementation.put("key2", "value21")
  KeyValueImplementation.put("key2", "value22")
  KeyValueImplementation.put("key2", "value23")
  KeyValueImplementation.put("key2", "value24")
  KeyValueImplementation.put("key2", "value25")
  KeyValueImplementation.put("key3", "value31")
  KeyValueImplementation.put("key4", "value41")


  println(s"result: ${KeyValueImplementation.get("key2", OffsetDateTime.now)}")


}

object KeyValueImplementation extends KeyValueImplementation {
  var bigMap = mutable.HashMap[String, Seq[TimestampAndValue]]()

  case class TimestampAndValue(
                                timestamp: OffsetDateTime,
                                value: String
                              )


}

trait KeyValueImplementation {
  import KeyValueImplementation._

  def get(key: String, timestamp: OffsetDateTime): Option[TimestampAndValue] = {
    bigMap.get(key) match {
      case Some(values) =>
        val allTimestamps = values.map(_.timestamp)
        val position = findPosition(timestamp, allTimestamps)
        val resultTimestamp = allTimestamps(position)
        values.find(_.timestamp == resultTimestamp)
      case None =>
        None
    }
  }

  private def findPosition(timestamp: OffsetDateTime, allTimestamps: Seq[OffsetDateTime]): Int = {
    if(allTimestamps.size == 1) {
      if(timestamp.isAfter(allTimestamps(0))) 1
      else 0
    } else {
      val (l, r ) = allTimestamps.splitAt(allTimestamps.length/2)
      if (timestamp.isBefore(l.head)) 0
      else if (timestamp.isBefore(r.head)) findPosition(timestamp, l)
      else if (timestamp.isAfter(l.last)) findPosition(timestamp, r)
      else allTimestamps.length
    }
  }

  def put(key: String, value: String) = {
    val timestamp = OffsetDateTime.now
    val newElement = TimestampAndValue(timestamp, value)
    bigMap.get(key) match {
      case Some(values) =>
        val newValues = values :+ newElement
        bigMap.put(key, newValues)
      case None =>
        bigMap.put(key, Seq(newElement))
    }
  }
}
