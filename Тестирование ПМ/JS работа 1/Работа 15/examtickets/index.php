<?php
//ini_set('display_errors', 1);
//error_reporting(E_ALL);
header("Content-Type: text/html; charset=utf8");
include("functions.php");
include("globals.php");

// Проверяем аутентификацию и получаем пользовательские данные
if(isset($_COOKIE['id'])&&isset($_COOKIE['hash']))
{
	$result_mass = auth_check($_COOKIE['id'], $_COOKIE['hash'], $HOST, $DBLOGIN, $DBPASSWORD, $DBNAME);
;}
else 
{
	header("Location: auth.php");
	die();
;}

?>


<html>
<head>
	<title>Главная страница</title>
	<link rel="stylesheet" type="text/css" href="main.css">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<script src="jquery-1.10.1.js"  type="text/javascript"></script>
	<?php if($result_mass['access_level'] == 1): ?>
		<script type="text/javascript" src="admin_scripts.js"></script>
	<?php endif; ?>
	<?php if($result_mass['access_level'] == 5): ?>
		<script type="text/javascript" src="student_scripts.js"></script>
	<?php endif; ?>
</head>
<body>
<div id=main_box>
<a href="index.php"><img src="logo.png" id=logo></a><br>
<table id="main_table">
	<tr>
		<td id="inform_panel" colspan=2>
		<?php 	// Если загрузили билеты, то сообщаем об успехе
		if(isset($_REQUEST['operation_result']))
		{
		print $_REQUEST['operation_result'];
		;}
		
		if(isset($_REQUEST['upload_error']))
			{
			print "Произошла ошибка загрузки файла. Error :".$_REQUEST['upload_error']."<br> ";
			if($_REQUEST['upload_error'] === "2")
				{
				print "Файл не должен превышать размер в 8Мб";
				;}
			;}
		?>
		</td>
	</tr>
	<tr>
		<td id="left_column">
			<h1 id="user_name"><?php print $result_mass['fio'];?></h1><br><br>
			<?php if($result_mass['access_level'] == 1): ?>
				
				<div class="menu_point" id="show_subjects">
					<input type=button id="button_show_subjects" class='submit menu_point_button' value='Список предметов'>
				</div>
				<div class="menu_point" id="menu_point_add_subject">
					<input type=button class='submit menu_point_button' value='Добавить предмет'>
					<div class="menu_subpoint" id="menu_subpoint_add_subject">
							Введите название предмета <br>
							<input type=text size=30 id="subj_name_to_add"><br>
							<input type=button class="hide_subpoint_button submit" value="Добавить" id="button_add_subject">
							<input type=button class="hide_subpoint_button submit" value="Отмена">
					</div>
				</div>
				<div class="menu_point" id="show_students">
					<input type=button id="button_show_students" class='submit menu_point_button' value='Список студентов'>
				</div>
			<?php endif; ?>
			<?php if($result_mass['access_level'] == 5): ?>				
				
			<?php endif; ?>			
			<form action="exit.php">
				<input type=submit class=submit name=exit value="Выход"><br>
			</form>
		</td>		
		
		<td id="right_column">
			
		</td>
	</tr>
</table>

</div>
</body>
</html>
