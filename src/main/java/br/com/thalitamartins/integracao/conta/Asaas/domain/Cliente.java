package br.com.thalitamartins.integracao.conta.Asaas.domain;

import br.com.thalitamartins.integracao.conta.Asaas.viewDTO.ClienteDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private static final String SEQ_GENERATOR = "cliente_id_seq_gen";
    private static final String SEQ_NAME = "cliente_id_seq";

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_GENERATOR)
    @SequenceGenerator(name = SEQ_GENERATOR, sequenceName = SEQ_NAME, allocationSize = 1)
    private Long id;

    private String nome;

    private String cpf;

    private String cnpj;
    private String email;
    private String telefone;
    private String celular;
    private String logradouro;
    private Long numero;
    private String complemento;
    private String bairro;
    private String cep;

    private String cidade;
    private String emailAdicional;
    private String inscricaoMunicipal;
    private String inscricaoEstadual;
    private String observacoes;
    private String nomeGrupo;

    private String idAsaas;
    private Boolean ativo;

    public Cliente(ClienteDTO clienteDTO){
        this.nome = clienteDTO.getName();
        this.email = clienteDTO.getEmail();
        this.telefone = clienteDTO.getPhone();
        this.celular = clienteDTO.getMobilePhone();
        this.logradouro = clienteDTO.getAddress();
        this.numero = Long.valueOf(clienteDTO.getAddressNumber());
        this.complemento = clienteDTO.getComplement();
        this.bairro = clienteDTO.getProvince();
        this.cep = clienteDTO.getPostalCode();
        this.cidade = clienteDTO.getCity();
        this.emailAdicional = clienteDTO.getAdditionalEmails();
        this.inscricaoMunicipal = clienteDTO.getMunicipallnscription();
        this.inscricaoEstadual = clienteDTO.getStatelnscription();
        this.observacoes = clienteDTO.getObservations();
        this.nomeGrupo = clienteDTO.getGroupName();
        this.ativo = true;
    }
}
