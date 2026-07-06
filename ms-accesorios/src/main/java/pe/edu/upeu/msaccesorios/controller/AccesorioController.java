package pe.edu.upeu.msaccesorios.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pe.edu.upeu.msaccesorios.dto.AccesorioRequest;
import pe.edu.upeu.msaccesorios.dto.AccesorioResponse;
import pe.edu.upeu.msaccesorios.service.AccesorioService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/accesorios")
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

    /**
     * IMPORTANTE: Al enviar desde Postman:
     * 1. En Body -> form-data
     * 2. Key: 'accesorio', Value: (tu JSON), Content-Type: application/json
     * 3. Key: 'imagen', Value: (archivo), Content-Type: image/png (o jpg)
     */
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AccesorioResponse> crear(
            @RequestPart(value = "accesorio") @Valid AccesorioRequest request,
            @RequestPart(value = "imagen", required = false) MultipartFile imagen) throws Exception {

        AccesorioResponse creado = service.crear(request, imagen);
        return new ResponseEntity<>(creado, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AccesorioResponse> actualizar(
            @PathVariable Long id,
            @RequestPart(value = "accesorio") @Valid AccesorioRequest request,
            @RequestPart(value = "imagen", required = false) MultipartFile imagen) throws Exception {

        AccesorioResponse actualizado = service.actualizar(id, request, imagen);
        return ResponseEntity.ok(actualizado);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<AccesorioResponse>> buscarPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(service.buscarPorNombre(nombre));
    }

    // SOLO SE MODIFICÓ ESTO: Ahora recibe el categoriaId (Long) en la ruta
    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<List<AccesorioResponse>> buscarPorCategoriaId(@PathVariable Long categoriaId) {
        return ResponseEntity.ok(service.buscarPorCategoriaId(categoriaId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}