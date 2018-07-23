<?php
	$inData = getRequestInfo();

	$room = $inData["room"];
  $inputUser = $inData["displayName"];
  $choice = $inData["choice"];

	$conn = new mysqli("localhost", "RudeDude", "cop4331!", "RPSx");
	if ($conn->connect_error)
	{
		returnWithError("0");
	}
	else
	{
    $sql = "SELECT user_1,user_2 FROM Room_Table WHERE room='" . $room . "'";
    $result = $conn->query($sql);
    if($result->num_rows > 0)
    {
      $row = $result->fetch_assoc();
      if($row["user_1"] == $inputUser)
      {
        $sql = "UPDATE Room_Table SET choice_1 ='" . $choice . "' WHERE room='" . $room . "'";
        if($result = $conn->query($sql) != TRUE)
        {
          returnWithError("0");
        }
        else
        {
          returnWithError("1");
        }
      }
      else if($row["user_2"] == $inputUser)
      {
        $sql = "UPDATE Room_Table SET choice_2 ='" . $choice . "' WHERE room='" . $room . "'";
        if($result = $conn->query($sql) != TRUE)
        {
          returnWithError("0");
        }
        else
        {
          returnWithError("1");
        }
      }
      else
      {
        returnWithError("0");
      }
    }
    else
    {
      returnWithError("0");
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
