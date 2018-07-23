<?php
	$inData = getRequestInfo();

	$room = $inData["room"];

	$conn = new mysqli("localhost", "RudeDude", "cop4331!", "RPSx");
	if ($conn->connect_error)
	{
		returnWithError("0");
	}
	else
	{
    $sql = "SELECT user_1,user_2,choice_1,choice_2 FROM Room_Table WHERE room='" . $room . "'";
    $result = $conn->query($sql);
    if($result->num_rows > 0)
    {
      $row = $result->fetch_assoc();
      $data ='"'. $row["user_1"] . "," . $row["choice_1"] . "," . $row["user_2"] . "," . $row["choice_2"] . '"';
      returnWithError($data);  
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
