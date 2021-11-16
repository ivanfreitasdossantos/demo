package com.example.demo.service;

import com.example.demo.dto.ClientDTO;
import java.util.List;


public interface ClientService {
    

    public ClientDTO saveClient(ClientDTO clientDTO);
    
    public List<ClientDTO> getClients();
    
    public ClientDTO getById(Long id);
    
    public ClientDTO updateClient(Long id, ClientDTO clientDTO);
    
    public void delete(Long id);
}
