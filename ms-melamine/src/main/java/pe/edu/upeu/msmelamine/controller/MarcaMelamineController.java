package pe.edu.upeu.msmelamine.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.msmelamine.entity.MarcaMelamineEntity;
import pe.edu.upeu.msmelamine.service.IMarcaMelamineService;

import java.util.List;

@RestController
@RequestMapping("/api/melamine/marcas")
public class MarcaMelamineController {

    private final IMarcaMelamineService service;

    public MarcaMelamineController(IMarcaMelamineService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<MarcaMelamineEntity>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MarcaMelamineEntity> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<MarcaMelamineEntity> crear(@Valid @RequestBody MarcaMelamineEntity entity) {
        return new ResponseEntity<>(service.crear(entity), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MarcaMelamineEntity> actualizar(@PathVariable Long id, @Valid @RequestBody MarcaMelamineEntity entity) {
        return ResponseEntity.ok(service.actualizar(id, entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}