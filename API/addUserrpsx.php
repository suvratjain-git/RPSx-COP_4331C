<?php
	$inData = getRequestInfo();

	$userName = $inData["Username"];
	$passWord = $inData["Password"];
	$firstName = $inData["firstName"];
	$lastName = $inData["lastName"];
	$displayName = $inData["displayName"];

	$conn = new mysqli("localhost", "RudeDude", "cop4331!", "RPSx");
	if ($conn->connect_error || $userName == null)
	{
		returnWithError($conn->connect_error);
	}
	else
	{
		$sql = "INSERT INTO Players_DB (Username,Password,firstName,lastName,displayName) VALUES ('" . $userName ."', '" . $passWord ."', '" . $firstName ."', '" . $lastName ."', '" . $displayName . "')";
		if($result = $conn->query($sql) != TRUE)
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

?>
