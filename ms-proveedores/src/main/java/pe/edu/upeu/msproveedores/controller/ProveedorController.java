package pe.edu.upeu.msproveedores.controller;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;
import pe.edu.upeu.msproveedores.dto.ProveedorRequest;
import pe.edu.upeu.msproveedores.dto.ProveedorResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.msproveedores.service.ProveedorService;

import java.util.List;

@RestController
@RequestMapping("/api/proveedores")


public class ProveedorController {

    private final ProveedorService service;

    public ProveedorController(ProveedorService service) {
        this.service = service;
    }

    // 1. Listar todos
    @GetMapping
    public ResponseEntity<List<ProveedorResponse>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    // 2. Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<ProveedorResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    // 3. Buscar por Nombre
    @GetMapping("/buscar")
    public ResponseEntity<List<ProveedorResponse>> buscarPorNombre(@RequestParam String nombres) {
        return ResponseEntity.ok(service.buscarPorNombre(nombres));
    }

    /**
     * IMPORTANTE: Al enviar desde Postman:
     * 1. En Body -> form-data
     * 2. Key: 'proveedor', Value: (tu JSON), Content-Type: application/json
     * 3. Key: 'imagen', Value: (archivo), Content-Type: image/png (o jpg)
     */
    // 4. Crear nuevo proveedor (Modificado para recibir imagen)
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProveedorResponse> crear(
            @RequestPart(value = "proveedor") @Valid ProveedorRequest request,
            @RequestPart(value = "imagen", required = false) MultipartFile imagen) throws Exception {

        ProveedorResponse creado = service.crear(request, imagen);
        return new ResponseEntity<>(creado, HttpStatus.CREATED);
    }

    // 5. Actualizar proveedor (Modificado para recibir imagen)
    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProveedorResponse> actualizar(
            @PathVariable Long id,
            @RequestPart(value = "proveedor") @Valid ProveedorRequest request,
            @RequestPart(value = "imagen", required = false) MultipartFile imagen) throws Exception { // Se agregó throws Exception por si acaso

        ProveedorResponse actualizado = service.actualizar(id, request, imagen);
        return ResponseEntity.ok(actualizado);
    }

    // 6. Eliminar proveedor
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
