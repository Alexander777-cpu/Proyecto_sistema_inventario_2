package pe.edu.upeu.msherramientas.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.msherramientas.entity.TipoHerramientaEntity;
import pe.edu.upeu.msherramientas.service.ITipoHerramientaService;

import java.util.List;

@RestController
@RequestMapping("/api/tipos")
public class TipoHerramientaController {

    private final ITipoHerramientaService tipoService;

    public TipoHerramientaController(ITipoHerramientaService tipoService) {
        this.tipoService = tipoService;
    }

    @GetMapping
    public ResponseEntity<List<TipoHerramientaEntity>> listAll() {
        return ResponseEntity.ok(tipoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoHerramientaEntity> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(tipoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<TipoHerramientaEntity> crear(@RequestBody TipoHerramientaEntity entity) {
        TipoHerramientaEntity creado = tipoService.crear(entity);
        return new ResponseEntity<>(creado, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoHerramientaEntity> actualizar(@PathVariable Long id, @RequestBody TipoHerramientaEntity entity) {
        TipoHerramientaEntity actualizado = tipoService.actualizar(id, entity);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        tipoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}