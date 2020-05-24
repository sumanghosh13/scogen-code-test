package com.scala.scogen.datagenerator

import com.scala.scogen.BaseClassTest
import org.junit.Assert

class DeptDetailsTest extends BaseClassTest {

  private val deptDataset = new DeptDetailsDataset().deptDataDataset(spark).cache()
  import spark.implicits._

  "count all" should "return all 1000 rows" in {
    Assert.assertEquals(5, deptDataset.count)
  }

  "IT dept " should "exists " in {
    Assert.assertEquals(1, deptDataset.filter($"deptid" === "IT").count)
  }

  "MGR as a dept" should "not be there yet" in{
    Assert.assertEquals(0, deptDataset.filter($"deptid" === "MGR").count)
  }

}
