package br.com.fiap.plusoft.challenge.java.model.figuraPublica;

import br.com.fiap.plusoft.challenge.java.model.categoria.Categoria;
import br.com.fiap.plusoft.challenge.java.model.pesquisa.Pesquisa;
import br.com.fiap.plusoft.challenge.java.model.score.Score;
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
@Table(name = "INOV_TB_FIG_PUBLICA")
@SequenceGenerator(name = "inov_fig_publica_seq", sequenceName = "inov_tb_fig_publica_seq", allocationSize = 1, initialValue = 1)
public class FiguraPublica {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inov_fig_publica_seq")
    @Column(name = "cd_fig_publica")
    private Long codigo;
    @Column(name = "nm_fig_publica", length = 100, nullable = false)
    private String nome;
    @Column(name = "nm_artistico", length = 100)
    private String nomeArtistico;
    @Column(name = "nm_usuario_rede_social", length = 100, nullable = false)
    private String nomeRedeSocial;

    @ManyToMany(mappedBy = "figuraPublica", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Pesquisa> pesquisa;

    @OneToOne(mappedBy = "figuraPublica",cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Score score;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "cd_categoria")
    private Categoria categoria;

}
