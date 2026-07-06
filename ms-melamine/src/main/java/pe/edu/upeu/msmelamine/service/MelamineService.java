package pe.edu.upeu.msmelamine.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pe.edu.upeu.msmelamine.dto.MelamineRequest;
import pe.edu.upeu.msmelamine.dto.MelamineResponse;
import pe.edu.upeu.msmelamine.entity.ColorMelamineEntity;
import pe.edu.upeu.msmelamine.entity.EstadoMelamineEntity;
import pe.edu.upeu.msmelamine.entity.MarcaMelamineEntity;
import pe.edu.upeu.msmelamine.entity.MelamineEntity;
import pe.edu.upeu.msmelamine.mapper.MelamineMapper;
import pe.edu.upeu.msmelamine.repository.MelamineRepository;
import pe.edu.upeu.msmelamine.service.cloud.CloudinaryService;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MelamineService implements IMelamineService {

    private final MelamineRepository repository;
    private final MelamineMapper mapper;
    private final CloudinaryService cloudinaryService;

    private final IColorMelamineService colorService;
    private final IMarcaMelamineService marcaService;
    private final IEstadoMelamineService estadoService;

    public MelamineService(MelamineRepository repository,
                           MelamineMapper mapper,
                           CloudinaryService cloudinaryService,
                           IColorMelamineService colorService,
                           IMarcaMelamineService marcaService,
                           IEstadoMelamineService estadoService) {
        this.repository = repository;
        this.mapper = mapper;
        this.cloudinaryService = cloudinaryService;
        this.colorService = colorService;
        this.marcaService = marcaService;
        this.estadoService = estadoService;
    }

    private String subirImagen(MultipartFile archivo) throws Exception {
        return (archivo != null && !archivo.isEmpty()) ? cloudinaryService.subirImagen(archivo) : null;
    }

    @Override
    public MelamineResponse crear(MelamineRequest request, MultipartFile imagen) throws Exception {
        if (repository.existsByNombreIgnoreCase(request.getNombre())) {
            throw new IllegalArgumentException("Ya existe una melamina con el nombre: " + request.getNombre());
        }

        ColorMelamineEntity color = colorService.buscarPorId(request.getColorId());
        MarcaMelamineEntity marca = marcaService.buscarPorId(request.getMarcaId());
        EstadoMelamineEntity estado = estadoService.buscarPorId(request.getEstadoId());

        request.setImagenUrl(subirImagen(imagen));
        MelamineEntity entity = mapper.toEntity(request);

        entity.setColor(color);
        entity.setMarca(marca);
        entity.setEstado(estado);

        return mapper.toResponse(repository.save(entity));
    }

    @Override
    public List<MelamineResponse> listar() {
        return repository.findAll().stream().map(mapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public MelamineResponse buscarPorId(Long id) {
        MelamineEntity entity = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró la melamina"));
        return mapper.toResponse(entity);
    }

    @Override
    public List<MelamineResponse> buscarPorNombre(String nombre) {
        return repository.findByNombreContainingIgnoreCase(nombre).stream()
                .map(mapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<MelamineResponse> buscarPorDimensiones(BigDecimal ancho, BigDecimal largo) {
        return repository.findByAnchoAndLargo(ancho, largo).stream()
                .map(mapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<MelamineResponse> buscarPorEstado(Long estadoId) {
        return repository.findByEstadoId(estadoId).stream()
                .map(mapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public MelamineResponse actualizar(Long id, MelamineRequest request, MultipartFile imagen) throws Exception {
        MelamineEntity entity = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró la melamina"));

        ColorMelamineEntity color = colorService.buscarPorId(request.getColorId());
        MarcaMelamineEntity marca = marcaService.buscarPorId(request.getMarcaId());
        EstadoMelamineEntity estado = estadoService.buscarPorId(request.getEstadoId());

        if (imagen != null && !imagen.isEmpty()) {
            request.setImagenUrl(subirImagen(imagen));
        } else {
            request.setImagenUrl(entity.getImagenUrl());
        }

        mapper.updateEntity(entity, request);
        entity.setColor(color);
        entity.setMarca(marca);
        entity.setEstado(estado);

        return mapper.toResponse(repository.save(entity));
    }

    @Override
    public void eliminar(Long id) {
        MelamineEntity entity = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró la melamina"));
        repository.delete(entity);
    }
}