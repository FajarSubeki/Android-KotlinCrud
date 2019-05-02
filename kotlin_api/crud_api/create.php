<?php
	require_once('connection.php');
	$nis = $_POST['nis'];
	$name = $_POST['name'];
	$address = $_POST['address'];
	$gender = $_POST['gender'];
	if(!$nis || !$name || !$address || !$gender){
	  echo json_encode(array('message'=>'Field Wajib Diisi'));
	}else{
	$query = mysqli_query($CON, "INSERT INTO tb_student VALUES ('$nis','$name','$address','$gender')");
	if($query){
	    echo json_encode(array('message'=>'Siswa berhasil ditambahkan'));
	  }else{
	    echo json_encode(array('message'=>'Gagal menambahkan siswa'));
	  }
	}
?>