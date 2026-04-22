package pe.edu.upeu.msaccesorios.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.msaccesorios.dto.AccesorioRequest;
import pe.edu.upeu.msaccesorios.dto.AccesorioResponse;
import pe.edu.upeu.msaccesorios.service.AccesorioService;

import java.util.List;

@RestController
@RequestMapping("/api/accesorios")
@CrossOrigin(origins = "http://localhost:8080")
public class AccesorioController {

    private final AccesorioService service;

    public AccesorioController(AccesorioService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<AccesorioResponse>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccesorioResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<AccesorioResponse>> buscarPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(service.buscarPorNombre(nombre));
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<AccesorioResponse>> buscarPorCategoria(@PathVariable String categoria) {
        return ResponseEntity.ok(service.buscarPorCategoria(categoria));
    }

    @GetMapping("/con-stock")
    public ResponseEntity<List<AccesorioResponse>> listarConStock() {
        return ResponseEntity.ok(service.listarConStock());
    }

    @PostMapping
    public ResponseEntity<AccesorioResponse> crear(@Valid @RequestBody AccesorioRequest request) {
        return new ResponseEntity<>(service.crear(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccesorioResponse> actualizar(@PathVariable Long id, @Valid @RequestBody AccesorioRequest request) {
        return ResponseEntity.ok(service.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}