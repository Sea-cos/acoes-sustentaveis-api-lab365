package futurodev.acoes_futuro_api.controller;


import futurodev.acoes_futuro_api.model.dto.AcaoRequest;
import futurodev.acoes_futuro_api.model.dto.AcaoResponse;
import futurodev.acoes_futuro_api.model.entity.AcaoSustentavel;
import futurodev.acoes_futuro_api.service.AcaoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/acoes")
public class AcaoController {

    private final AcaoService service;

    public AcaoController(AcaoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<AcaoResponse>> listarTodas() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AcaoResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/categoria")
    public ResponseEntity<List<AcaoSustentavel>> filtrarPorCategoria(@RequestParam String tipo){
        List<AcaoSustentavel> lista = service.buscarPorCategoria(tipo);
        return ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<AcaoResponse> criar(@Valid @RequestBody AcaoRequest dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AcaoResponse> atualizar(@PathVariable Long id,
                                                                @Valid @RequestBody AcaoRequest dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
