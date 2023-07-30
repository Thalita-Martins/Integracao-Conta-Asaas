package br.com.thalitamartins.integracao.conta.Asaas.controller;

import br.com.thalitamartins.integracao.conta.Asaas.exception.ErroException;
import br.com.thalitamartins.integracao.conta.Asaas.viewDTO.ClienteDTO;
import br.com.thalitamartins.integracao.conta.Asaas.service.ClienteService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/cliente")
public class ClienteController {

    private ClienteService clienteService;

    @PostMapping(value = "/cadastrar")
    public ResponseEntity createCliente(@RequestBody ClienteDTO cliente) {
        try {
            return clienteService.createCliente(cliente);
        } catch (ErroException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/listar")
    public ResponseEntity findAllCliente(@RequestParam(value = "name", required = false) String name,
                                         @RequestParam(value = "email", required = false) String email,
                                         @RequestParam(value = "cpfCnpj", required = false) String cpfCnpj,
                                         @RequestParam(value = "groupName", required = false) String groupName,
                                         @RequestParam(value = "externalReference", required = false) String externalReference,
                                         @RequestParam(value = "offset") int offset,
                                         @RequestParam(value = "limit") int limit) {
        try {
            return clienteService.addParam(name, email, cpfCnpj, groupName, externalReference, offset, limit);
        } catch (ErroException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/listar/{id}")
    public ResponseEntity fndByClienteId(@PathVariable("id") Long id) {
        try {
            return clienteService.findByClienteId(id);
        } catch (ErroException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
