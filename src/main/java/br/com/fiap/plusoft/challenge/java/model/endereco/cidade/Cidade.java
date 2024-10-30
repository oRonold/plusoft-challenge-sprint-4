package br.com.fiap.plusoft.challenge.java.model.endereco.cidade;

import br.com.fiap.plusoft.challenge.java.model.cliente.dto.CadastrarClienteDTO;
import br.com.fiap.plusoft.challenge.java.model.endereco.bairro.Bairro;
import br.com.fiap.plusoft.challenge.java.model.endereco.estado.Estado;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "INOV_TB_CIDADE")
@SequenceGenerator(name = "inov_cidade_seq", sequenceName = "inov_tb_cidade_seq", allocationSize = 1, initialValue = 1)
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inov_cidade_seq")
    @Column(name = "cd_cidade")
    private Long codigo;
    @Column(name = "nm_cidade", length = 100, nullable = false)
    private String nome;
    @Column(name = "nr_ddd", length = 5, nullable = false)
    private String ddd;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cd_estado")
    private Estado estado;

    @OneToMany(mappedBy = "cidade")
    private List<Bairro> bairros;

    public Cidade(CadastrarClienteDTO dto){
        this.nome = dto.getNomeCidade();
        this.ddd = dto.getNumeroDDD();
        bairros = new ArrayList<>();
    }
}
