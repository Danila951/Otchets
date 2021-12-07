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
	<title>Аутентификация пользователя</title>
	<link rel="stylesheet" type="text/css" href="main.css">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<style>
	</style>	
</head>
<body>
<div id=main_box>
<img src="logo.png" id=logo>
<table id=reg_auth_table>
<tr><td colspan=2 align=center>Аутентификация пользователя</td></tr>
<form name="auth_form" action="check_auth.php" method=POST>
<tr><td>Логин</td><td><input type=text name=login required></td></tr>
<tr><td>Пароль</td><td><input type=password name=password required></td></tr>
<tr><td ><input type=submit name=submit class=submit value="Вход"></td><td align=right><A href=reg.php>Регистрация</a></td></tr>
</table>
<img id=loading src=loading.gif><br>
<?php
if(isset($_COOKIE["error_auth"]))
{
print "<p style='color:red;'>".$_COOKIE["error_auth"]."</p>";
;}
?>
</div>




<script type="text/javascript">
auth_form.onsubmit=function(){document.getElementById("loading").style.visibility="visible";};
</script>
</body>
</html>
