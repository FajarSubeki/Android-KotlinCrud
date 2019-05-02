<?php
	require_once('connection.php');
	$nis = $_GET['nis'];
	if(!$nis){
	  echo json_encode(array('message'=>'required field is empty'));
	}else{
	$query = mysqli_query($CON, "DELETE FROM tb_student WHERE nis='$nis'");
	if($query){
	    echo json_encode(array('message'=>'Siswa berhasil dihapus'));
	  }else{
	    echo json_encode(array('message'=>'Gagal hapus siswa'));
	  }
	}
?>