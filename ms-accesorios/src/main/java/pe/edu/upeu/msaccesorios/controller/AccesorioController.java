package pe.edu.upeu.msaccesorios.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pe.edu.upeu.msaccesorios.dto.AccesorioRequest;
import pe.edu.upeu.msaccesorios.dto.AccesorioResponse;
import pe.edu.upeu.msaccesorios.entity.EstadoAccesorioEntity;
import pe.edu.upeu.msaccesorios.entity.MarcaAccesorioEntity;
import pe.edu.upeu.msaccesorios.service.IAccesorioService;
import pe.edu.upeu.msaccesorios.service.IEstadoAccesorioService;
import pe.edu.upeu.msaccesorios.service.IMarcaAccesorioService;

import java.util.List;

@RestController
@RequestMapping("/api/accesorios")
public class AccesorioController {

    private final IAccesorioService service;

    // Agregamos los servicios de Marca y Estado
    private final IEstadoAccesorioService estadoService;
    private final IMarcaAccesorioService marcaService;

    // Actualizamos el constructor para inyectarlos
    public AccesorioController(IAccesorioService service,
                               IEstadoAccesorioService estadoService,
                               IMarcaAccesorioService marcaService) {
        this.service = service;
        this.estadoService = estadoService;
        this.marcaService = marcaService;
    }

    // ==========================================
    // NUEVOS ENDPOINTS INFALIBLES PARA MARCA Y ESTADO
    // ==========================================

    @PostMapping("/estados")
    public ResponseEntity<EstadoAccesorioEntity> crearEstado(@RequestBody EstadoAccesorioEntity entity) {
        EstadoAccesorioEntity creado = estadoService.crear(entity);
        return new ResponseEntity<>(creado, HttpStatus.CREATED);
    }

    @PostMapping("/marcas")
    public ResponseEntity<MarcaAccesorioEntity> crearMarca(@RequestBody MarcaAccesorioEntity entity) {
        MarcaAccesorioEntity creado = marcaService.crear(entity);
        return new ResponseEntity<>(creado, HttpStatus.CREATED);
    }

    // ==========================================
    // TUS ENDPOINTS ORIGINALES DE ACCESORIO
    // ==========================================

    @GetMapping
    public ResponseEntity<List<AccesorioResponse>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccesorioResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}