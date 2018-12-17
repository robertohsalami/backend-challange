package com.invillia.acme.api.teste;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Assert;
import org.junit.Test;

import com.invillia.acme.modelo.Provedor;
import com.thoughtworks.xstream.XStream;

public class ProvedorRestServiceTeste {	
			
	@Test
	public void testaCriarProvedor() {		
		Client client = ClientBuilder.newClient();		 
		WebTarget target = client.target("http://localhost:8080");		
		Provedor provedor = new Provedor();
		provedor.setNome("Livraria Roberto Salami");
		provedor.setEndereco("Avenida Brasil 1511 - Araraquara/SP");		
		String xml = new XStream().toXML(provedor);
		Entity<String> entity = Entity.entity(xml, MediaType.APPLICATION_XML);
		Response response = target.path("/provedor").request().post(entity);
		Assert.assertEquals(201, response.getStatus());
		
	}
	
	@Test
	public void testeRecuperarUmProvedor() {		
		Client client = ClientBuilder.newClient();		 
		WebTarget target = client.target("http://localhost:8080");		
		String conteudo = target.path("/provedor/1").request().get(String.class);
		Provedor provedor = (Provedor) new XStream().fromXML(conteudo);
		Assert.assertEquals("Livraria Roberto Salami", provedor.getNome());		
	}
	
}
