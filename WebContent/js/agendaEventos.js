$(document).ready(function() {
  
  var data = new Date();
  /*PEGA O DIA*/
  var dia  = data.getDate(); 
  /*PEGA O MÊS E SOMA MAIS UM PARA CHEGAR NO MÊS ATUAL(JAVASCRIPT O MÊS COMEÇA EM 0(iNDEX DO MÊS))*/
  var mes  = (data.getMonth() + 1);
  /*PEGA O ANO*/
  var ano  = data.getFullYear();
  /*MONTA A DATA*/
  var dataAtual = ano +'-'+mes+'-'+dia;
  
 $.ajax({
    type: "POST",
    url: "controller.do",
    data: { command: "ListarFeriadosPuxar" },
    success: function(data){
     console.log(data); //lista de feriados vindo da controller (banco)
     
     var feriados = [ //criacao da lista que sera passada para o calendario
      {
             title: 'Business Lunch',
             start: '2018-03-03T13:00:00',
             constraint: 'businessHours'
           },
           {
             title: 'Meeting',
             start: '2018-03-13T11:00:00',
             constraint: 'availableForMeeting', // defined below
             color: '#257e4a'
           },
           {
             title: 'Conference',
             start: '2018-03-18',
             end: '2018-03-20'
           },
           {
             title: 'Party',
             start: '2018-03-29T20:00:00'
           },

           // areas where "Meeting" must be dropped
           {
             id: 'availableForMeeting',
             start: '2018-03-11T10:00:00',
             end: '2018-03-11T16:00:00',
             rendering: 'background'
           },
           {
             id: 'availableForMeeting',
             start: '2018-03-13T10:00:00',
             end: '2018-03-13T16:00:00',
             rendering: 'background'
           },

           // red areas where no events can be dropped
           {
             start: '2018-03-24',
             end: '2018-03-28',
             overlap: false,
             rendering: 'background',
             color: '#ff9f89'
           },
           {
             start: '2018-03-06',
             end: '2018-03-08',
             overlap: false,
             rendering: 'background',
             color: '#ff9f89'
           }
     ];
    
    for (var i = 0; i < data.length; i++) {  //percorrendo cada feriado
     
     var dtFinal = '';
     
     if(data[i].dFinal){
      dtFinal = data[i].dFinal.split('/');
      dtFinal = dtFinal[2] + "-" + dtFinal[1] + "-" + dtFinal[0];
     }
     
     var dtInicio = data[i].inicio.split('/');
      dtInicio = dtInicio[2] + "-" + dtInicio[1] + "-" + dtInicio[0];
      
     var feriado = { //criando mais um objeto 
      title: data[i].feriado,
      start: dtInicio,
      end: dtFinal,
      overlap: false,
      //rendering: 'background',
      color: '#9f89ff'
     };
     
     feriados.push(feriado); //inserindo o feriado na lista de feriados do calendario
    }
    
    return setCalendar(feriados);
    }
 }); 
 
 function setCalendar(feriados){
  console.log(feriados);
  
    $('#div-agenda-eventos').fullCalendar({
      header: {
        left: 'prev,next today',
        center: 'title',
        right: 'month,agendaWeek,agendaDay,listMonth'
      },
      
      defaultDate: new Date().toJSON().slice(0,10),
      navLinks: true, // can click day/week names to navigate views
      businessHours: true, // display business hours
      editable: true,
      displayEventTime : false,
      eventLimit: true, // allow "more" link when too many events
      eventClick: function(event) {
			
			$('#visualizar #id').text(event.id);
			$('#visualizar #id').val(event.id);
			$('#visualizar #nome').text(event.title);
			$('#visualizar #nome').val(event.title);
			$('#visualizar #inicio').text(event.start.format('DD/MM/YYYY HH:mm:ss'));
			$('#visualizar #inicio').val(event.start.format('DD/MM/YYYY HH:mm:ss'));
			$('#visualizar #fim').text(event.end.format('DD/MM/YYYY HH:mm:ss'));
			$('#visualizar #fim').val(event.end.format('DD/MM/YYYY HH:mm:ss'));
			$('#visualizar').modal('show');
			return false;

		},

      
      selectable:true,
      selecHelper:true,
      select: function(start, end){
			$('#cadastrarModal #inicio').val(moment(start).format('DD/MM/YYYY HH:mm:ss'));
			$('#cadastrarModal #fim').val(moment(end).format('DD/MM/YYYY HH:mm:ss'));
			$('#cadastrarModal').modal('show');						
		},
      
      lang:'pt-br',
		buttonText: {
		    today: 'Hoje',
		    month: 'M\u00eas',
		    week: 'Semana',
		    day: 'Dia'
		},
		
      events: feriados //setando a lista de feriados no calendario
      
      
    });
    
 } 
 

  });