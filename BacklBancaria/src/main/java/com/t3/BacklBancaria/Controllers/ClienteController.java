package com.t3.BacklBancaria.Controllers;

import com.t3.BacklBancaria.DTO.JsendResponse;
import com.t3.BacklBancaria.Models.ClienteEntity;
import com.t3.BacklBancaria.Models.userLoginDTO.UserLogin;
import com.t3.BacklBancaria.Services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="api/v1/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/Iniciar-Sesion")
    public ResponseEntity<JsendResponse<ClienteEntity>> iniciarSesion(@Valid  @RequestBody UserLogin userLogin){

        try{
            ClienteEntity cliente = clienteService.buscarClienteLogin(
                    userLogin.getCorreo(),
                    userLogin.getPassword()
            );

            if(cliente != null){
                return ResponseEntity.ok( new JsendResponse<>("success",cliente));
            }
            else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new JsendResponse<>("fail", "Credenciales incorrectas"));
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new JsendResponse<>("error", "Ocurrió un error al inciar sesion: " + e.getMessage()));
        }
    }
}
