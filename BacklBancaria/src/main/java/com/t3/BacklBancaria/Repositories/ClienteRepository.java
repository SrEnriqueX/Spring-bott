package com.t3.BacklBancaria.Repositories;

import com.t3.BacklBancaria.Models.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity,Long> {

    @Query("SELECT c FROM ClienteEntity c WHERE c.correo = :correo AND c.contraseña = :password")
    ClienteEntity buscarClienteLogin(@Param("correo") String correo, @Param("password") String password);
}
