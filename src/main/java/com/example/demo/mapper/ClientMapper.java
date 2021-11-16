package com.example.demo.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.example.demo.dto.ClientDTO;
import com.example.demo.model.Client;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClientMapper {

    private ModelMapper modelMapper = new ModelMapper();

    public ClientDTO toModel (Client entity){

        if (entity != null)
           return modelMapper.map(entity, ClientDTO.class);

        return null;
    }

    public List<ClientDTO> toModel (List<Client> entities){
        List<ClientDTO> models = new ArrayList<ClientDTO>();
        for (Client entity : entities) {
        	models.add(modelMapper.map(entity, ClientDTO.class));
        }
        return models;
    }

}
