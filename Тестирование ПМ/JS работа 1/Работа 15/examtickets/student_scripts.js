	reload_page = function ()
		{
		document.location.href="index.php";
		;}
	
	$(document).ready(function()
	{	
		
		// Отображаем все предметы при загрузке
		$("#right_column").load("student_operations.php", "show_subjects=true", false);

		// Получить билет
		get_ticket = function(this_object)
			{
			subject_name = document.getElementById(this_object.id).value;
			if(confirm("Вы действительно хотите получить билет по предмету\n\""+subject_name+"\"? \n После получения билета, выбрать билет по другому предмету будет невозможно."))
				{
				id = this_object.id.substring(2);
				$("#right_column").load("student_operations.php", "get_ticket=true&subj_id="+id);
				;}
			;}
			
	});