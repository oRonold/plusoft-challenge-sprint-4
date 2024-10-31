package br.com.fiap.plusoft.challenge.java.model.pesquisa;

import br.com.fiap.plusoft.challenge.java.model.figuraPublica.FiguraPublica;
import br.com.fiap.plusoft.challenge.java.model.pesquisa.dto.CriarPesquisaDTO;
import br.com.fiap.plusoft.challenge.java.model.tipoServico.TipoServico;
import br.com.fiap.plusoft.challenge.java.model.usuario.Usuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "INOV_TB_PESQUISA")
@SequenceGenerator(name = "inov_pesquisa_seq", sequenceName = "inov_tb_pesquisa_seq", allocationSize = 1, initialValue = 1)
public class Pesquisa {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inov_pesquisa_seq")
    @Column(name = "cd_pesquisa")
    private Long codigo;

    @Column(name = "ds_pesquisa", length = 100, nullable = false)
    private String descricao;

    @Column(name = "dt_pesquisa", nullable = false)
    @CreatedDate
    private LocalDateTime dataPesquisa;

    @Column(name = "st_pesquisa", nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusPesquisa statusPesquisa;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "INOV_TB_PESQUISA_FIG_PUBLICA",
            joinColumns = @JoinColumn(name = "cd_pesquisa", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "cd_fig_publica", nullable = false))
    private List<FiguraPublica> figuraPublica;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "cd_tipo_servico")
    private TipoServico tipoServico;

    @ManyToOne
    @JoinColumn(name = "cd_usuario")
    private Usuario usuario;

    public Pesquisa(CriarPesquisaDTO dto){
        this.descricao = dto.getDescricao();
        this.dataPesquisa = LocalDateTime.now();
        this.statusPesquisa = StatusPesquisa.EM_ANDAMENTO;
        this.figuraPublica = new ArrayList<>();
        this.tipoServico = new TipoServico(dto);
    }

}
