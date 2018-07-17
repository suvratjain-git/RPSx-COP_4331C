<?php

	$inData = getRequestInfo();

	$gamesWon = $inData["gamesWon"];
	$gamesLost = $inData["gamesLost"];
	$gamesTied = $inData["gamesTied"];
	$gamesPlayed = $inData["gamesPlayed"];
	$ID = $inData["ID"];

	$conn = new mysqli("localhost", "RudeDude", "cop4331!", "RPSx");

	if ($conn->connect_error)
	{
		returnWithError( $conn->connect_error );
	}

	if($gamesWon != NULL)
	{
		$sql = "UPDATE Players_DB SET gamesWon = '". $gamesWon ."', gamesPlayed = '". $gamesPlayed . "'WHERE ID = '" . $ID . "' ";
	  $conn->query($sql);
	}

	else if($gamesLost != NULL)
	{
		$sql = "UPDATE Players_DB SET gamesLost = '". $gamesLost ."', gamesPlayed = '". $gamesPlayed . "'WHERE ID = '" . $ID . "' ";
	  $conn->query($sql);
	}

	else if($gamesTied != NULL)
	{
		$sql = "UPDATE Players_DB SET gamesTied = '". $gamesTied ."', gamesPlayed = '". $gamesPlayed . "'WHERE ID = '" . $ID . "' ";
	  $conn->query($sql);
	}
	else
	{
			returnWithError("Unable to Update");
	}
		$conn->close();

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

?>
