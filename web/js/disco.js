function assignEvents() {
	if ($('#form').length) {
		$("#form").validate({
			rules: {

			},
			ignore: ".ignore"
		});

		save = $('#saveDetail');
		if(save.length) {
			save.bind('click', function() {
				refreshAll();
				$(this).remove();
			});
		}

		$('#doit').bind('click', function(event) {
			if (!$('#form').valid()) {
				event.preventDefault();
			} 
		});
	}
	
	$('a.remove').bind('click', function(event) {		
		event.preventDefault();
		url = $(this).attr('href');
		$(this).remove();
		refreshDetail();
	});
}

function refreshDetail() {
	$.ajax({
		type: "GET",
		url: url,
		data: '',
		success: function(response){
			$('#anotherform').html(response);
		},
		error: function(xhr, ajaxOptions, thrownError) { }
	});
}

function refreshAll() {
	$.ajax({
		type: "POST",
		url: "http://http://localhost:8084/SongStock//disco/saveDetail",
		data: 'Nombre=' + $('#Nombre').val()
                + '&Imagen=' + $('#Imagen').val()
                + '&Precio=' + $('#Precio').val()
                + '&Stock=' + $('#Stock').val()
                + '&Anhio=' + $('#Anhio').val()
                + '&Genero=' + $('#Genero').is(':checked')
                + '&Interprete=' + $('#Interprete').val()
,
		success: function(response){
			$('#anotherform').html(response);
		},
		error: function(xhr, ajaxOptions, thrownError) { }
	});
}