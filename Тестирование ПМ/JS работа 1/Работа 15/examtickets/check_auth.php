<?php
//ini_set('display_errors', 1);
//error_reporting(E_ALL);
header("Content-Type: text/html; charset=utf8");
include("functions.php");
include("globals.php");

if(isset($_REQUEST["submit"]))
{
		// Проверяем входные данные
	sleep(3);
	$auth_result=auth_data_check($_REQUEST["login"], $_REQUEST["password"]);
	if($auth_result!==true)
		{
		setcookie("error_auth", "Некорректно введен логин или пароль. Проверьте раскладку клавиатуры.", time());
		header("location: auth.php");
		die();
		;}
		
	// Преобразуем входные данные
	$login = strtolower(addslashes(trim($_REQUEST["login"])));
	$password = md5(strtolower(addslashes(trim($_REQUEST["password"]))));
	
	// Подключаемся к базе
	$connect_res = connect_to_database($HOST, $DBLOGIN, $DBPASSWORD, $DBNAME);
	if($connect_res!==true)
		{
		setcookie("error_auth", "Не удается подключиться к базе данных. Обратитесь к администратору.", time());
		header("location: auth.php");
		die();
		;}
				
	// Проверяем существование такого пользователя
	$query_result=mysql_query("SELECT * FROM users WHERE login = '".$login."'");
	if($query_result===false)
		{
		setcookie("error_auth", "Не удалось отправить SQL-запрос. Обратитесь к администратору.", time());
		header("location: auth.php");
		die();		
		}
	$result_mass = mysql_fetch_array($query_result);
	if($result_mass===false)
		{
		setcookie("error_auth", "Пользователь с таким логином не зарегистрирован.", time());
		header("location: auth.php");
		die();
		;}
	if($result_mass['password']!==$password) 
		{
		setcookie("error_auth", "Пароль введен неверно.", time());
		header("location: auth.php");
		die();		
		;}
	
	// Авторизируем пользователя
	$hash = get_random_string();
	if(mysql_query("UPDATE users SET hash='".$hash."', ip='".$_SERVER['REMOTE_ADDR']."' WHERE id = ".$result_mass['id'])===false) 
		{
		setcookie("error_auth", "Ошибка авторизации пользователя. Обратитесь к администратору.", time());
		header("location: auth.php");
		die();
		;}	
	setcookie("hash", $hash, time()+60*60*24);
	setcookie("id", $result_mass['id'], time()+60*60*24);
	header("Location:index.php");
		
	
;}

?>
