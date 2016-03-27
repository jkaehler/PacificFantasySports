<?php

	$con = mysqli_connect("localhost", "root", "", "pacificfantasysports");
	
	$email = $_POST["email"];
	$password = $_POST["password"];
	
	$statement = mysqli_prepare($con, "SELECT * FROM user WHERE email = ? AND password = ?");
	mysqli_stmt_bind_param($statement, "ss", $username, $password);
	
	mysqli_stmt_execute($statement);
	mysqli_stmt_store_result($statement);
	mysqli_stmt_bind_result($statement, $name, $age, $username, $password, $phoneNumber);
	
	$user = array();
	
	while(mysqli_stmt_fetch($statement)){
		$user['name'] = $name;
		$user['password'] = $password;
	}
	
	echo json_encode($user);
	mysqli_stmt_close($statement);
	mysqli_close($con);
	
?>