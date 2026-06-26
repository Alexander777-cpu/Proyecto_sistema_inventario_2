package pe.edu.upeu.msmelamine.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pe.edu.upeu.msmelamine.dto.MelamineRequest;
import pe.edu.upeu.msmelamine.dto.MelamineResponse;
import pe.edu.upeu.msmelamine.service.MelamineService;

import java.util.List;

@RestController
@RequestMapping("/api/melamine")
public class MelamineController {

    private final MelamineService service;

    public MelamineController(MelamineService service) {
        this.service = service;
    }

    // 1. Listar todos
    @GetMapping
    public ResponseEntity<List<MelamineResponse>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    // 2. Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<MelamineResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    // 3. Buscar por Nombre
    @GetMapping("/buscar")
    public ResponseEntity<List<MelamineResponse>> buscarPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(service.buscarPorNombre(nombre));
    }

    /**
     * IMPORTANTE: Al enviar desde Postman:
     * 1. En Body -> form-data
     * 2. Key: 'melamine', Value: (tu JSON), Content-Type: application/json
     * 3. Key: 'imagen', Value: (archivo), Content-Type: image/png (o jpg)
     */
    // 4. Crear nueva melamina (Preparado para recibir imagen)
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MelamineResponse> crear(
            @RequestPart(value = "melamine") @Valid MelamineRequest request,
            @RequestPart(value = "imagen", required = false) MultipartFile imagen) throws Exception {

        MelamineResponse creado = service.crear(request, imagen);
        return new ResponseEntity<>(creado, HttpStatus.CREATED);
    }

    // 5. Actualizar melamina (Preparado para recibir imagen)
    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MelamineResponse> actualizar(
            @PathVariable Long id,
            @RequestPart(value = "melamine") @Valid MelamineRequest request,
            @RequestPart(value = "imagen", required = false) MultipartFile imagen) throws Exception {

        MelamineResponse actualizado = service.actualizar(id, request, imagen);
        return ResponseEntity.ok(actualizado);
    }

    // 6. Eliminar melamina
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}