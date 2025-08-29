package futurodev.acoes_futuro_api.model.entity;

import futurodev.acoes_futuro_api.model.Enums.CategoriaAcao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "acoes_sustentaveis")
public class AcaoSustentavel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @Column(length = 1000)
    private String descricao;

    @Enumerated(EnumType.STRING)
    private CategoriaAcao categoria;

    private LocalDate dataRealizacao;

    private String responsavel;

    public AcaoSustentavel(String titulo, String descricao, CategoriaAcao categoria, LocalDate dataRealizacao, String responsavel) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.categoria = categoria;
        this.dataRealizacao = dataRealizacao;
        this.responsavel = responsavel;
    }


}
