package com.invillia.acme.api;

import java.net.URI;
import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.invillia.acme.modelo.Provedor;
import com.invillia.acme.service.ProvedorService;
import com.thoughtworks.xstream.XStream;

@Component
@Path("/provedor")
public class ProvedorRestService {
		
	@Autowired
	private ProvedorService service;	
	
	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_XML)	
	public String recuperar(@PathParam("id") long id) {
		Optional<Provedor> provedor = service.recuperar(id);
		Provedor prov = provedor.get();
		String xml = new XStream().toXML(prov);		
		return xml;
	}
	
	@Path("{id}")
	@PUT
	@Produces(MediaType.APPLICATION_XML)	
	public String atualizarEndereco(@PathParam("id") long id, String endereco) {
		return service.atualizarEndereco(id,endereco);		
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)	
	public Response criaProvedor(String conteudo) {		
		Provedor provedor = (Provedor)new XStream().fromXML(conteudo);
		service.salvar(provedor);
		URI uri = URI.create("/provedor"+provedor.getId());
		return Response.created(uri).build();
	}


}
