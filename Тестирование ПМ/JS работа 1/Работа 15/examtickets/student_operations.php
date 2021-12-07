<?php
//ini_set('display_errors', 1);
//error_reporting(E_ALL);
header("Content-Type: text/html; charset=utf8");
include("functions.php");
include("globals.php");

// Проверяем аутентификацию, уровень доступа и получаем пользовательские данные
$result_mass = auth_check($_COOKIE['id'], $_COOKIE['hash'], $HOST, $DBLOGIN, $DBPASSWORD, $DBNAME);
if($result_mass['access_level']!=="5")
	{
	header("Location:exit.php");
	die();
	;}

// Отображение списка предметов
if(isset($_REQUEST['show_subjects']))
{
	// Проверяем, выдавался ли билет

	check_reseived_ticket($result_mass['ticket'], $result_mass['subj_id']);
		
	// Считываем все предметы из базы
	$query_res = mysql_query("SELECT * FROM subjects");
	if($query_res===false)
		{
		print "Ошибка считывания списка предметов. Обратитесь к администратору.";
		die();
		;}	

	// Сортируем результат по алфавиту
	$subject_list=array();
	while($result_mass=mysql_fetch_assoc($query_res))
		{
		$subject_list[$result_mass['subj_id']] = $result_mass['subj_name'];
		;}
	asort($subject_list);
	
	
	// Отображаем результат
	print "<h3>Выберите предмет, по которому сдаете экзамен:</h3><table id='subj_list_table'>";
	foreach($subject_list as $key => $value)
		{
		print 
		"<tr><td>
		<input type=button class='submit' id=id".$key." value='".$value."' style='width:100%' onclick='get_ticket(this)'>
		</td></tr>"
		;}
	print "</table>";	
	die();
;}

// Получение билета
if($_REQUEST['get_ticket']==="true")
{	
	// Проверяем входные данные
	$subj_id = $_REQUEST['subj_id'];
	if(!preg_match("/^[0-9]+$/",$subj_id))
	{
		die("Некорректный идентификатор предмета:".$subj_id);
	;}
	
	// Проверяем, выдавался ли билет
	check_reseived_ticket($result_mass['ticket'], $result_mass['subj_id']);
		
	// Получаем массив файлов
	if(!($catalog = opendir($subj_id)))
	{
		die("Ошибка Каталог не существует. Обратитесь к администратору");		
	;}
	$files = array();
	while(($file = readdir($catalog))!==false)
	{
		if($file!="."&&$file!="..")
		{
		$files[] = $file;	
		;}
	;}
	
	// Определяем случайный билет
	shuffle($files);
	$ticket = $files[0];
	
	// Записываем полученный билет в базу
	$query_res = mysql_query("UPDATE users SET ticket = '".$ticket."', subj_id='".$subj_id."' WHERE id = '".$result_mass['id']."'");
	if($query_res===false)
		{
		print "Ошибка записи полученного билета в базу данных. Обратитесь к администратору.";
		die();
		;}	
	
	check_reseived_ticket($ticket, $subj_id);
;}

?>
