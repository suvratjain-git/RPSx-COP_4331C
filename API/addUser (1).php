<?php
 frontend
	$inData = getRequestInfo();

	$userName = $inData["Username"];
	$passWord = $inData["Password"];


	$conn = new mysqli("localhost", "RudeDude", "cop4331!", "RPSx");
	if ($conn->connect_error || $userName == null)
	{
		returnWithError($conn->connect_error);
	}
	else
	{
		$sql = "insert INTO Users (Username, Password) VALUES (' $userName ', ' $passWord ')";
		if( $result = $conn->query($sql) != TRUE )
		{
			returnWithError($conn->error);
		}
		$conn->close();
	}

	//returnWithError("");

	function getRequestInfo()
	{
		return json_decode(file_get_contents('php://input'), true);
	}

	function sendResultInfoAsJson($obj)
	{
		header('Content-type: application/json');
		echo $obj;
	}

	function returnWithError($err)
	{
		$retValue = '{"error":"' . $err . '"}';
		sendResultInfoAsJson($retValue);
	}



$conn = new mysqli('localhost','RudeDude','cop4331!','RPSx');

//test connection
if($conn->connect_error) {
  die ("Connection Error: " . $conn->connect_error);
}

if(isset($_POST['user']) && isset($_POST['password'])) {

  $pWord = $_POST['password'];
  $newUser = $_POST['username'];
}

  $checkUsername = mysqli_query("SELECT * FROM Users WHERE Username = '$newUser'");

  if(mysqli_num_rows($checkUsername) >= 1)
  {
    sendResultInfoAsJson("Error! Username already exists.");
  }
  else
      {
        $registerUser = mysqli_query("INSERT INTO Users (Username, Password) VALUES ('$newUser','$pWord')");
      }

  if($registerUser)
      {
        sendResultInfoAsJson("Your account was successfully created!");
      }
  $conn->close();
master
?>
