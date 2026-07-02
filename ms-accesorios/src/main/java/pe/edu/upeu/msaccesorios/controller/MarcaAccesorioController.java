package pe.edu.upeu.msaccesorios.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.msaccesorios.entity.MarcaAccesorioEntity;
import pe.edu.upeu.msaccesorios.service.IMarcaAccesorioService;

import java.util.List;

@RestController
@RequestMapping("/api/marcas-accesorios")
public class MarcaAccesorioController {

    private final IMarcaAccesorioService service;

    public MarcaAccesorioController(IMarcaAccesorioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<MarcaAccesorioEntity> crear(@RequestBody MarcaAccesorioEntity entity) {
        MarcaAccesorioEntity creado = service.crear(entity);
        return new ResponseEntity<>(creado, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MarcaAccesorioEntity>> listar() {
        return ResponseEntity.ok(service.listar());
    }
}