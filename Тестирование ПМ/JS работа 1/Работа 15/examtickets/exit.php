<?php
//ini_set('display_errors', 1);
//error_reporting(E_ALL);
setcookie("id");
setcookie("hash");
$_COOKIE["id"] = false;
$_COOKIE["hash"] = false;
unset ($_COOKIE);
?>
<script type=text/javascript>
document.location.href="auth.php";
</script>
