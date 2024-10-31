package br.com.fiap.plusoft.challenge.java.model.tipoServico;

import br.com.fiap.plusoft.challenge.java.model.pesquisa.Pesquisa;
import br.com.fiap.plusoft.challenge.java.model.pesquisa.dto.CriarPesquisaDTO;
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
@Table(name = "INOV_TB_TIPO_SERVICO")
@SequenceGenerator(name = "inov_tipo_servico_seq", sequenceName = "inov_tb_tipo_servico_seq", allocationSize = 1, initialValue = 1)
public class TipoServico {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inov_tipo_servico_seq")
    @Column(name = "cd_tipo_servico")
    private Long codigo;
    @Column(name = "ds_tipo_servico", length = 100, nullable = false)
    private String descricao;

    @OneToMany(mappedBy = "tipoServico", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Pesquisa> pesquisas;

    public TipoServico(CriarPesquisaDTO dto){
        this.descricao = dto.getTipoServico();
    }

}
