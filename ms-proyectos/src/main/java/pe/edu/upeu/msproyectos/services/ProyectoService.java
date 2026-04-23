package pe.edu.upeu.msproyectos.services;

import org.springframework.stereotype.Service;
import pe.edu.upeu.msproyectos.clients.AccesorioClient;
import pe.edu.upeu.msproyectos.clients.ClienteClient;
import pe.edu.upeu.msproyectos.clients.HerramientaClient;
import pe.edu.upeu.msproyectos.dtos.*;
import pe.edu.upeu.msproyectos.entity.ProyectoEntity;
import pe.edu.upeu.msproyectos.errors.ProyectoNotFoundException;
import pe.edu.upeu.msproyectos.mappers.ProyectoMapper;
import pe.edu.upeu.msproyectos.repository.ProyectoRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProyectoService {
    private final ProyectoRepository proyectoRepository;
    private final ProyectoMapper proyectoMapper;
    private final ClienteClient clienteClient;
    private final HerramientaClient herramientaClient;
    private final AccesorioClient accesorioClient;

    public ProyectoService(ProyectoRepository proyectoRepository,
                           ProyectoMapper proyectoMapper,
                           ClienteClient clienteClient,
                           HerramientaClient herramientaClient,
                           AccesorioClient accesorioClient) {
        this.proyectoRepository = proyectoRepository;
        this.proyectoMapper = proyectoMapper;
        this.clienteClient = clienteClient;
        this.herramientaClient = herramientaClient;
        this.accesorioClient = accesorioClient;
    }

    public List<ProyectoResponse> listar() {
        return proyectoRepository.findAll()
                .stream()
                .map(proyectoMapper::toResponse)
                .collect(Collectors.toList());
    }

    public ProyectoResponse buscarPorId(Long id) {
        ProyectoEntity entity = proyectoRepository.findById(id)
                .orElseThrow(() -> new ProyectoNotFoundException(id));
        return proyectoMapper.toResponse(entity);
    }

    public ProyectoResponse crear(ProyectoRequest request) {
        ProyectoEntity entity = proyectoMapper.toEntity(request);
        return proyectoMapper.toResponse(proyectoRepository.save(entity));
    }

    public ProyectoResponse actualizar(Long id, ProyectoRequest request) {
        ProyectoEntity entity = proyectoRepository.findById(id)
                .orElseThrow(() -> new ProyectoNotFoundException(id));
        proyectoMapper.updateEntity(entity, request);
        return proyectoMapper.toResponse(proyectoRepository.save(entity));
    }

    public void eliminar(Long id) {
        ProyectoEntity entity = proyectoRepository.findById(id)
                .orElseThrow(() -> new ProyectoNotFoundException(id));
        proyectoRepository.delete(entity);
    }

    public List<ProyectoResponse> buscarPorNombre(String nombre) {
        return proyectoRepository.findByNombreContainingIgnoreCase(nombre)
                .stream()
                .map(proyectoMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<ProyectoDetalleResponse> listarDetalle() {
        return proyectoRepository.findAll()
                .stream()
                .map(this::armarDetalle)
                .collect(Collectors.toList());
    }

    public ProyectoDetalleResponse buscarDetallePorId(Long id) {
        ProyectoEntity entity = proyectoRepository.findById(id)
                .orElseThrow(() -> new ProyectoNotFoundException(id));
        return armarDetalle(entity);
    }

    private ProyectoDetalleResponse armarDetalle(ProyectoEntity entity) {
        ClienteResponseDTO cliente = clienteClient.buscarPorId(entity.getClienteId());
        HerramientaResponseDTO herramienta = herramientaClient.buscarPorId(entity.getHerramientaId());
        AccesorioResponseDTO accesorio = accesorioClient.buscarPorId(entity.getAccesorioId());

        ProyectoDetalleResponse response = new ProyectoDetalleResponse();
        response.setId(entity.getId());
        response.setNombre(entity.getNombre());
        response.setDireccion(entity.getDireccion());

        response.setClienteId(entity.getClienteId());
        response.setNombreCliente(cliente.getNombre());

        response.setHerramientaId(entity.getHerramientaId());
        response.setNombreHerramienta(herramienta.getNombre());

        response.setAccesorioId(entity.getAccesorioId());
        response.setNombreAccesorio(accesorio.getNombre());

        return response;
    }
}
