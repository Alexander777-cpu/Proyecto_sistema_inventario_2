package pe.edu.upeu.msproveedores.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pe.edu.upeu.msproveedores.dto.ProveedorRequest;
import pe.edu.upeu.msproveedores.dto.ProveedorResponse;
import pe.edu.upeu.msproveedores.entity.CategoriaProveedorEntity;
import pe.edu.upeu.msproveedores.entity.ProveedorEntity;
import pe.edu.upeu.msproveedores.mapper.ProveedorMapper;
import pe.edu.upeu.msproveedores.repository.ProveedorRepository;
import pe.edu.upeu.msproveedores.service.cloud.CloudinaryService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProveedorService implements IProveedorService {

    private final ProveedorRepository repository;
    private final ProveedorMapper mapper;
    private final ICategoriaProveedorService categoriaService;
    private final CloudinaryService cloudinaryService;

    @Autowired
    public ProveedorService(ProveedorRepository repository,
                            ProveedorMapper mapper,
                            ICategoriaProveedorService categoriaService,
                            CloudinaryService cloudinaryService) {
        this.repository = repository;
        this.mapper = mapper;
        this.categoriaService = categoriaService;
        this.cloudinaryService = cloudinaryService;
    }

    private String subirImagen(MultipartFile archivo) throws Exception {
        return (archivo != null && !archivo.isEmpty()) ? cloudinaryService.subirImagen(archivo) : null;
    }

    @Override
    public ProveedorResponse crear(ProveedorRequest request, MultipartFile imagen) throws Exception {
        if (repository.findByNombresAndApellidos(request.getNombres(), request.getApellidos()).isPresent()) {
            throw new IllegalArgumentException("Ya existe un proveedor con ese nombre y apellido: " + request.getNombres());
        }

        // Buscamos la categoría real para validar que exista
        CategoriaProveedorEntity categoria = categoriaService.buscarPorId(request.getCategoriaId());

        request.setImagenUrl(subirImagen(imagen));
        ProveedorEntity entity = mapper.toEntity(request);
        entity.setCategoria(categoria); // Asignamos la entidad completa

        return mapper.toResponse(repository.save(entity));
    }

    @Override
    public List<ProveedorResponse> listar() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ProveedorResponse buscarPorId(Long id) {
        ProveedorEntity entity = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Proveedor no encontrado con ID: " + id));
        return mapper.toResponse(entity);
    }

    @Override
    public List<ProveedorResponse> buscarPorCategoria(Long categoriaId) {
        return repository.findByCategoriaId(categoriaId).stream()
                .map(mapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<ProveedorResponse> buscarPorNombre(String nombres) {
        return repository.findByNombresContainingIgnoreCase(nombres).stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ProveedorResponse actualizar(Long id, ProveedorRequest request, MultipartFile imagen) throws Exception {
        ProveedorEntity entity = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Proveedor no encontrado con ID: " + id));

        CategoriaProveedorEntity categoria = categoriaService.buscarPorId(request.getCategoriaId());

        if (imagen != null && !imagen.isEmpty()) {
            request.setImagenUrl(subirImagen(imagen));
        } else {
            request.setImagenUrl(entity.getImagenUrl());
        }

        mapper.updateEntity(entity, request);
        entity.setCategoria(categoria);

        return mapper.toResponse(repository.save(entity));
    }

    @Override
    public void eliminar(Long id) {
        // 👇 MÉTODO CORREGIDO (Evita que la aplicación explote)
        ProveedorEntity entity = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Proveedor no encontrado con ID: " + id));
        repository.delete(entity);
    }
}