package pe.edu.upeu.msmelamine.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pe.edu.upeu.msmelamine.dto.MelamineRequest;
import pe.edu.upeu.msmelamine.dto.MelamineResponse;
import pe.edu.upeu.msmelamine.service.IMelamineService;

import java.util.List;

@RestController
@RequestMapping("/api/melamine")
public class MelamineController {

    private final IMelamineService service;

    public MelamineController(IMelamineService service) {
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

    @GetMapping("/buscar")
    public ResponseEntity<List<MelamineResponse>> buscarPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(service.buscarPorNombre(nombre));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MelamineResponse> crear(
            @RequestPart(value = "melamine") @Valid MelamineRequest request,
            @RequestPart(value = "imagen", required = false) MultipartFile imagen) throws Exception {
        return new ResponseEntity<>(service.crear(request, imagen), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MelamineResponse> actualizar(
            @PathVariable Long id,
            @RequestPart(value = "melamine") @Valid MelamineRequest request,
            @RequestPart(value = "imagen", required = false) MultipartFile imagen) throws Exception {
        return ResponseEntity.ok(service.actualizar(id, request, imagen));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}