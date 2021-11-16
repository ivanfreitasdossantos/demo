package com.example.demo.service;

import com.example.demo.dto.ClientDTO;
import com.example.demo.mapper.ClientMapper;
import com.example.demo.model.Client;
import com.example.demo.repository.ClientRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.modelmapper.ModelMapper;

@Service
public class ClientServiceImp implements ClientService  {
    
	@Autowired
	ModelMapper mapper;
	
	@Autowired
	ClientMapper clientMapper;
    
    ClientRepository repository;
    
    ClientServiceImp(ClientRepository repository, ClientMapper clientMapper){
    	this.repository = repository;
    	this.clientMapper =  clientMapper;
    }
    
    public ClientDTO saveClient(ClientDTO clientDTO){
        return mapResponse(repository.save(mapRequest(clientDTO)));
    }
    
    public List<ClientDTO> getClients(){
    	
         return  clientMapper.toModel(repository.findAll());
    }
    
    public ClientDTO getById(Long id){
    	
    	Optional<Client> c =  Optional.ofNullable(repository.findById(id).orElseThrow(() ->  new IllegalArgumentException("Client not register!!")));
    	return c.map(this::mapResponse).get();
    }
    
    public ClientDTO updateClient(Long id, ClientDTO clientDTO){
    	
    	Client client = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Client not register!!"));
    	if (client == null || client.getId() == null) {
            throw new IllegalArgumentException("Client id cannot be null");
        }
         return mapResponse(repository.save(mapRequest(clientDTO)));
    }
    
    public void delete(Long id){
    	Client client = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Client not register!!"));
    	if (client == null || client.getId() == null) {
            throw new IllegalArgumentException("Client id cannot be null");
        }
        repository.delete(client);
   }
    
    
    private ClientDTO mapResponse(Client model) {
            return mapper.map(model, ClientDTO.class);
    }
    
    private Client mapRequest(ClientDTO dto) {
		return mapper.map(dto, Client.class);
	}
}
