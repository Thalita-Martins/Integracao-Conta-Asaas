package br.com.thalitamartins.integracao.conta.Asaas.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientesResponse {

    @JsonProperty("data")
    private List<ClienteResponse> clientes;
}
