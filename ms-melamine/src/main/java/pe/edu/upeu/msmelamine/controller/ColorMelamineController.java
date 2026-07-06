package pe.edu.upeu.msmelamine.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.msmelamine.entity.ColorMelamineEntity;
import pe.edu.upeu.msmelamine.service.IColorMelamineService;

import java.util.List;

@RestController
@RequestMapping("/api/melamine/colores")
public class ColorMelamineController {

    private final IColorMelamineService service;

    public ColorMelamineController(IColorMelamineService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ColorMelamineEntity>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ColorMelamineEntity> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<ColorMelamineEntity> crear(@Valid @RequestBody ColorMelamineEntity entity) {
        return new ResponseEntity<>(service.crear(entity), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ColorMelamineEntity> actualizar(@PathVariable Long id, @Valid @RequestBody ColorMelamineEntity entity) {
        return ResponseEntity.ok(service.actualizar(id, entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}