package pe.edu.upeu.msmelamine.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.msmelamine.entity.EstadoMelamineEntity;
import pe.edu.upeu.msmelamine.service.IEstadoMelamineService;

import java.util.List;

@RestController
@RequestMapping("/api/melamine/estados")
public class EstadoMelamineController {

    private final IEstadoMelamineService service;

    public EstadoMelamineController(IEstadoMelamineService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<EstadoMelamineEntity>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstadoMelamineEntity> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<EstadoMelamineEntity> crear(@Valid @RequestBody EstadoMelamineEntity entity) {
        return new ResponseEntity<>(service.crear(entity), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstadoMelamineEntity> actualizar(@PathVariable Long id, @Valid @RequestBody EstadoMelamineEntity entity) {
        return ResponseEntity.ok(service.actualizar(id, entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}