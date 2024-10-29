package br.com.fiap.plusoft.challenge.java.model.endereco.pais;

import br.com.fiap.plusoft.challenge.java.model.endereco.estado.Estado;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor

@Entity
@Table(name = "INOV_TB_PAIS")
@SequenceGenerator(name = "inov_pais_seq", sequenceName = "inov_tb_pais_seq", allocationSize = 1, initialValue = 1)
public class Pais {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inov_pais_seq")
    @Column(name = "cd_pais")
    private Long codigo;
    @Column(name = "nm_pais", length = 50, nullable = false)
    private String nome;
    @Column(name = "nr_cd_pais", length = 5, nullable = false)
    private String numeroCodigo;

    @OneToMany(mappedBy = "pais", cascade = CascadeType.ALL)
    private List<Estado> estados;
}
