package pe.edu.upeu.msherramientas.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.msherramientas.entity.MarcaHerramientaEntity;
import pe.edu.upeu.msherramientas.service.IMarcaHerramientaService;

import java.util.List;

@RestController
@RequestMapping("/api/herramientas/marcas")
public class MarcaHerramientaController {

    private final IMarcaHerramientaService marcaService;

    public MarcaHerramientaController(IMarcaHerramientaService marcaService) {
        this.marcaService = marcaService;
    }

    @GetMapping
    public ResponseEntity<List<MarcaHerramientaEntity>> listAll() {
        return ResponseEntity.ok(marcaService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MarcaHerramientaEntity> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(marcaService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<MarcaHerramientaEntity> crear(@RequestBody MarcaHerramientaEntity entity) {
        MarcaHerramientaEntity creado = marcaService.crear(entity);
        return new ResponseEntity<>(creado, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MarcaHerramientaEntity> actualizar(@PathVariable Long id, @RequestBody MarcaHerramientaEntity entity) {
        MarcaHerramientaEntity actualizado = marcaService.actualizar(id, entity);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        marcaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}