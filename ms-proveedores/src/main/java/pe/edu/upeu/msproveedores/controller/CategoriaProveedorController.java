package pe.edu.upeu.msproveedores.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.msproveedores.entity.CategoriaProveedorEntity;
import pe.edu.upeu.msproveedores.service.ICategoriaProveedorService;

import java.util.List;

// 👇 Ruta limpia como en herramientas
@RestController
@RequestMapping("/api/proveedores/categorias")
public class CategoriaProveedorController {

    private final ICategoriaProveedorService service;

    public CategoriaProveedorController(ICategoriaProveedorService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CategoriaProveedorEntity> crear(@RequestBody CategoriaProveedorEntity entity) {
        return new ResponseEntity<>(service.crear(entity), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CategoriaProveedorEntity>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaProveedorEntity> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaProveedorEntity> actualizar(@PathVariable Long id, @RequestBody CategoriaProveedorEntity entity) {
        return ResponseEntity.ok(service.actualizar(id, entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}