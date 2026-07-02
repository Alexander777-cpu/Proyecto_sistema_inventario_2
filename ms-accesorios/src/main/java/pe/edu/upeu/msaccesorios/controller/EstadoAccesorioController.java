package pe.edu.upeu.msaccesorios.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.msaccesorios.entity.EstadoAccesorioEntity;
import pe.edu.upeu.msaccesorios.service.IEstadoAccesorioService;

import java.util.List;

@RestController
@RequestMapping("/api/estados-accesorios")
public class EstadoAccesorioController {

    private final IEstadoAccesorioService service;

    public EstadoAccesorioController(IEstadoAccesorioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<EstadoAccesorioEntity> crear(@RequestBody EstadoAccesorioEntity entity) {
        EstadoAccesorioEntity creado = service.crear(entity);
        return new ResponseEntity<>(creado, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EstadoAccesorioEntity>> listar() {
        return ResponseEntity.ok(service.listar());
    }
}