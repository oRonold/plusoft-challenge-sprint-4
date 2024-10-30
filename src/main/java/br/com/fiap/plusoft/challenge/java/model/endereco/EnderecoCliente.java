package br.com.fiap.plusoft.challenge.java.model.endereco;

import br.com.fiap.plusoft.challenge.java.model.cliente.Cliente;
import br.com.fiap.plusoft.challenge.java.model.cliente.dto.CadastrarClienteDTO;
import br.com.fiap.plusoft.challenge.java.model.endereco.logradouro.Logradouro;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "INOV_TB_ENDERECO_CLIENTE")
@SequenceGenerator(name = "inov_endereco_cliente_seq", sequenceName = "inov_tb_endereco_cliente_seq", allocationSize = 1, initialValue = 1)
public class EnderecoCliente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inov_endereco_cliente_seq")
    @Column(name = "cd_endereco")
    private Long codigo;
    @Column(name = "nr_logradouro", length = 11, nullable = false)
    private String numero;
    @Column(name = "ds_ponto_referencia", length = 50)
    private String pontoReferencia;

    @ManyToOne
    @JoinColumn(name = "cd_cliente")
    private Cliente cliente;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cd_logradouro")
    private Logradouro logradouro;

    public EnderecoCliente(CadastrarClienteDTO dto) {
        this.numero = dto.getNumeroLogradouro();
        this.pontoReferencia = dto.getPontoReferencia();
    }
}
