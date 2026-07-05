package pe.edu.upeu.msherramientas.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pe.edu.upeu.msherramientas.dto.HerramientaRequest;
import pe.edu.upeu.msherramientas.dto.HerramientaResponse;
import pe.edu.upeu.msherramientas.service.HerramientaService;

import java.util.List;

@RestController
@RequestMapping("/api/herramientas")
public class HerramientaController {

    private final HerramientaService herramientaService;

    public HerramientaController(HerramientaService herramientaService) {
        this.herramientaService = herramientaService;
    }

    @GetMapping
    public ResponseEntity<List<HerramientaResponse>> listAll(){
        return ResponseEntity.ok(herramientaService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HerramientaResponse> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(herramientaService.buscarPorId(id));
    }

    @GetMapping("/marca/{marcaId}")
    public ResponseEntity<List<HerramientaResponse>> buscarPorMarca(@PathVariable Long marcaId) {
        return ResponseEntity.ok(herramientaService.buscarPorMarca(marcaId));
    }

    @GetMapping("/estado/{estadoId}")
    public ResponseEntity<List<HerramientaResponse>> buscarPorEstado(@PathVariable Long estadoId) {
        return ResponseEntity.ok(herramientaService.buscarPorEstado(estadoId));
    }

    /**
     * IMPORTANTE: Al enviar desde Postman:
     * 1. En Body -> form-data
     * 2. Key: 'herramienta', Value: (tu JSON), Content-Type: application/json
     * 3. Key: 'imagen', Value: (archivo), Content-Type: image/png (o jpg)
     */
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<HerramientaResponse> crear(
            @RequestPart(value = "herramienta") @Valid HerramientaRequest herramientaRequest,
            @RequestPart(value = "imagen", required = false) MultipartFile imagen) throws Exception {

        HerramientaResponse creado = herramientaService.crear(herramientaRequest, imagen);
        return new ResponseEntity<>(creado, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<HerramientaResponse> actualizar(
            @PathVariable Long id,
            @RequestPart(value = "herramienta") @Valid HerramientaRequest herramientaRequest,
            @RequestPart(value = "imagen", required = false) MultipartFile imagen) throws Exception {

        HerramientaResponse actualizado = herramientaService.actualizar(id, herramientaRequest, imagen);
        return ResponseEntity.ok(actualizado);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<HerramientaResponse>> buscarPorNombre(@RequestParam String nombre){
        return ResponseEntity.ok(herramientaService.buscarPorNombre(nombre));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        herramientaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}