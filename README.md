# scogen-code-test

#### Below mentioned user case is implemented both in java and scala

##Dataset details:
Department Data Definition: department id, department name

Employee Data Definition: emp id, first name, last name, age

Emp Finance info: emp id , ctc, basic,  pf,  gratuity

Emp Dept info: emp id, department id

##Requirements:
1) create 5 department(IT, INFRA, HR, ADMIN, FIN)
2) Write Code to generate 1000 Emp data(unique first name), with age between 18 - 60
3) Write code to generate emp finance info for each emp with CTC between 10,000 - 100,000, basic 20% CTC, PF 10% of basic, gratuity 5% of basic

4)Distribute these 1000 emp to 5 departments, 500 emp works in 2 dept, rest 500 in 3 dept.

##Execute:
1) Write a program to find emp with age > 40 & ctc > 30,000
2) Write a program to find dept with max emp with age > 35 & gratuity < 800

#How to run
Java: com.java.scogen.EmployeeDataMain is the starting point, you can run that class and see the results on console
Scala: com.scala.scogen.EmployeeDataMain is the starting point, you can run that class and see the results on console
