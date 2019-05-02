<?php
	require_once('connection.php');
	$nis = $_POST['nis'];
	$name = $_POST['name'];
	$address = $_POST['address'];
	$gender = $_POST['gender'];
	if(!$nis || !$name || !$address || !$gender){
	  echo json_encode(array('message'=>'required field is empty.'));
	}else{
	$query = mysqli_query($CON, "UPDATE tb_student SET name='$name', address='$address', gender='$gender' WHERE nis = '$nis'");
	if($query){
	    echo json_encode(array('message'=>'Siswa berhasil diupdate'));
	  }else{
	    echo json_encode(array('message'=>'Gagal update siswa'));
	  }
}
?>