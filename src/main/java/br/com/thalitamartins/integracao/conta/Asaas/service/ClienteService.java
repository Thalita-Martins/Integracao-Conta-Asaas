package br.com.thalitamartins.integracao.conta.Asaas.service;

import br.com.thalitamartins.integracao.conta.Asaas.client.ClientService;
import br.com.thalitamartins.integracao.conta.Asaas.domain.Cliente;
import br.com.thalitamartins.integracao.conta.Asaas.exception.ErroException;
import br.com.thalitamartins.integracao.conta.Asaas.repository.ClienteRepository;
import br.com.thalitamartins.integracao.conta.Asaas.response.ClienteResponse;
import br.com.thalitamartins.integracao.conta.Asaas.viewDTO.ClienteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import static java.util.Objects.nonNull;


@Service
public class ClienteService {

    private final ClientService clientService;

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClientService clientService, ClienteRepository clienteRepository) {
        this.clientService = clientService;
        this.clienteRepository = clienteRepository;
    }

    public ResponseEntity createCliente(ClienteDTO clienteDTO) {
        var cliente = new Cliente(clienteDTO);

        if (!(clienteDTO.getCpfCnpj().length() > 11)){
            cliente.setCpf(clienteDTO.getCpfCnpj());
        }else{
            cliente.setCnpj(clienteDTO.getCpfCnpj());
        }
        var clienteSalvo = clienteRepository.save(cliente);
        clienteDTO.setExternalReference(String.valueOf(cliente.getId()));
        clienteDTO.setNotificationDisabled("true");

        ResponseEntity<?> response = clientService.createCliente(clienteDTO);

        if(response.getBody() instanceof ClienteResponse){
            ClienteResponse clientesResponse = (ClienteResponse) response.getBody();
            clienteSalvo.setIdAsaas(clientesResponse.getId());
            clienteRepository.save(clienteSalvo);
        }

        return response;
    }

    public ResponseEntity findByClienteId(Long id) {
        var clienteRecuperado = clienteRepository.findById(id).orElseThrow(() -> new ErroException("Cliente não encontrado"));

        var path = clienteRecuperado.getIdAsaas();

        return clientService.findByClientId(path);
    }

    public ResponseEntity deleteClienteId(String id){
        return clientService.deleteCliente(id);
    }

    public ResponseEntity restaurarClienteId(String id){
        return clientService.restaurarCliente(id);
    }

    public ResponseEntity addParam(String name, String email, String cpfCnpj, String groupName, String externalReference, int offset, int limit){

        UriComponentsBuilder builder = UriComponentsBuilder.newInstance();

        if(nonNull(name)){
            builder.queryParam("cpfCnpj", name);
        }
        if(nonNull(email)){
            builder.queryParam("email", email);
        }
        if(nonNull(cpfCnpj)){
            builder.queryParam("cpfCnpj", cpfCnpj);
        }
        if(nonNull(groupName)){
            builder.queryParam("groupName", groupName);
        }
        if(nonNull(externalReference)){
            builder.queryParam("externalReference", externalReference);
        }
        builder.queryParam("offset", offset);
        builder.queryParam("limit", limit);

        return clientService.findAllCliente(builder);
    }
}
