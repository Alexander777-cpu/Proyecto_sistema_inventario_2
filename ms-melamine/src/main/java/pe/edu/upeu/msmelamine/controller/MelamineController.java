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

    @GetMapping
    public ResponseEntity<List<MelamineResponse>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MelamineResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    /**
     * IMPORTANTE: Al enviar desde Postman:
     * 1. En Body -> form-data
     * 2. Key: 'melamine', Value: (tu JSON), Content-Type: application/json
     * 3. Key: 'imagen', Value: (archivo), Content-Type: image/png (o jpg)
     */
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MelamineResponse> crear(
            @RequestPart(value = "melamine") @Valid MelamineRequest request,
            @RequestPart(value = "imagen", required = false) MultipartFile imagen) throws Exception {

        MelamineResponse creado = service.crear(request, imagen);
        return new ResponseEntity<>(creado, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MelamineResponse> actualizar(
            @PathVariable Long id,
            @RequestPart(value = "melamine") @Valid MelamineRequest request,
            @RequestPart(value = "imagen", required = false) MultipartFile imagen) throws Exception {

        MelamineResponse actualizado = service.actualizar(id, request, imagen);
        return ResponseEntity.ok(actualizado);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<MelamineResponse>> buscarPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(service.buscarPorNombre(nombre));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
