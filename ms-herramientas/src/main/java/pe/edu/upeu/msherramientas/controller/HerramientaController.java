package pe.edu.upeu.msherramientas.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.msherramientas.dto.HerramientaDTO;
import pe.edu.upeu.msherramientas.service.HerramientaService;

import java.util.List;

@RestController
@RequestMapping("/api/herramientas")
@CrossOrigin(origins = "http://localhost:8081/api/herramientas")
public class HerramientaController {
    private final HerramientaService herramientaService;

    public HerramientaController(HerramientaService herrmientaService) { this.herramientaService = herrmientaService; }

    @GetMapping
    public ResponseEntity<List<HerramientaDTO>> listAll(){
        List<HerramientaDTO> herramientas = herramientaService.listar();
        return ResponseEntity.ok(herramientas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HerramientaDTO> buscarPorId(@PathVariable Long id){
        HerramientaDTO herramienta = herramientaService.buscarPorId(id);
        return ResponseEntity.ok(herramienta);
    }

    @PostMapping
    public ResponseEntity<HerramientaDTO> crear(@Valid @RequestBody HerramientaDTO herramientaDTO){
        HerramientaDTO creado = herramientaService.crear(herramientaDTO);
        return new ResponseEntity<>(creado, HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<HerramientaDTO> actualizar(@PathVariable Long id, @Valid @RequestBody HerramientaDTO herramientaDTO){
        HerramientaDTO actualizado = herramientaService.actualizar(id, herramientaDTO);
        return ResponseEntity.ok(actualizado);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<HerramientaDTO>> buscarPorNombre(@RequestParam String nombre){
       return ResponseEntity.ok(herramientaService.buscarPorNombre(nombre));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        herramientaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
