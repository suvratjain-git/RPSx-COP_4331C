<?php
	$inData = getRequestInfo();

	$conn = new mysqli("localhost", "RudeDude", "cop4331!", "RPSx");

	if ($conn->connect_error)
	{
		returnWithError("0");
	}
	else
	{
    $sql = "SELECT Username,Password FROM Players_DB where email='" . $inData["email"] . "'";
   	$result = $conn->query($sql);
		if ($result->num_rows > 0)
		{
      $row = $result->fetch_assoc();
      $to = $inData["email"];
      $subject = "RPSx Account Info Request";
      $txt = "Username: " . $row["Username"] . "\nPassword: " . $row["Password"] .
      "\n\nPlease do not reply to this email.";
      $headers = "From: account.recovery@ameade.us" . "\r\n";
      mail($to, $subject, $txt, $headers);
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
