#Projeto SOAP Estoque

<h1>Serviços Web são utilizados para integrar sistemas</h1>

Geralmente quando temos aplicaçãoes externas necessitando de informações, oferecemos serviços com dados requisitados

<h1>SOAP é XML que trafega em cima do protocolo HTTP</h1>

Os dados são externalizados em XML, através do protocolo HTTP POST 

<h1>o JRE já vem com o JAX-WS (Metro) para usar SOAP</h1>

A JVM já vem preparada para executar os serviços SOAP, não necessitando de um servidor para tal

<h1>o contrato do serviço é o WSDL que também é um XML</h1>

O WSDL é o xml descritivo do serviço disponibilizado, contendo todas as informações do serviço

<h1>uma mensagem SOAP possui um Envelope e um Body,</h1>

`Envelope` que indica todos os dados do xml, e o `Body` que em si contém os dados do serviço

<h1>na mensagem SOAP o Header é opcional</h1>

`header` contém informações adicionais(opcionais) da requisição ao serviço

#SoapUI
* Criando projeto no SoapUi com a requisição com dados descritivos do serviço

	http://localhost:8080/estoquews?wsdl
	
* Será gerado um xml com os dados para a realização da requisição

		<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ws="http://ws.estoque.caelum.com.br/">
		   <soapenv:Header/>
		   <soapenv:Body>
		    <ws:getItens/>
		   </soapenv:Body>
	</soapenv:Envelope>
	
* Ao se realizar a requisição com os dados acima é gerado o retorno da requisição com dados em XML

	<S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/">
	   <S:Body>
	      <ns2:todosItensResponse xmlns:ns2="http://ws.estoque.caelum.com.br/">
	         <return>
	            <codigo>GAL</codigo>
	            <nome>Galaxy Tab</nome>
	            <quantidade>3</quantidade>
	            <tipo>Tablet</tipo>
	         </return>
	         ...
	         <return>
	            <codigo>IP4</codigo>
	            <nome>IPhone 4 C</nome>
	            <quantidade>7</quantidade>
	            <tipo>Celular</tipo>
	         </return>
	      </ns2:todosItensResponse>
	   </S:Body>
	</S:Envelope>
	
* Para criar um serviço temos que anotá-lo com `@WebService`

	@WebService
	public class EstoqueWS {}
	
* Disponibilizando um serviço

	EstoqueWS service = new EstoqueWS();
	String url = "http://localhost:8080/estoquews";
	Endpoint.publish(url, service);

* `EstoqueWS service = new EstoqueWS();` precisamos do serviço
* `String url = "http://localhost:8080/estoquews";` caminho para acessar o serviço
* `Endpoint.publish(url, service);` disponibilizando o serviço