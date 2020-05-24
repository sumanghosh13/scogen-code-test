package com.scala.scogen.metaData

import scala.collection.immutable.HashSet
import scala.util.Random

object EmployeeNameList {

  private[metaData] var counter = 0

  def setofUniqueNames: Set[String] = {
    var nameSet = new HashSet[String]
    while (nameSet.size < 1000)
      {
        nameSet += randomNameGenerator
      }
    nameSet
  }

  def randomNameGenerator: String = {
    val alpha = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
    val size = alpha.size
    val random = new Random
    val targetStringLength = random.nextInt(10) + 3
    (1 to targetStringLength).map(x => alpha(random.nextInt().abs % size)).mkString
  }

  def serialIDGenerator: String = Integer.toString({
    counter += 1; counter - 1
  })

  def randomAgeGenerator: Int = {
    val leftLimit = 18
    val rightLimit = 42
    val random = new Random
    random.nextInt(rightLimit + 1) + leftLimit
  }

}
