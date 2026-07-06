package pe.edu.upeu.msherramientas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pe.edu.upeu.msherramientas.dto.HerramientaRequest;
import pe.edu.upeu.msherramientas.dto.HerramientaResponse;
import pe.edu.upeu.msherramientas.entity.EstadoHerramientaEntity;
import pe.edu.upeu.msherramientas.entity.HerramientaEntity;
import pe.edu.upeu.msherramientas.entity.MarcaHerramientaEntity;
import pe.edu.upeu.msherramientas.entity.TipoHerramientaEntity;
import pe.edu.upeu.msherramientas.errors.HerramientaNotFoundException;
import pe.edu.upeu.msherramientas.mapper.HerramientaMapper;
import pe.edu.upeu.msherramientas.repository.HerramientaRepository;
import pe.edu.upeu.msherramientas.service.cloud.CloudinaryService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HerramientaService implements IHerramientaService {

    private final HerramientaRepository repository;
    private final HerramientaMapper mapper;
    private final CloudinaryService cloudinaryService;

    // Inyectamos las interfaces de los otros catálogos locales
    private final ITipoHerramientaService tipoService;
    private final IMarcaHerramientaService marcaService;
    private final IEstadoHerramientaService estadoService;

    @Autowired
    public HerramientaService(HerramientaRepository repository,
                              HerramientaMapper mapper,
                              CloudinaryService cloudinaryService,
                              ITipoHerramientaService tipoService,
                              IMarcaHerramientaService marcaService,
                              IEstadoHerramientaService estadoService) {
        this.repository = repository;
        this.mapper = mapper;
        this.cloudinaryService = cloudinaryService;
        this.tipoService = tipoService;
        this.marcaService = marcaService;
        this.estadoService = estadoService;
    }

    // Método intacto para subir la imagen a Cloudinary
    private String subirImagenAAlmacenamientoExterno(MultipartFile archivo) throws Exception {
        if (archivo != null && !archivo.isEmpty()) {
            return cloudinaryService.subirImagen(archivo);
        }
        return null;
    }

    @Override
    public HerramientaResponse crear(HerramientaRequest request, MultipartFile imagen) throws Exception {
        // 1. Validar que no exista otra herramienta con el mismo nombre
        if (repository.existsByNombreIgnoreCase(request.getNombre())) {
            throw new IllegalArgumentException("Ya existe una herramienta con el nombre: " + request.getNombre());
        }

        // 2. Obtener las entidades relacionadas (Esto valida que los IDs foráneos existan en la BD)
        TipoHerramientaEntity tipo = tipoService.buscarPorId(request.getTipoId());
        MarcaHerramientaEntity marca = marcaService.buscarPorId(request.getMarcaId());
        EstadoHerramientaEntity estado = estadoService.buscarPorId(request.getEstadoId());

        // 3. Subir imagen a Cloudinary si existe
        String urlImagen = subirImagenAAlmacenamientoExterno(imagen);
        request.setImagenUrl(urlImagen);

        // 4. Mapear los datos de texto y asignar las relaciones completas
        HerramientaEntity entity = mapper.toEntity(request);
        entity.setTipo(tipo);
        entity.setMarca(marca);
        entity.setEstado(estado);

        // 5. Guardar en BD y retornar (El mapper leerá los nombres de las entidades que le acabamos de setear)
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    public List<HerramientaResponse> listar() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public HerramientaResponse buscarPorId(Long id) {
        HerramientaEntity entity = repository.findById(id)
                .orElseThrow(() -> new HerramientaNotFoundException(id));
        return mapper.toResponse(entity);
    }

    @Override
    public List<HerramientaResponse> buscarPorNombre(String nombre) {
        return repository.findByNombreContainingIgnoreCase(nombre).stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<HerramientaResponse> buscarPorMarca(Long marcaId) {
        return repository.findByMarcaId(marcaId).stream()
                .map(mapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<HerramientaResponse> buscarPorEstado(Long estadoId) {
        return repository.findByEstadoId(estadoId).stream()
                .map(mapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public HerramientaResponse actualizar(Long id, HerramientaRequest request, MultipartFile imagen) throws Exception {
        // 1. Buscar la herramienta existente
        HerramientaEntity entity = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró la herramienta con ID: " + id));

        // 2. Obtener las nuevas entidades relacionadas (valida que los nuevos IDs existan)
        TipoHerramientaEntity tipo = tipoService.buscarPorId(request.getTipoId());
        MarcaHerramientaEntity marca = marcaService.buscarPorId(request.getMarcaId());
        EstadoHerramientaEntity estado = estadoService.buscarPorId(request.getEstadoId());

        // 3. Actualización de URL en Cloudinary si se envía una nueva imagen
        if (imagen != null && !imagen.isEmpty()) {
            request.setImagenUrl(subirImagenAAlmacenamientoExterno(imagen));
        } else {
            request.setImagenUrl(entity.getImagenUrl()); // Mantiene la foto anterior
        }

        // 4. Actualizar datos base de la entidad
        mapper.updateEntity(entity, request);

        // 5. Sobrescribir con las nuevas relaciones
        entity.setTipo(tipo);
        entity.setMarca(marca);
        entity.setEstado(estado);

        // 6. Guardar y retornar
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    public void eliminar(Long id) {
        HerramientaEntity entity = repository.findById(id)
                .orElseThrow(() -> new HerramientaNotFoundException(id));
        repository.delete(entity);
    }
}