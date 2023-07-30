package br.com.thalitamartins.integracao.conta.Asaas.client;

import br.com.thalitamartins.integracao.conta.Asaas.response.ClienteResponse;
import br.com.thalitamartins.integracao.conta.Asaas.response.ClientesResponse;
import br.com.thalitamartins.integracao.conta.Asaas.viewDTO.ClienteDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@Service
@Slf4j
public class ClientService {

    @Value("${asaas.apiKey}")
    private String apiKey;

    @Value("${asaas.dominio}")
    private String dominio;

    public ResponseEntity<?> createCliente(ClienteDTO clienteDTO) {
        log.info("cadastrando um novo cliente");
        try {
            String url = dominio;

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("access_token", apiKey);

            ObjectMapper objectMapper = new ObjectMapper();
            String jsonData = objectMapper.writeValueAsString(clienteDTO);

            HttpEntity<String> requestEntity = new HttpEntity<>(jsonData, headers);
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<ClienteResponse> responseEntity = restTemplate.exchange(url, POST, requestEntity,
                    ClienteResponse.class);


            return responseEntity;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Erro na requisição: " + e.getMessage());
        }
    }

    public ResponseEntity<?> findAllCliente(UriComponentsBuilder builders) {
        log.info("Listando todos os clientes cadastrados");
        try {
            String url = dominio;
            UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url + builders.toUriString());

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("access_token", apiKey);

            HttpEntity<String> requestEntity = new HttpEntity<>(headers);
            RestTemplate restTemplate = new RestTemplate();
            ParameterizedTypeReference<ClientesResponse> responseType = new ParameterizedTypeReference<>() {
            };
            ResponseEntity<ClientesResponse> responseEntity = restTemplate.exchange(builder.toUriString(), GET,
                    requestEntity, responseType);

            List<ClienteResponse> clientes = responseEntity.getBody().getClientes();

            return ResponseEntity.ok(clientes);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Erro na requisição: " + e.getMessage());
        }
    }

    public ResponseEntity<?> findByClientId(String id) {
        log.info("Listar o cliente de idAsaas[{}]", id);
        try {

            String url = dominio + "/" + id;

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("access_token", apiKey);
            HttpEntity<String> requestEntity = new HttpEntity<>(headers);
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<ClienteResponse> responseEntity = restTemplate.exchange(url, GET, requestEntity,
                    ClienteResponse.class);

            return responseEntity;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Erro na requisição: " + e.getMessage());
        }
    }
}
