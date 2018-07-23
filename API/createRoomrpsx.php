<?php
	$inData = getRequestInfo();

	$room = $inData["room"];
  $displayName = $inData["displayName"];

	$conn = new mysqli("localhost", "RudeDude", "cop4331!", "RPSx");
	if ($conn->connect_error)
	{
		returnWithError("0");
	}
	else
	{
    $sql = "SELECT room FROM Room_Table WHERE room='" . $room . "' AND isFull='" . 1 . "'";
    $result = $conn->query($sql);
    if($result->num_rows > 0)
    {
      $sql = "DELETE FROM Room_Table WHERE room='" . $room . "'";
      $conn->query($sql);
    }
		$sql = "SELECT room FROM Room_Table WHERE room='" . $room . "'";
		$result = $conn->query($sql);
		if($result->num_rows > 0)
    {
      returnWithError("2");
    }
		else
		{
			$sql = "INSERT INTO Room_Table (Room, user_1) VALUES ('" . $room . "','" . $displayName . "')";
			if($result = $conn->query($sql) != TRUE)
			{
				returnWithError("0");
			}
			else
			{
				returnWithError("1");
			}
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
		$retValue = json_encode($err);
		sendResultInfoAsJson($retValue);
	}

?>
