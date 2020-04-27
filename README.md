# LibraryJavaSwing
*Simple Library application using Java Swing 

For DB : used postgresql 11<br/>
java : Java 8<br/>

*added jars for postgresql & rs2xml in resources folder<br/>

*Library Management Assignment

1. Login frame
2. Admin frame
3. User frame

```
1. Login
	a. can login with admin or user
	b. validation 
		i.  empty data submit
		ii. incorrect username or pwd
		
2. Admin
login as UN: admin & PWD: admin
	a. back btn -> go back to login page
	b. welcome msg - top right
	c. view -> display books in table (rs2xml.jar)
	d. add -> add book
		i. enter details for book like title, author, isbn
		ii. validations
			 a) empty fields
			 b) isbn -> only numbers (no alphanumeric)
			 c) correct details -> data insert to table (for refresh click on view btn)
					
3. User
login as UN: user & PWD: user
  a. back btn -> go back to login page
	b. welcome msg - top right
	c. view -> display books in table (rs2xml.jar)
```		
		
		
