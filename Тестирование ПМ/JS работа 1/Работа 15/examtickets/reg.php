<?php 
ini_set('display_errors', 1);
error_reporting(E_ALL);
header("Content-Type: text/html; charset=utf8");
include("functions.php");
include("globals.php");
if (isset($_COOKIE['id'])){redirect_to_index($_COOKIE['id'], $_COOKIE['hash']);}
?>



<html>
<head>
	<title>Регистрация пользователя</title>
	<link rel="stylesheet" type="text/css" href="main.css">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<style>
	</style>
	
</head>
<body>
<div id=main_box>
<img src="logo.png" id=logo>

<?php

if(isset($_REQUEST["submit"]))
{
	// Проверяем входные данные
	sleep(3);
	$reg_result=registration_data_check($_REQUEST["fio"], $_REQUEST["login"], $_REQUEST["password"], $_REQUEST["password2"], $_REQUEST["email"]);
	if($reg_result!==true)
		{
		print "<h3>В процессе регистрации возникли ошибки:</h3><ol style='text-align:left'>";
		foreach($reg_result as $key =>$value)
			{
			print "<li>".$value."<br>";
			;}
		print "</ol><br><br><A HREF='reg.php'>Вернуться назад</a>";
		die();
		;}
		
	// Преобразуем поступившие данные
	//$fio = $_REQUEST[fio];
	$fio = mb_convert_case(mb_strtolower(addslashes(trim($_REQUEST['fio'])), 'UTF-8'), MB_CASE_TITLE, "UTF-8");
	$login = addslashes(trim(strtolower($_REQUEST['login'])));
	$password = md5(addslashes(trim(strtolower($_REQUEST['password']))));
	$email = addslashes(trim(strtolower($_REQUEST['email'])));
	
	// Подключаемся к базе
	$connect_res = connect_to_database($HOST, $DBLOGIN, $DBPASSWORD, $DBNAME);
	if($connect_res!==true)
		{
		print $connect_res; 
		print "<br><A href=reg.php>Вернуться назад</a>";
		die();
		;}
	
	// Добавляем в таблицу нового пользователя
	if(mysql_query("INSERT INTO users (fio, login, password, email) VALUES ('".$fio."','".$login."', '".$password."', '".$email."')")===false) 
		{
		if(mysql_errno()===1062)
			{
			print "<h3>Пользователь с таким логином уже зарегистрирован.</h3><br>";
			print "<br><A href=reg.php>Вернуться назад</a>";			
			;}
		else
			{
		print "<h3>Ошибка. Не удалось зарегистрировать пользователя. Обратитесь к администратору. </h3><br>";  print mysql_error();	
			;}
		//print mysql_error();
		die();
		;}
		
	print "<h3 style='font-family:monospace; text-align:center'>Пользователь успешно зарегистрирован. <br>Вы будете перенаправлены на страницу аутентификации. </h3><br>";
	print "<A href=auth.php>Перейти на страницу аутентификации...</a><br><br>";
	print "<script type='text/javascript'>setTimeout(\"document.location.href='auth.php'\", 3000)</script>";
	die();		
;}
?>


<table id=reg_auth_table >
<tr><td colspan=2 align=center>Регистрация пользователя</td></tr>	
<form name="reg_form" action="" method=POST accept-charset="utf-8" enctype="application/x-www-form-urlencoded">
<tr>
<td>ФИО</td><td><input type=text name=fio autocomplete=off size=30 required></td>
</tr><tr>
<td>Логин</td><td><input type=text name=login autocomplete=off required></td>
</tr>
<tr>
<td>Пароль</td><td><input type=password name=password autocomplete=off required></td>
</tr>
<tr>
<td>Повторите пароль</td><td><input type=password name=password2 autocomplete=off required></td>
</tr>
<tr>
<td>Email</td><td><input type=text name=email autocomplete=off required></td>
</tr>
<tr>
<td><input type=submit name=submit class=submit value="Регистрация"></td><td align=right><A href=auth.php>Вход</a></td>
</tr>
</table>
<img id=loading src=loading.gif>
</div>




<script type="text/javascript">
reg_form.onsubmit=function(){document.getElementById("loading").style.visibility="visible";};
</script>
</body>
</html>
