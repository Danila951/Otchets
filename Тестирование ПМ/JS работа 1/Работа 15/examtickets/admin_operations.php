<?php
header("Content-Type: text/html; charset=utf8");
include("functions.php");
include("globals.php");

// Проверяем аутентификацию, уровень доступа и получаем пользовательские данные
$result_mass = auth_check($_COOKIE['id'], $_COOKIE['hash'], $HOST, $DBLOGIN, $DBPASSWORD, $DBNAME);
if($result_mass['access_level']!=="1")
	{
	header("Location:exit.php");
	die();
	;}

// Добавление нового предмета	
if(isset($_REQUEST['add_subject']))
{
		// Проверяем корректность названия предмета
		if(!preg_match("/^[А-я0-9\s]+$/u",$_REQUEST['subj_name']))
			{
			print "Название предмета должно состоять из символов русского алфавита и цифр<br>"; 
			print "Некорректное название: ".$_REQUEST['subj_name'];
			die();
			;}
		
		// Преобразуем данные 
		$subj_name = addslashes(trim($_REQUEST['subj_name']));
		
		// Проверяем, существование такого предмета
		$query_res = mysql_query("SELECT * FROM subjects WHERE subj_name = '".$subj_name."'");
		if($query_res === false)
			{
			die("Не удалось считать имя предмета из базы данных. Обратитесь к администратору.");
			;}
		if(mysql_fetch_array($query_res)!=false)
			{
			die("Такой предмет уже зарегистрирован.<br>");
			;}
		
		// Записываем в базу новый предмет
		if(mysql_query("INSERT INTO subjects (subj_name) VALUES ('".$subj_name."')")===false)
			{
			die("Не удалось добавить пользователя в базу данных. Обратитесь к администратору.");
			;}	
			
		// Создаем каталог для этого предмета
		$query_res = mysql_query("SELECT subj_id FROM subjects WHERE subj_name = '".$subj_name."'");
		if($query_res===false)
			{
			die("Не удалось считать ID предмета. Обратитесь к администратору."); 
			;}
		$res_mass = mysql_fetch_array($query_res);
		$subj_id = $res_mass['subj_id'];
		if(!mkdir($subj_id))
			{
			die("Не удалось создать каталог. Обратитесь к администратору.");
			;}
		print "<script type=text/javascript>$('#right_column').load('admin_operations.php', 'show_subjects=true');</script>";
		die();
;}

// Отображение списка предметов		
if(isset($_REQUEST['show_subjects']))
{
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
	print "<table id='subj_list_table'>";
	foreach($subject_list as $key => $value)
		{
		print 
		"<tr><td>
		<input type=button class='submit' id=id".$key." value='".$value."' style='width:100%' onclick='show_tickets(this)'>
		</td><td>
		<img src='cross.gif' style='margin-left:10px' class='cross' id='".$key."' width='15px' alt='Удалить' onclick='delete_subject(this)'>
		</td></tr>"
		;}
	print "</table>";	
	die();
;}

// Удаление предмета	

if(isset($_REQUEST['delete_subject'])&&preg_match("/^[0-9]+$/",$_REQUEST['delete_subject']))
{
	$subj_id = $_REQUEST['delete_subject'];
	if(mysql_query("DELETE FROM subjects WHERE subj_id = ".$subj_id)!==true)
		{
		die( "Ошибка удаления предмета. Обратитесь к администратору.")
		;}
	if(!($catalog = opendir($subj_id)))
		{
		die("Ошибка Каталог не существует. Обратитесь к администратору");		
		;}
	while(($file = readdir($catalog))!==false)
		{
		if($file!="."&&$file!="..")
			{
			unlink($subj_id."/".$file);
			;}
		;}
		closedir($catalog);
		rmdir($subj_id);
	print "<script type=text/javascript>$('#right_column').load('admin_operations.php', 'show_subjects=true');</script>";
	die();
;}
	
// Отображение билетов

if(isset($_REQUEST["show_tickets"]))
{
		if(!($catalog = opendir($_REQUEST["subj_id"])))
		{
		die("Ошибка Каталог не существует. Обратитесь к администратору");		
		;}
	print "
		<form action='admin_operations.php' method='POST'>
		<table>
		";
	$count=1;
	while(($file = readdir($catalog))!==false)
		{
		if($file!="."&&$file!="..")
			{
			print 
				"
					<tr>
						<td>
							<input type=checkbox name='file".$count."' value='".$file."'>
						</td>
						<td>
							".$count++.". ".$file."
						</td>
					</tr>
				";
			;}
		;}
	print 
		"
				<tr>
					<td colspan=2>
						<input type=hidden name='subj_id' value='".$_REQUEST['subj_id']."'>
						<input type=submit class='submit' name='button_delete_ticket' value='Удалить отмеченные'>
					</td>
				</tr>
			</form>
		</table>
		";
		closedir($catalog);	
	
	print 
	"
	<p> <b>Загрузить новые билеты </b><br>
	Размер билета не должен превышать 8Мб.</p>
	<form action=admin_operations.php method=post enctype='multipart/form-data'>
		<input type=hidden name='MAX_FILE_SIZE' value='8388608'>
		<input type=hidden name='subj_id' value='".$_REQUEST["subj_id"]."'>
		<input type='file' name='uploadfile[]' multiple><br>

		<input type=submit class='submit' name='upload_button' value=Загрузить>
	</form>
	";
	die();
;}

// Загрузка билетов

if(isset($_REQUEST["upload_button"]))
{
	$i = 0;
	while(isset($_FILES['uploadfile']['tmp_name'][$i]))
	{
		if($_FILES['uploadfile']['tmp_name'][$i]!=false&&$_FILES['uploadfile']['error'][$i]===0)
		{	
			$replace_simbols = array("'");
			$_FILES['uploadfile']['name'][$i] = str_replace($replace_simbols, "\"", $_FILES['uploadfile']['name'][$i]);
			if(!copy($_FILES['uploadfile']['tmp_name'][$i], $_REQUEST["subj_id"]."/".$_FILES['uploadfile']['name'][$i]))
			{
				die("Ошибка загрузки файла.");
			;}
;
		;}
		if($_FILES['uploadfile']['tmp_name'][$i]!=false&&$_FILES['uploadfile']['error'][$i]!==0)
		{
			header("Location:index.php?upload_error=".$_FILES['uploadfile']['error'][$i]);
			die();
		;}
		$i++;	
	;}
	//print "<script type=text/javascript>$('#right_column').load('admin_operations.php', 'show_tickets=true&subj_id=".$_REQUEST['subj_id']."');</script>";
	header("Location:index.php?operation_result=".date("H:i")." Билеты%20успешно%20загружены.");
	
;}
	
// Удаление билетов

if(isset($_REQUEST["button_delete_ticket"]))	
{
	foreach($_REQUEST as $key => $value)
		{
		if(preg_match("/^file[0-9]+$/", $key))
			{
			if(!(unlink($_REQUEST['subj_id']."/".$value)))
				{
				header("Location:index.php?operation_result=".date("H:i")." Билет ".$value."%20не%20существует.");
				die();
				;}
			;}		
		;}
	header("Location:index.php?operation_result=".date("H:i")." Билеты%20успешно%20удалены.");
	die();
;}


// Отображение списка студентов

if(isset($_REQUEST["show_students"]))	
{
	// Считываем все предметы из базы
	$query_res = mysql_query("SELECT * FROM users WHERE access_level = 5");
	if($query_res===false)
	{
		print "Ошибка считывания списка предметов. Обратитесь к администратору.";
		die();
	;}	
	// Сортируем результат по алфавиту
	$students_list=array();
	while($result_mass=mysql_fetch_assoc($query_res))
	{
		$students_list[$result_mass['id']] = $result_mass['fio'];
		$tickets_list[$result_mass['id']] = $result_mass['ticket'];
	;}
	asort($students_list);
	
	// Отображаем список студентов
	$count=1;
	print 	
		"
		<form action='admin_operations.php'>
		<input type=submit class='submit' name='button_delete_students' value='Удалить отмеченных студентов'>
		<br><br>
		<table >
			<tr>
				<th colspan=2>Студент</th>
				<th colspan=2>Выданный билет</th>
			</tr>
		";
	foreach($students_list as $key => $value)
		{
			print 
				"
				<tr>
					<td>
						<input type='checkbox' name=student".$count." value=".$key.">
					</td>
					<td style='border-right:solid 1px black; padding-right:10px;'>
						".$count++." ".$value."
					</td>
					<td style='padding-left:10px;padding-right:10px;'>
						"; if($tickets_list[$key]!=false)
							{
							print $tickets_list[$key];
							print 
							"
							</td>
							<td>
							<form action='admin_operations.php' >
							<input type=hidden name='button_delete_reseived_ticket' value=".$key.">
							<input type=image src='cross.gif' class='cross'  width='15px' alt='Удалить выданный билет'>
							</form>
							</td>
							"
							;}
						print "
					</td>
				</tr>
				";
		;}
	print
		"
		</table>
		</form>
		";
	die();
;}

// Удаление студентов
if(isset($_REQUEST["button_delete_students"]))	
{
	foreach($_REQUEST as $key => $value)
	{
		if(preg_match("/^student[0-9]+$/", $key))
		{
			$query_res = mysql_query("DELETE FROM users WHERE id ='".$value."'");
			if($query_res===false)
			{
				print "Ошибка удаления пользователя ".$value." ".$key.". Обратитесь к администратору.";
				die();
			;}	
		;}		
	;}
	header("Location:index.php?operation_result=".date("H:i")." Студенты%20успешно%20удалены.");
	//echo '<script type=text/javascript>$("#right_column").load("admin_operations.php", "show_students=true");</script>';
	die();	
;}

// Удаление выданного билета
if(isset($_REQUEST['button_delete_reseived_ticket']) && preg_match("/^[0-9]+$/", $_REQUEST['button_delete_reseived_ticket']))
{
	if(!mysql_query("UPDATE users SET ticket = '' WHERE id = '".$_REQUEST['button_delete_reseived_ticket']."'"))
	{
		die("Невозможно удалить выданный билет. Обратитесь к администратору.");
	;}
	header("Location:index.php?operation_result=".date("H:i")." Выданный%20билет%20удален.");
	//print "<script type=text/javascript>$('#right_column').load('admin_operations.php', 'show_students=true');</script>";
	die();	
;}

die("Некорректная операция.");
?>
