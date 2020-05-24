package com.scala.scogen.caseclasses

import com.scala.scogen.metaData.EmployeeNameList

case class EmployeeDetailsPojo(val fname: String,
                               val lname: String = EmployeeNameList.randomNameGenerator,
                               val empid: String = EmployeeNameList.serialIDGenerator,
                               val age: Int = EmployeeNameList.randomAgeGenerator)
