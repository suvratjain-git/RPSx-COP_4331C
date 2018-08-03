<?php
	$inData = getRequestInfo();

	$userName = $inData["Username"];
	$passWord = $inData["Password"];
	$firstName = $inData["firstName"];
	$lastName = $inData["lastName"];
	$displayName = $inData["displayName"];
	$email = $inData["email"];
	$data = [];

	$conn = new mysqli("localhost", "RudeDude", "cop4331!", "RPSx");
	if ($conn->connect_error || $userName == null)
	{
		returnWithError("0");
	}
	else
	{
		$temp = checkEmail($conn, $inData);
		if(checkDisplayName($conn, $inData) == 1 && $temp == 1)
		{
			$sql = "INSERT INTO Players_DB (Username,Password,firstName,lastName,displayName,email) VALUES ('" . $userName ."', '" . $passWord ."', '" . $firstName ."', '" . $lastName ."', '" . $displayName . "', '" . $email . "')";
			if($result = $conn->query($sql) != TRUE)
			{
				returnWithError("0");
			}
			else
			{
				$data +=["displayName" => $displayName, "gamesWon" => 0, "gamesLost" => 0, "gamesTied" => 0, "gamesPlayed" => 0];
				returnWithError($data);
			}
		}
		else if($temp == 0)
		{
			returnWithError("3");
		}
		else
		{
			returnWithError("2");
		}
		$conn->close();
	}

	//returnWithError("");

	function getRequestInfo()
	{
		return json_decode(file_get_contents('php://input'), true);
	}

	function checkDisplayName($conn, $inData)
	{
		$sql = "SELECT * FROM Players_DB WHERE displayName='" . $inData["displayName"] . "'";
		$result = $conn->query($sql);
		if ($result->num_rows > 0)
		{
			return 0;
		}
		else
		{
			return 1;
		}
	}

	function checkEmail($conn, $inData)
	{
		$sql = "SELECT * FROM Players_DB WHERE email='" . $inData["email"] . "'";
		$result = $conn->query($sql);
		if ($result->num_rows > 0)
		{
			return 0;
		}
		else
		{
			return 1;
		}
	}

	function sendResultInfoAsJson($obj)
	{
		header('Content-type: application/json');
		echo $obj;
	}

	function returnWithError($err)
	{
		$retValue = json_encode($err);
		sendResultInfoAsJson($retValue);
	}

?>
