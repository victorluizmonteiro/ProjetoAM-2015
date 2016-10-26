function aparecer(elemento){

	elemento = '#' + elemento;

	$(elemento).css("opacity", "0.9");
}

function valCNPJ(){
	
	
	var x = document.getElementById('txtCNPJ').value;
	
	while(x.indexOf('.')>0){	
		x = x.replace('.', '');
	}
	while(x.indexOf('/')>0){	
		x = x.replace('/', '');
	}
	while(x.indexOf('-')>0){	
		x = x.replace('-', '');
	}
	
	if (!validarCNPJ(x)){
		alert('CNPJ INVÁLIDO');
		document.getElementById('txtCNPJ').value = "";
		
	}
}

function valEmail(){
	if (!validaEMail()){
		alert("E-MAIL INVÁLIDO");
		document.getElementById('txtEmail').value = "";
	}
}
function validaEMail()
{
var x= document.getElementById('txtEmail').value;
var atpos=x.indexOf("@");
var dotpos=x.lastIndexOf(".");
if (atpos<1 || dotpos<atpos+2 || dotpos+2>=x.length)
{
return false;
}
return true;
}
function validarCNPJ(cnpj) {
	 
    cnpj = cnpj.replace(/[^\d]+/g,'');
 
    if(cnpj == '') return false;
     
    if (cnpj.length != 14)
        return false;
 
    // Elimina CNPJs invalidos conhecidos
    if (cnpj == "00000000000000" || 
        cnpj == "11111111111111" || 
        cnpj == "22222222222222" || 
        cnpj == "33333333333333" || 
        cnpj == "44444444444444" || 
        cnpj == "55555555555555" || 
        cnpj == "66666666666666" || 
        cnpj == "77777777777777" || 
        cnpj == "88888888888888" || 
        cnpj == "99999999999999")
        return false;
         
    // Valida DVs
    tamanho = cnpj.length - 2
    numeros = cnpj.substring(0,tamanho);
    digitos = cnpj.substring(tamanho);
    soma = 0;
    pos = tamanho - 7;
    for (i = tamanho; i >= 1; i--) {
      soma += numeros.charAt(tamanho - i) * pos--;
      if (pos < 2)
            pos = 9;
    }
    resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
    if (resultado != digitos.charAt(0))
        return false;
         
    tamanho = tamanho + 1;
    numeros = cnpj.substring(0,tamanho);
    soma = 0;
    pos = tamanho - 7;
    for (i = tamanho; i >= 1; i--) {
      soma += numeros.charAt(tamanho - i) * pos--;
      if (pos < 2)
            pos = 9;
    }
    resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
    if (resultado != digitos.charAt(1))
          return false;
           
    return true;
    
}
function validaDados(){


	var x = document.getElementById('txtS1').value;
	var y = document.getElementById('txtS2').value;


	if (x != y){
		alert('Senhas não conferem!');
		document.getElementById('txtS1').value = "";
		document.getElementById('txtS2').value = "";
		document.getElementById('txtS1').focus;
		return false
	}


	return true;
}
function ativaMascarasLogin(){


	
}
function ativaMascarasIndex(){
	

		$('#dtOcorrencia').mask('99/99/9999');
		$('#dtOcorreHonorario').mask('99/99/9999');
		$('#dtOcorreDespesa').mask('99/99/9999');
		

	
}
function pesquisaCEP(cep){
	cep = cep.replace('-', '');

	$.ajax({
		url: "http://api.postmon.com.br/v1/cep/" + cep,
		method: "GET",
		success: function(data){

			$('#slcEstado option:contains("'+data.estado+'")').attr('selected', 'selected');
			$("#txtCidade ").val(data.cidade);



			$("#txtBairro").val(data.bairro);
			$("#txtLogradouro").val(data.logradouro);
			$('#slcTipo').focus();
		}


	});
}
function validaNum(id){
	if (document.getElementById(id).value <= 0){
		document.getElementById(id).value = "";
	}
	
}

function SomenteNumero(e){
	 var tecla=(window.event)?event.keyCode:e.which;
	 if((tecla>47 && tecla<58)) return true;
	 else{
	 if (tecla==8 || tecla==0) return true;
	 else  return false;
	 }
}

function validaData(id){
	var x = document.getElementById(id).value;
	

	

	if (!validacaoData(x)){
		document.getElementById(id).value = "";
	}
	
	data = new Date();
	mes = data.getMonth();
	dia = data.getDate();
	mes = mes + 1;
	

	
	var comeco = x.indexOf('/')+1;
	var validaDia = x.substring(0, comeco-1);
	var validaMes = x.substring(comeco, comeco+2);
	
	if (validaDia > dia){
		document.getElementById(id).value = "";
	}
		
	
	if (mes != validaMes){
		document.getElementById(id).value = "";
	}
	
}

function validacaoData(data){
	
	if (data.length > 10) return false;
	
	if (data.indexOf('/')>0){
		while (data.indexOf('/')>0){
			data = data.replace('/', '')
		}
		var dia = data.substring(0, 2);
		var mes = data.substring(2, 4);
		var ano = data.substring(4, 8);
		
	}
	if (data.indexOf('-')>0){
	while (data.indexOf('-')>0){
		data = data.replace('-', '')
	}
		var dia = data.substring(6, 8);
		var ano = data.substring(0, 4);
		var mes = data.substring(4, 6);
	
	}
	
	if (dia > 31){
		return false;
	}
	if(mes >12){
		return false
	}
	if(ano > 2999){
		return false;
	}
	if (ano < 1899){
		return false;
	}
	return true;
		
}
function lancaHono (){
	document.getElementById('proSelected').innerHTML = document.getElementById('ProcessosHonorarios').value;
}
function lancaDispesa(){
	document.getElementById('despSelected').innerHTML = document.getElementById('ProcessosDespesas').value;
}

function menuClick(id){
	
	
	
	 x = document.getElementById(id).href;
	 
	 x = x.substring(x.indexOf('#'))
	 
	 
	
	if (x == '#processos'){
		
		document.getElementById(id).href = '#aberturaProcesso';
		
	}else if (x == '#aberturaProcesso'){
		
		document.getElementById(id).href = '#processos';
	} 

	
	if (x == '#honorarios'){
		document.getElementById(id).href = '#LancaHonorario';
	}else if (x == '#LancaHonorario'){
		document.getElementById(id).href = '#honorarios'; 
	}
	
	
	if (x == '#despesas'){
		document.getElementById(id).href = '#LancaDespesa';
	}else if (x == '#LancaDespesa'){
		document.getElementById(id).href = '#despesas';
	}
	
	
}
		
