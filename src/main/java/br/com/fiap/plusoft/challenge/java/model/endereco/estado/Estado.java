package br.com.fiap.plusoft.challenge.java.model.endereco.estado;

import br.com.fiap.plusoft.challenge.java.model.endereco.cidade.Cidade;
import br.com.fiap.plusoft.challenge.java.model.endereco.pais.Pais;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "INOV_TB_ESTADO")
@SequenceGenerator(name = "inov_estado_seq", sequenceName = "inov_tb_estado_seq", allocationSize = 1, initialValue = 1)
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inov_estado_seq")
    @Column(name = "cd_estado")
    private Long codigo;
    @Column(name = "nm_estado", length = 50, nullable = false)
    private String nome;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cd_pais")
    private Pais pais;

    @OneToMany(mappedBy = "estado")
    private List<Cidade> cidades;
}
