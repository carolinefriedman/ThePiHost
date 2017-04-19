<?php

include 'dbh.php'

$first = $_POST['firstName'];
$last = $_POST['lastName'];
$userid = $_POST['userID'];
$pword = $_POST['password'];

$sql = "INSERT INTO loginTest (firstName, lastName, userID, password) 
VALUES ('$first', '$last', '$userid', '$pword')";

$results = $conn->query($sql);

?>
