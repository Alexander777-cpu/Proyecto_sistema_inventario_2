package pe.edu.upeu.msaccesorios.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.msaccesorios.entity.MarcaAccesorioEntity;
import pe.edu.upeu.msaccesorios.service.IMarcaAccesorioService;

import java.util.List;

@RestController
@RequestMapping("/api/accesorios/marcas")
public class MarcaAccesorioController {

    private final IMarcaAccesorioService service;

    public MarcaAccesorioController(IMarcaAccesorioService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<MarcaAccesorioEntity>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MarcaAccesorioEntity> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<MarcaAccesorioEntity> crear(@RequestBody MarcaAccesorioEntity entity) {
        MarcaAccesorioEntity creado = service.crear(entity);
        return new ResponseEntity<>(creado, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MarcaAccesorioEntity> actualizar(@PathVariable Long id, @RequestBody MarcaAccesorioEntity entity) {
        MarcaAccesorioEntity actualizado = service.actualizar(id, entity);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}