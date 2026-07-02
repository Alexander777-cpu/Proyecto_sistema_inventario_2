package pe.edu.upeu.msproveedores.controller;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;
import pe.edu.upeu.msproveedores.dto.ProveedorRequest;
import pe.edu.upeu.msproveedores.dto.ProveedorResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.msproveedores.service.IProveedorService;
import pe.edu.upeu.msproveedores.service.ProveedorService;

import java.util.List;

@RestController
@RequestMapping("/api/proveedores")

public class ProveedorController {

    private final IProveedorService service;

    public ProveedorController(IProveedorService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ProveedorResponse>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProveedorResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    // Nota: Asegúrate de que el método buscarPorNombre esté definido en IProveedorService
    @GetMapping("/buscar")
    public ResponseEntity<List<ProveedorResponse>> buscarPorNombre(@RequestParam String nombres) {
        return ResponseEntity.ok(service.buscarPorNombre(nombres));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProveedorResponse> crear(
            @RequestPart(value = "proveedor") @Valid ProveedorRequest request,
            @RequestPart(value = "imagen", required = false) MultipartFile imagen) throws Exception {

        return new ResponseEntity<>(service.crear(request, imagen), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProveedorResponse> actualizar(
            @PathVariable Long id,
            @RequestPart(value = "proveedor") @Valid ProveedorRequest request,
            @RequestPart(value = "imagen", required = false) MultipartFile imagen) throws Exception {

        return ResponseEntity.ok(service.actualizar(id, request, imagen));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
