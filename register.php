<?php
	$con = mysqli_connect("localhost", "root", "", "pacificfantasysports");
	
	$firstName = $_POST["firstName"];
	$lastName = $_POST["lastName"];
	$email = $_POST["email"];
	$password = $_POST["password"];
	
	$statement = mysqli_prepare($con, "INSERT INTO user (FirstName, LastName, email, password) VALUES (?, ?, ?, ?) "); 
	mysqli_stmt_bind_param($statement, "ssss", $firstName, $lastName, $email, $password);
	mysqli_stmt_execute($statement);
	
	mysqli_stmt_close($statement);

	//finish up by closing the connection
	
	mysqli_close($con);

?>
