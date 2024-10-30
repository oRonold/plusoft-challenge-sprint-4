package br.com.fiap.plusoft.challenge.java.model.cliente;

import br.com.fiap.plusoft.challenge.java.model.cliente.dto.CadastrarClienteDTO;
import br.com.fiap.plusoft.challenge.java.model.endereco.EnderecoCliente;
import br.com.fiap.plusoft.challenge.java.model.ramo.Ramo;
import br.com.fiap.plusoft.challenge.java.model.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "INOV_TB_CLIENTE")
@SequenceGenerator(name = "inov_cliente_seq", sequenceName = "inov_tb_cliente_seq", allocationSize = 1, initialValue = 1)
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inov_cliente_seq")
    @Column(name = "cd_cliente")
    private Long codigo;

    @Column(name = "nm_cliente", length = 100, nullable = false)
    private String nome;

    @Column(name = "dt_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "nr_cpf", length = 11, nullable = false, unique = true)
    private String cpf;

    @Column(name = "nr_telefone", length = 15, nullable = false, unique = true)
    private String telefone;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cd_usuario")
    private Usuario usuario;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cd_ramo")
    private Ramo ramo;

    @OneToMany(mappedBy = "cliente",cascade = CascadeType.ALL)
    private List<EnderecoCliente> enderecoClientes;

    public Cliente(CadastrarClienteDTO dto){
        this.nome = dto.getNome();
        this.dataNascimento = dto.getDataNascimento();
        this.cpf = dto.getCpf();
        this.telefone = dto.getTelefone();
        enderecoClientes = new ArrayList<>();
    }

}
