<?php
/*
Список функций

get_random_string() 											Возвращает строку 62 случайных неповторяющихся символов 
registration_check($login, $password, $password2, $email)		Проверяет корректность логина, пароля и совпадение паролей. 
																Возвращает тру или массив ошибок.
connect_to_database($host, $dblogin, $dbpassword, $dbname)		Возвращает тру, если удалось подключиться, сообщение об ошибке, если нет
auth_check($id, $hash, $host, $dblogin, $dbpassword, $dbname)	Проверяет авторизацию, при неудачи перенаправляет на exit.php. 
																Использует connect_to_database()
																
*/

ini_set('display_errors', 1);
error_reporting(E_ALL);

function get_random_string()  
{
	$hash="a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,0,1,2,3,4,5,6,7,8,9,A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z";
	$mass=explode(",", $hash);
	shuffle($mass);
	$hash=implode($mass);
	return $hash;
;}

function registration_data_check($fio, $login, $password, $password2, $email)
	{

	if(!preg_match("/^[а-яА-ЯЁё]{1,30}\s{1,5}[а-яА-ЯЁё]{1,30}\s{1,5}[а-яА-ЯЁё]{1,30}$/u",trim($fio))){$err[]="ФИО должны состоять из символов русского алфавита.";}
	if(!preg_match("/^[a-zA-z0-9]{1,20}$/",trim($login))){$err[]="Логин должен состоять из символов латинского алфавита и цифр и быть длиной не более 20 символов";}
	if(!preg_match("/^[a-zA-z0-9]{1,20}$/",trim($password))){$err[]="Пароль должен состоять из символов латинского алфавита и цифр и быть длиной не более 20 символов"; }
	if($password!==$password2){$err[]="Пароли не совпадают";}
	if(!filter_var($email, FILTER_VALIDATE_EMAIL)){$err[]="Введен некорректный адрес электронной почты.";}
	if(isset($err))
		{
		return $err;
		;} 
	else
		{
		return true;
		;}

	;}

function auth_data_check($login, $password)
	{
	if(!preg_match("/^[a-zA-z0-9]{1,20}$/",$login)){$err[]="Логин должен состоять из символов латинского алфавита и цифр и быть длиной не более 20 символов";}
	if(!preg_match("/^[a-zA-z0-9]{1,20}$/",$password)){$err[]="Пароль должен состоять из символов латинского алфавита и цифр и быть длиной не более 20 символов";}
	if(isset($err))
		{
		return $err;
		;} 
	else
		{
		return true;
		;}

	;}
	
	
	
	
function connect_to_database($host, $dblogin, $dbpassword, $dbname)
{
	if (!mysql_connect($host, $dblogin, $dbpassword)) 
		{
		return "Ошибка подключения к хосту с базой данных<br>";
		;}
	if(!mysql_select_db($dbname))
		{
		return "Ошибка подключения к базе данных<br>";
		;}
	mysql_set_charset("utf8");
	return true;
;}



function auth_check($id, $hash, $host, $dblogin, $dbpassword, $dbname)
	{
	// Проверяем, пришли ли ИД и ИП.
	if(!isset($id)||!isset($hash))
		{
		header("Location:exit.php");
		die();
		;}
		
	// Подключаемся к базе
	$connect_result = connect_to_database($host, $dblogin, $dbpassword, $dbname);
	if($connect_result!==true)
		{
		die("Не удалось подключиться к базе данных. Обратитесь к администратору.");
		;}
	
	// Проверяем авторизацию пользователя
	$query_result=mysql_query("SELECT * FROM users WHERE id = '".intval($id)."' AND hash='".addslashes($hash)."' AND ip='".$_SERVER['REMOTE_ADDR']."'");
	if($query_result===false)
		{
		die("Не удалось отправить запрос к базе данных. Обратитесь к администратору.");
		;}
		
	$result_mass = mysql_fetch_array($query_result);
	if($result_mass===false)
		{
		header("Location:exit.php");
		die();
		;}
	return $result_mass;
	
	;}
	
function redirect_to_index($id, $hash)
	{
	if($id!=false||$hash!=false)
		{
		header("Location: index.php");
		;}
	;}
	
	
function check_reseived_ticket($ticket, $subj_id)
	{
	if(!$ticket=="")
		{
		die
		(
			"Билет получен: <a href='".$subj_id."/".$ticket."'>".$ticket."</a>"
		);
		;}
	;}
	
	
	
	
	
	
	
	
	
	
	
	
?>
