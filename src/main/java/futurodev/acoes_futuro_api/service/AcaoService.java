package futurodev.acoes_futuro_api.service;

import futurodev.acoes_futuro_api.model.dto.AcaoRequest;
import futurodev.acoes_futuro_api.model.dto.AcaoResponse;
import futurodev.acoes_futuro_api.model.entity.AcaoSustentavel;
import futurodev.acoes_futuro_api.model.exceptions.ResourceNotFoundException;
import futurodev.acoes_futuro_api.repository.AcaoSustentavelRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AcaoService {

    private final AcaoSustentavelRepository repository;

    public AcaoService(AcaoSustentavelRepository repository) {
        this.repository = repository;
    }

    public List<AcaoResponse> listarTodas() {
        return repository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public AcaoResponse buscarPorId(Long id) {
        AcaoSustentavel acao = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ação não encontrada com o ID" + id));
        return toResponseDTO(acao);
    }

    public AcaoResponse criar(AcaoRequest dto) {
        AcaoSustentavel acao = toEntity(dto);
        AcaoSustentavel salvo = repository.save(acao);
        return toResponseDTO(salvo);
    }

    public AcaoResponse atualizar(Long id, AcaoRequest dto) {
        AcaoSustentavel existente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ação não encontrada com o ID" + id));

        existente.setTitulo(dto.getTitulo());
        existente.setDescricao(dto.getDescricao());
        existente.setCategoria(dto.getCategoria());
        existente.setDataRealizacao(dto.getDataRealizacao());
        existente.setResponsavel(dto.getResponsavel());

        AcaoSustentavel atualizado = repository.save(existente);
        return toResponseDTO(atualizado);
    }

    public void excluir(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Ação não encontrada com o ID" + id);
        }
        repository.deleteById(id);
    }

    // Conversão DTO → Entidade
    private AcaoSustentavel toEntity(AcaoRequest dto) {
        return new AcaoSustentavel(
                dto.getTitulo(),
                dto.getDescricao(),
                dto.getCategoria(),
                dto.getDataRealizacao(),
                dto.getResponsavel()
        );
    }

    // Conversão Entidade → DTO
    private AcaoResponse toResponseDTO(AcaoSustentavel acao) {
        return new AcaoResponse(
                acao.getId(),
                acao.getTitulo(),
                acao.getDescricao(),
                acao.getCategoria(),
                acao.getDataRealizacao(),
                acao.getResponsavel()
        );
    }
}
