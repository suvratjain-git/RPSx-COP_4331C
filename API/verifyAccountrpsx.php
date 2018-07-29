<?php

	$inData = getRequestInfo();

	$conn = new mysqli("localhost", "RudeDude", "cop4331!", "RPSx");

	if ($conn->connect_error)
	{
		returnWithError( $conn->connect_error );
	}
	else
	{
    $sql = "SELECT * FROM Players_DB where Username='" . $inData["Username"] . "' and displayName='" . $inData["displayName"] . "' and email='" . $inData["email"] . "'";
   	$result = $conn->query($sql);
		if ($result->num_rows > 0)
		{
			returnWithInfo("1");
		}
		else
		{
			returnWithError("0");
		}
		$conn->close();
	}

	function getRequestInfo()
	{
		return json_decode(file_get_contents('php://input'), true);
	}

	function sendResultInfoAsJson( $obj )
	{
		header('Content-type: application/json');
		echo $obj;
	}

	function returnWithError($err)
	{
		$retValue = json_encode($err);
		sendResultInfoAsJson($retValue);
	}

	function returnWithInfo($data)
	{
		$retValue = json_encode($data);
		sendResultInfoAsJson($retValue);
	}

?>
