package pe.edu.upeu.msaccesorios.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.msaccesorios.entity.EstadoAccesorioEntity;
import pe.edu.upeu.msaccesorios.service.IEstadoAccesorioService;

import java.util.List;

@RestController
@RequestMapping("/api/accesorios/estados")
public class EstadoAccesorioController {

    private final IEstadoAccesorioService service;

    public EstadoAccesorioController(IEstadoAccesorioService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<EstadoAccesorioEntity>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstadoAccesorioEntity> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<EstadoAccesorioEntity> crear(@RequestBody EstadoAccesorioEntity entity) {
        EstadoAccesorioEntity creado = service.crear(entity);
        return new ResponseEntity<>(creado, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstadoAccesorioEntity> actualizar(@PathVariable Long id, @RequestBody EstadoAccesorioEntity entity) {
        EstadoAccesorioEntity actualizado = service.actualizar(id, entity);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}