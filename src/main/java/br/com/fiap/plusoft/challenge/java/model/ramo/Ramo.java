package br.com.fiap.plusoft.challenge.java.model.ramo;

import br.com.fiap.plusoft.challenge.java.model.cliente.Cliente;
import br.com.fiap.plusoft.challenge.java.model.cliente.dto.CadastrarClienteDTO;
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
@Table(name = "INOV_TB_RAMO")
@SequenceGenerator(name = "inov_ramo_seq", sequenceName = "inov_tb_ramo_seq", allocationSize = 1, initialValue = 1)
public class Ramo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inov_ramo_seq")
    @Column(name = "cd_ramo")
    private Long codigo;
    @Column(name = "nm_ramo", length = 100, nullable = false)
    private String nome;
    @Column(name = "ds_ramo", length = 100, nullable = false)
    private String descricao;

    @OneToMany(mappedBy = "ramo")
    private List<Cliente> cliente;

    public Ramo(CadastrarClienteDTO dto){
        this.nome = dto.getNomeRamo();
        this.descricao = dto.getDescRamo();
        cliente = new ArrayList<>();
    }

}
