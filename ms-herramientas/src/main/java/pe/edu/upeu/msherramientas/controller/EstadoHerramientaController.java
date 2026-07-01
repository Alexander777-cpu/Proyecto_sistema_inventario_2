package pe.edu.upeu.msherramientas.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.msherramientas.entity.EstadoHerramientaEntity;
import pe.edu.upeu.msherramientas.service.IEstadoHerramientaService;

import java.util.List;

@RestController
@RequestMapping("/api/estados")
public class EstadoHerramientaController {

    private final IEstadoHerramientaService estadoService;

    public EstadoHerramientaController(IEstadoHerramientaService estadoService) {
        this.estadoService = estadoService;
    }

    @GetMapping
    public ResponseEntity<List<EstadoHerramientaEntity>> listAll() {
        return ResponseEntity.ok(estadoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstadoHerramientaEntity> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(estadoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<EstadoHerramientaEntity> crear(@RequestBody EstadoHerramientaEntity entity) {
        EstadoHerramientaEntity creado = estadoService.crear(entity);
        return new ResponseEntity<>(creado, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstadoHerramientaEntity> actualizar(@PathVariable Long id, @RequestBody EstadoHerramientaEntity entity) {
        EstadoHerramientaEntity actualizado = estadoService.actualizar(id, entity);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        estadoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}