<?php

	$inData = getRequestInfo();

	$gamesWon = 0;
	$gamesLost = 0;
	$gamesTied = 0;
	$gamesPlayed = 0;
	$data = "";

	$conn = new mysqli("localhost", "RudeDude", "cop4331!", "RPSx");

	if ($conn->connect_error)
	{
		returnWithError( $conn->connect_error );
	}
	else
	{
		$sql = "SELECT displayName,gamesWon,gamesLost,gamesTied,gamesPlayed FROM Players_DB where Username='" . $inData["Username"] . "' and Password='" . $inData["Password"] . "'";
		$result = $conn->query($sql);
		if ($result->num_rows > 0)
		{
			$row = $result->fetch_assoc();
			$gamesWon = $row["gamesWon"];
			$gamesLost = $row["gamesLost"];
			$gamesTied = $row["gamesTied"];
			$gamesPlayed = $row["gamesPlayed"];
			$displayName = $row["displayName"];
			$data .='"'.$displayName.",".$gamesWon.",".$gamesLost.",".$gamesTied.",".$gamesPlayed.",".$ID.'"';
			returnWithInfo($data);
		}
		else
		{
			returnWithError("No Records Found");
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
		$retValue = '{"id":0,"firstName":"","lastName":"","error":"' . $err . '"}';
		sendResultInfoAsJson($retValue);
	}

	function returnWithInfo($data)
	{
		$retValue = json_encode($data);
		sendResultInfoAsJson($retValue);
	}

?>
