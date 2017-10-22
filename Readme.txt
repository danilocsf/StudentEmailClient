1 - Explicações em geral

  1.1 - Sobre o mock	 
        Foi criado um mock no SoapUI representando os webservices que retornam os dados dos alunos e as notas dos alunos.
        O mock que retorna os dados dos alunos roda na porta 8088 e o que retorna as notas do aluno roda na porta 8089.
        Todos os alunos do mock possuem o mesmo e-mail: appstudentemailsender@gmail.com				
        Foram criadas 3 consultas para alunos específicos com os seguintes CPFs: 123 , 456 e 789            

  1.2 - Sobre os projetos client e server	
        A solução é composta por 2 projetos : StudentEmailSender (server) e StudentEmailClient(client)
        O server roda na porta 8080 e o client na porta 8888
     
  1.3 - Localização dos projetos client e server no GitHub	      
        Server: https://github.com/danilocsf/StudentEmailSender
        Client : https://github.com/danilocsf/StudentEmailClient			

  1.4 - E-mail para teste
        For criado um email para teste da aplicação.
        Apesar da aplicação ter sido testada com diferentes e-mails, a versão final utiliza o mesmo e-mail para envio e recebimento.
        Os dados do e-mail são os seguintes: login: appstudentemailsender@gmail.com   senha:123EmailSender

  1.5 - Problemas conhecidos
        Caso o e-mail responsável pelo envio seja do domínio gmail, o mesmo deve permitir o acesso a apps menos seguros.
        Portanto recomendo manter o e-mail criado para testes. 						
        Caso o antivírus  esteja escaneando os e-mails enviados, a seguinte exceção pode ocorrer:
        Mail server connection failed; nested exception is javax.mail.MessagingException: Could not convert socket to TLS;\n  nested exception is:\n\tjavax.net.ssl.SSLHandshakeException:                             sun.security.validator.ValidatorException: PKIX path building failed: sun.security.provider.certpath.SunCertPathBuilderException: unable to find valid certification path to requested target. Failed messages:                    javax.mail.MessagingException: Could not convert socket to TLS;\n  nested exception is:\n\tjavax.net.ssl.SSLHandshakeException: sun.security.validator.ValidatorException: PKIX path building failed:                            sun.security.provider.certpath.SunCertPathBuilderException: unable to find valid certification path to requested target	               
    
  1.6 - Versão do Java
        A aplicação foi compilada com a versão 8 do Java.
===============================================================================================			
2 - Passos para execução do projeto
 	 	
  2.1 - Importação e execução do mock
        O arquivo Mock_alunos.xml , localizado na raíz dos projetos, deve ser importado no SoapUI. (o mesmo arquivo encontra-se nos projetos client e server) 
        Os serviços AlunoService e NotasAlunosService devem ser iniciados e permanecerem ativos durante toda a execução.
    
  2.2 - Execução do server
        A aplicação server (StudentEmailServer) deve ser executada primeiro.
        O jar StudentEmailSender-0.0.1-SNAPSHOT.jar , localizado em target deve ser executado da seguinte maneira através do prompt de comando:
        java -jar StudentEmailSender-0.0.1-SNAPSHOT.jar	 
      
  2.3 - Execução do client
        A aplicação client (StudentEmailClient) deve ser executada após o server terminar de subir por completo.
        O jar StudentEmailClient-0.0.1-SNAPSHOT.jar, localizado em target deve ser executado da seguinte maneira através do prompt de comando:
        java -jar StudentEmailClient-0.0.1-SNAPSHOT.jar
       
  2.4 - Informações adicionais
        Ao executar o client, o processo de envio de e-mails será iniciado automaticamente.
        Será possível acompanhar a execução através de logs tanto no prompt de comando do client quanto do server.
        Os e-mails enviados poderão ser verificados no e-mail informado no item 1.4.   	     
		
	