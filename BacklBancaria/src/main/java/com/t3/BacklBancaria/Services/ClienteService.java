package com.t3.BacklBancaria.Services;

import com.t3.BacklBancaria.Models.ClienteEntity;
import com.t3.BacklBancaria.Repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    public ClienteEntity buscarClienteLogin(String correo, String password){
        return clienteRepository.buscarClienteLogin(correo,password);
    }
}
