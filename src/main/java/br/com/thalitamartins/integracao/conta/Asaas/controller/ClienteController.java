package br.com.thalitamartins.integracao.conta.Asaas.controller;

import br.com.thalitamartins.integracao.conta.Asaas.DTO.ClienteDTO;
import br.com.thalitamartins.integracao.conta.Asaas.service.ClienteService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/client")
public class ClienteController {

    private ClienteService clienteService;

    @PostMapping(value = "/cadastrar")
    public ResponseEntity createCliente(@RequestBody ClienteDTO cliente) {
        return clienteService.createCliente(cliente);
    }
}
