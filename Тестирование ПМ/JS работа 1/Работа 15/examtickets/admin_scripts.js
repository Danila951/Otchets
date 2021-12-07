	reload_page = function ()
		{
		document.location.href="index.php";
		;}
	
	$(document).ready(function()
	{	
		// Прячем все подпункты
		$(".menu_subpoint").slideUp(10);
		
		// Отображаем все предметы при загрузке
		$("#right_column").load("admin_operations.php", "show_subjects=true");
		
		// Функция удаления предмета
		delete_subject = function(this_object)
			{
			subject_name = document.getElementById("id"+this_object.id).value;
			if(confirm("Вы действительно хотите удалить предмет \""+subject_name+"\"?"))
				{
				$("#right_column").load("admin_operations.php", "delete_subject="+this_object.id);
				;}
			;}

		
		// Функция входа в билеты
		show_tickets = function(this_object)
			{
			id = this_object.id.substring(2);
			$("#right_column").load("admin_operations.php", "subj_id="+id+"&show_tickets=true");
			;}
		
		// Функция отображения подпунктов
		function show_subpoint()
			{
			var children = $(this).children();	// Получаем массив дочерних объектов
			$(children[1]).slideDown(1000);		// Скрываем дочерний объект
			;}
		
		// Функция скрытия подпунктов
		function hide_subpoint(event)
			{
			var parents = $(this).parents();	//Получаем массив родительских объектов
			$(parents[0]).slideUp(1000);		//Скрываем родительский объект
			if(event){event.stopPropagation();}			// Останавливаем всплытие, чтобы не вызвался родительский онклик
			;}
			
		$(".menu_point").click(show_subpoint);				// Прикрепляем функцию отображения подпунктов ко всем пунктам
		$(".hide_subpoint_button").click(hide_subpoint);	// Прикрепляем функцию скрытия подпунктов ко всем кнопкам "Отмена"

		// Функция отправки нового предмета
		$("#button_add_subject").mousedown(function()
											{
											subj_name = document.getElementById("subj_name_to_add").value;
											subj_name=encodeURI(subj_name);
											$("#right_column").load("admin_operations.php", "add_subject=true&subj_name="+subj_name);
											document.getElementById("subj_name_to_add").value = "";
											;})
											
		// Функция отображения списка предметов
		$("#button_show_subjects").click(function()
											{
											$("#right_column").load("admin_operations.php", "show_subjects=true");
											;})	
											
		// Функция отображения списка студентов
		$("#button_show_students").click(function()
											{
											$("#right_column").load("admin_operations.php", "show_students=true");
											;})	
			
	});