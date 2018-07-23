<?php
	$inData = getRequestInfo();

	$userName = $inData["Username"];
	$newPassword = $inData["newPassword"];
	$displayName = $inData["displayName"];

	$conn = new mysqli("localhost", "RudeDude", "cop4331!", "RPSx");
	if ($conn->connect_error || $userName == null)
	{
		returnWithError($conn->connect_error);
	}
	else
	{
    $sql = "SELECT displayName,Username,ID FROM Players_DB where Username='" . $inData["Username"] . "' and displayName='" . $inData["displayName"] . "'";
    $result = $conn->query($sql);
    $row = $result->fetch_assoc();
    $ID = $row["ID"];
    if($result->num_rows > 0)
    {
      $data = "UPDATE Players_DB SET Password= '". $newPassword . "'WHERE ID = '" . $ID . "' ";
      if($res = $conn->query($data) != TRUE)
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
