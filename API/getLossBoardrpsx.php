<?php

	$inData = getRequestInfo();
	$searchCount = 0;
  $dataArray = [];
  $i = 0;

	$conn = new mysqli("localhost", "RudeDude", "cop4331!", "RPSx");
	if ($conn->connect_error)
	{
		returnWithError($conn->connect_error);
	}
	else
	{
		$sql = "SELECT displayName,gamesLost FROM Players_DB";
		$result = $conn->query($sql);
		if ($result->num_rows > 0)
		{
			 while($row = $result->fetch_assoc())
			 {
					$searchCount++;
          $dataArray += [$row["displayName"] => $row["gamesLost"]];
			}
			if($result->num_rows > 0)
      {
        arsort($dataArray);
        foreach($dataArray as $x => $xVal)
        {
          if($i == 5)
          {
            break;
          }
          if($i > 0)
          {
            $data .="?";
          }
          $data .='"' . $x . "," . $xVal . '"';
          $i++;
        }
				returnWithInfo($data);
			}
			else
			{
				returnWithError(0);
			}
		}
		else
		{
			returnWithError(1);
		}
		$conn->close();
	}

	function getRequestInfo()
	{
		return json_decode(file_get_contents('php://input'), true);
	}

	function sendResultInfoAsJson($obj)
	{
		header('Content-type: application/json');
		echo $obj;
	}

	function returnWithError($id)
	{
		$retValue = '{"id":' . $id . '}';
		sendResultInfoAsJson($retValue);
	}

	function returnWithInfo($str)
	{
		$retValue = json_encode($str);
		sendResultInfoAsJson($retValue);
	}
?>
