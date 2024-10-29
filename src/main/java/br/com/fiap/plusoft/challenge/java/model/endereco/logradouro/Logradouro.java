package br.com.fiap.plusoft.challenge.java.model.endereco.logradouro;

import br.com.fiap.plusoft.challenge.java.model.endereco.EnderecoCliente;
import br.com.fiap.plusoft.challenge.java.model.endereco.bairro.Bairro;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "INOV_TB_LOGRADOURO")
@SequenceGenerator(name = "inov_logradouro_seq", sequenceName = "inov_tb_logradouro_seq", allocationSize = 1, initialValue = 1)
public class Logradouro {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inov_logradouro_seq")
    @Column(name = "cd_logradouro")
    private Long codigo;
    @Column(name = "nm_logradouro", length = 100, nullable = false)
    private String nome;
    @Column(name = "nr_cep", length = 8, nullable = false)
    private String cep;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cd_bairro")
    private Bairro bairro;

    @OneToMany(mappedBy = "logradouro")
    private List<EnderecoCliente> enderecoClientes;
}
