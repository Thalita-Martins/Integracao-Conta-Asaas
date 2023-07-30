package br.com.thalitamartins.integracao.conta.Asaas.repository;

import br.com.thalitamartins.integracao.conta.Asaas.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ClienteRepository extends JpaRepository<Cliente,Long>, JpaSpecificationExecutor<Cliente> {
}
