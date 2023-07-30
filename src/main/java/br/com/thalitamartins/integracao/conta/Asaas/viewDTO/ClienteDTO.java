package br.com.thalitamartins.integracao.conta.Asaas.viewDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO implements Serializable {
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
    private String city;
    private String externalReference;
    private String notificationDisabled;
    private String additionalEmails;
    private String municipallnscription;
    private String statelnscription;
    private String observations;
    private String groupName;
    private String company;
}
