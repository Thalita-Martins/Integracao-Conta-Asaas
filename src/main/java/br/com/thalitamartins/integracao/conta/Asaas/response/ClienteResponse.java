package br.com.thalitamartins.integracao.conta.Asaas.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteResponse implements Serializable {

    private String object;
    private String id;
    private String dateCreated;
    private String name;
    private String cpfCnpj;
    private String email;
    private String phone;
    private String mobilePhone;
    private String address;
    private String addressNumber;
    private String complement;
    private String province;
    private String postalCode;
    private String personType;
    private String deleted;
    private String externalReference;
    private String notificationDisabled;
    private String additionalEmails;
    private String city;
    private String state;
    private String observations;
}
