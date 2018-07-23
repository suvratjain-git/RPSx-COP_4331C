<?php

	$inData = getRequestInfo();

	$gamesWon = $inData["gamesWon"];
	$gamesLost = $inData["gamesLost"];
	$gamesTied = $inData["gamesTied"];
	$gamesPlayed = $inData["gamesPlayed"];
	$displayName = $inData["displayName"];
	$userName = $inData["Username"];
	//$ID = $inData["ID"];

	$conn = new mysqli("localhost", "RudeDude", "cop4331!", "RPSx");

	if ($conn->connect_error)
	{
		returnWithError("0");
	}
	if($gamesWon != NULL)
	{
		$sql = "UPDATE Players_DB SET gamesWon = '". $gamesWon ."', gamesPlayed = '". $gamesPlayed . "'WHERE Username = '" . $userName . "' ";
	  $conn->query($sql);
		returnWithError("1");
	}

	else if($gamesLost != NULL)
	{
		$sql = "UPDATE Players_DB SET gamesLost = '". $gamesLost ."', gamesPlayed = '". $gamesPlayed . "'WHERE Username = '" . $userName . "' ";
	  $conn->query($sql);
		returnWithError("1");
	}

	else if($gamesTied != NULL)
	{
		$sql = "UPDATE Players_DB SET gamesTied = '". $gamesTied ."', gamesPlayed = '". $gamesPlayed . "'WHERE Username = '" . $userName . "' ";
	  $conn->query($sql);
		returnWithError("1");
	}
	else if($displayName != NULL)
	{
		$sql = "UPDATE Players_DB SET displayName = '". $displayName . "'WHERE Username = '" . $userName . "' ";
		$conn->query($sql);
		returnWithError("1");
	}
	else
	{
			returnWithError("0");
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
		$retValue = json_encode($data);
		sendResultInfoAsJson($retValue);
	}

?>
