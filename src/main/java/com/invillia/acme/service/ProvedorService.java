package com.invillia.acme.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invillia.acme.modelo.Provedor;
import com.invillia.acme.repository.ProvedorRepository;
import com.thoughtworks.xstream.XStream;

@Service
public class ProvedorService {
	
	@Autowired
	private ProvedorRepository repository;	
		
	public void salvar(Provedor provedor) {
		repository.save(provedor);
	}

	public Optional<Provedor> recuperar(long id) {
		Optional<Provedor> provedor = repository.findById(id);
		return provedor;
	}

	public String atualizarEndereco(long id, String endereco) {
		Optional<Provedor> provedor = recuperar(id);
		if(provedor.isPresent()) {
			Provedor provedorUpdate = provedor.get();
			provedorUpdate.setEndereco(endereco);
			repository.save(provedorUpdate);
			String xml = new XStream().toXML(provedorUpdate);
			return xml;
		}
		return "Nao foi possivel atualizar o endereco";
	}

}
