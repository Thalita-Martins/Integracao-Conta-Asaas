package br.com.thalitamartins.integracao.conta.Asaas.service;

import br.com.thalitamartins.integracao.conta.Asaas.DTO.ClienteDTO;
import br.com.thalitamartins.integracao.conta.Asaas.response.ClienteResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class ClienteService {

    private final String apiKey = "$";
    public ResponseEntity createCliente(ClienteDTO clienteDTO) {
        try {
            String url = ("https://sandbox.asaas.com/api/v3/customers");

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("access_token", apiKey);
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonData = objectMapper.writeValueAsString(clienteDTO);
            HttpEntity<String> requestEntity = new HttpEntity<>(jsonData, headers);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<ClienteResponse> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, ClienteResponse.class);

            ObjectMapper objectMapperResponse = new ObjectMapper();

            return ResponseEntity.ok(objectMapperResponse.writeValueAsString(responseEntity.getBody()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Erro na requisição: " + e.getMessage());
        }
    }
}
