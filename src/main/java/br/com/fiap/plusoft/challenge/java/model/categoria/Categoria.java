package br.com.fiap.plusoft.challenge.java.model.categoria;

import br.com.fiap.plusoft.challenge.java.model.figuraPublica.FiguraPublica;
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
@Table(name = "INOV_TB_CATEGORIA")
@SequenceGenerator(name = "inov_categoria_seq", sequenceName = "inov_tb_categoria_seq", allocationSize = 1)
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inov_categoria_seq")
    @Column(name = "cd_categoria")
    private Long codigo;
    @Column(name = "nm_categoria", length = 50, nullable = false)
    private String nome;

    @OneToMany(mappedBy = "categoria")
    private List<FiguraPublica> figuraPublica;

    public Categoria(CriarPesquisaDTO dto){
        this.nome = dto.getCategoria();
    }

}
