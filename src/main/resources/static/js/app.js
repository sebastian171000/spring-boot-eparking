jQuery(document).ready($ => {
	
	
	var pathname = window.location.pathname;	
	console.log(pathname);
	if(pathname.indexOf("reservas/new") === 1 || pathname.indexOf("reservas/update") === 1){//INICIO JS PARA RESERVAS/NEW
		var sel = $('#estacionamiento');
		var log = $('#log');
		var log2 = $('#log2');
		var clases = $('select[name="estacionamiento"] :selected').attr('class');
		var split = clases.split(" ");
		var index = split[0];
		var espacios_disponibles = split[5];
		var nombresAdmin = split[6] + ' ' + split[7];
		var selectedOption = $(`#estacionamiento option:nth-child(${index})`);
		log.html(`<a href="/reservas/detalleEstacionamiento/${sel.val()}">Ver detalle ${selectedOption.text()}</a>`);
		log2.html(`${nombresAdmin} espacios disponibles`);
		//HORA_APERTURA
		var minTimeInput = split[1];
		//HORA_CIERRE
		var maxTimeInput = split[2];
		//ANTICIPACION_HORAS
		var anticipacionHoras = split[3];
		//ANTICIPACION_DIAS
		var anticipacionDias = parseInt(split[4])*-1 + 1;
		
		$('#estacionamiento').change(function(){
			$(location).attr('href',`${sel.val()}`);
			
		});

		
		var d = new Date();
		var hour = d.getHours();
		var mensajeDiaExtra = "";
		hour = minTimeInput.substr(0,2);
		$('.timepicker2').hide();
		$('.timepicker').timepicker({
		    timeFormat: 'HH:mm',
		    interval: 60,
		    minTime: hour + ':00',
		    maxTime: maxTimeInput,
		    defaultTime: hour + ':00',
		    startTime: minTimeInput,
		    dynamic: false,
		    dropdown: true,
		    scrollbar: true
		});
		$('#fechaReserva').change(function(){
			var hoy             = new Date();
			var valueFecha = $('#fechaReserva').val();
			var fechaFormulario = new Date(valueFecha);
			var log = $('.reservaHtml #validarFecha');
			//Si estamos 06 --> fechaFormulario.getDate() + 1 --> Se va a aceptar a partir del 06
			//Si estamos 06 --> fechaFormulario.getDate() + 0 --> Se va a aceptar a partir del 07
			//Si estamos 06 --> fechaFormulario.getDate() + -2 --> Se va a aceptar a partir del 09
			fechaFormulario.setDate(fechaFormulario.getDate() + anticipacionDias);
			hoy.setHours(0,0,0,0);  
			if(anticipacionDias == 1 && fechaFormulario.getDate() == hoy.getDate()){
				$('.timepicker').hide();
				$('.timepicker2').show();
				hour = d.getHours()  + parseInt(anticipacionHoras);
				console.log(hour)
				console.log(parseInt(maxTimeInput.substr(0,2)));
				if(hour > parseInt(maxTimeInput.substr(0,2))){
					
					mensajeDiaExtra = ", se agrego un día de más porque ya es muy tarde";
					hour = minTimeInput;
					anticipacionDias = anticipacionDias - 1;
				}
				$('.timepicker2').timepicker({
				    timeFormat: 'HH:mm',
				    interval: 60,
				    minTime: hour + ':00',
				    maxTime: maxTimeInput,
				    defaultTime: hour + ':00',
				    startTime: minTimeInput,
				    dynamic: false,
				    dropdown: true,
				    scrollbar: true
				});
				
			}else{
				$('.timepicker').show();
				$('.timepicker2').hide();
			}
			
			if (hoy <= fechaFormulario) {
				log.html("");
			}else {
				if(anticipacionDias == 1){
					log.html("La fecha debe ser de hoy a futuro");
					
					
				}else{
					log.html(`Se debe de reservar con ${anticipacionDias * -1 + 1} de anticipación`+mensajeDiaExtra);
					
					
				}
				
				
			}
		})
		
		
		$('.hora').on('click',function(){
			$('.nhoras').val('1');
			
		});
	
		$('.nhoras').change(function(){
			var nhoras = $(this).val();
			var hora = parseInt($('.timepicker').val().substr(0,2));
			var resta = parseInt(maxTimeInput.substr(0,2)) - hora;
			var hoy = new Date();
			var valueFecha = $('#fechaReserva').val();
			var fechaFormulario = new Date(valueFecha);
			
			if(anticipacionDias == 1 && (fechaFormulario.getDate() + anticipacionDias) == hoy.getDate()){
				hora = parseInt($('.timepicker2').val().substr(0,2));
				resta =  parseInt(maxTimeInput.substr(0,2)) - hora;
				
				
			}
	
				
			$(this).attr('max',resta);
		});
		$('#submit_nueva_reserva').on('click',function(e){
			var nhoras = $('.nhoras').val();
			var horaInputVal = $('.hora').val();
			var hoy = new Date();
			var valueFecha = $('#fechaReserva').val();
			var fechaFormulario = new Date(valueFecha);
			var errores = [];
			var listaErrores ='';
			if($('.timepicker2').is(':visible')) {
				$('.timepicker').val('');
	
			}
			if($('.timepicker').is(':visible')) {
				$('.timepicker2').val('');
	
			}
			
			fechaFormulario.setDate(fechaFormulario.getDate() + anticipacionDias);
			hoy.setHours(0,0,0,0);  
			if (valueFecha === '') {
				errores.push('fecha');
	            listaErrores += `<li>Error en la fecha</li>`;
			}
			
			if(nhoras == 0 || nhoras == ''){
				errores.push('nhoras');
	            listaErrores += `<li>Error en el N° de horas</li>`;
			}
			if(hoy > fechaFormulario){
				errores.push('fecha2');
				if(anticipacionDias == 1){
					listaErrores += `<li>La fecha debe ser de hoy a futuro</li>`;
					
				}else{
					listaErrores += `<li>Se debe de reservar con ${anticipacionDias * -1 + 1} de anticipación</li>`;
				}
			}
			
	
			if (errores.length != 0) {
	            Swal.fire({
	                title: 'Error!',
	                icon: 'error',
	                html: `<ul class="listaErrores">${listaErrores}</ul>`,
	                confirmButtonText: 'Aceptar',
	                customClass: 'swal-wide-registro',
	
	            })
				e.preventDefault();
	        }
	
		});
	}	//FIN DE JS PARA RESERVAS/NEW
	$('.btn-registrarse a').on('click', function() {
        var linkCliente = '/registroCliente';
        var linkAdmin = '/registroEstacionamiento';
        Swal.fire({
            title: '¿Cómo desea registrarse?',
            showDenyButton: true,
            showCancelButton: true,
            confirmButtonText: `Estacionamiento`,
            denyButtonText: `Cliente`,
            cancelButtonText: `Volver`,
            buttonsStyling: false,
            customClass: 'swal-wide'
        }).then((result) => {
            /* Read more about isConfirmed, isDenied below */
            if (result.isConfirmed) {
                window.location.href = linkAdmin;
            } else if (result.isDenied) {
                window.location.href = linkCliente;
            }
        })

    });
	
	
	
	
	//Subir imagen vista previa
    $(document).ready(function() {
        $('.upload').click(function() {
            $('.filefield').trigger('click');
        })
        $('.filefield').change(function() {
            if ($(this).val() != '') {
                $('.overlay_uploader').show();
                $('.spinner').show();
                readURL2(this);
            }
        })
    })

    function readURL2(input) {
        if (input.files[0].type == 'image/jpeg' || input.files[0].type == 'image/png') {
            $.each(jQuery(input)[0].files, function(i, file) {
                var reader = new FileReader();
                reader.onload = function(e) {
                    $('.overlay_uploader').hide();
                    $('.spinner').hide();
                    $('.img').attr("src", e.target.result);
                    $('.img-defecto').attr("src", "");
                    // $('.box').css('background-image', 'url(' + e.target.result + ')');
                }
                reader.readAsDataURL(input.files[0]);
            });
        } else {
            $('.overlay_uploader').hide();
            $('.spinner').hide();
            alert("Solo se permiten archivos .jpg y .png");
        }
    }
});
