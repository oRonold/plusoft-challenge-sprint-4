package br.com.fiap.plusoft.challenge.java.model.endereco.bairro;

import br.com.fiap.plusoft.challenge.java.model.endereco.cidade.Cidade;
import br.com.fiap.plusoft.challenge.java.model.endereco.logradouro.Logradouro;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "INOV_TB_BAIRRO")
@SequenceGenerator(name = "inov_bairro_seq", sequenceName = "inov_tb_bairro_seq", allocationSize = 1, initialValue = 1)
public class Bairro {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inov_bairro_seq")
    @Column(name = "cd_bairro")
    private Long codigo;
    @Column(name = "nm_bairro", length = 100, nullable = false)
    private String nome;
    @Column(name = "nm_zona", length = 20)
    private String zona;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cd_cidade")
    private Cidade cidade;

    @OneToMany(mappedBy = "bairro")
    private List<Logradouro> logradouros;
}
