package futurodev.acoes_futuro_api.model.dto;

import futurodev.acoes_futuro_api.model.Enums.CategoriaAcao;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AcaoRequest {

    @NotBlank(message = "O título é obrigatório")
    @Size(max = 100, message = "O título deve ter no máximo 100 caracteres")
    private String titulo;

    @NotBlank(message = "A descrição é obrigatória")
    @Size(max = 1000, message = "A descrição deve ter no máximo 1000 caracteres")
    private String descricao;

    @NotNull(message = "A categoria é obrigatória")
    private CategoriaAcao categoria;

    @NotNull(message = "A data de realização é obrigatória")
    @FutureOrPresent(message = "A data de realização não pode ser no passado")
    private LocalDate dataRealizacao;

    @NotBlank(message = "O responsável é obrigatório")
    @Size(max = 100, message = "O responsável deve ter no máximo 100 caracteres")
    private String responsavel;
}
