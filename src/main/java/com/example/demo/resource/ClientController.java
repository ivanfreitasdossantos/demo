package com.example.demo.resource;

import com.example.demo.dto.ClientDTO;
import com.example.demo.service.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseStatus;


@Slf4j
@RestController
@RequestMapping("/api/clients")
@Api(value="teste")
public class ClientController {
    
	@Autowired
	ModelMapper mapper;
	
    @Autowired
    ClientService service; 
    

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Register Client")
    public ClientDTO cadastroCliente(@RequestBody @Valid ClientDTO clientDTO) {
    	 
    	log.info("creating new client");
    	ClientDTO savedClient = service.saveClient(clientDTO);
         
        log.info("client saved with id: {}", savedClient.getId());
 		return mapper.map(savedClient, ClientDTO.class);
      
    }
    
    @GetMapping
    @ApiOperation(value = "Get all Clients")
    public List<ClientDTO>  getClients() {
    	 log.info("list all clients");
         return service.getClients();
      
    }
    
    
    @GetMapping("{id}")
    @ApiOperation(value = "Get details for client")
	public ClientDTO get(@PathVariable Long id) {
		log.info(" obtaining details for client id: {} ", id);
		return service.getById(id);
	}
    
    @PutMapping("{id}")
    @ApiOperation(value = "Update Client")
    public ClientDTO updateClient(@PathVariable Long id, @RequestBody @Valid ClientDTO clientDTO) {
    	log.info(" updating client of id: {} ", id);
        return service.updateClient(id, clientDTO);
      
    }
    
    @DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete Client")
	public void delete(@PathVariable Long id) {
		log.info(" deleting client of id: {} ", id);
		service.delete(id);
	}
    
    
}
