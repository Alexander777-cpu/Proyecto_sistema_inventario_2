package pe.edu.upeu.msclientes.service;

import org.springframework.stereotype.Service;
import pe.edu.upeu.msclientes.dto.ClienteRequest;
import pe.edu.upeu.msclientes.dto.ClienteResponse;
import pe.edu.upeu.msclientes.entity.ClienteEntity;
import pe.edu.upeu.msclientes.errors.ClienteNotFoundException;
import pe.edu.upeu.msclientes.mappers.ClienteMapper;
import pe.edu.upeu.msclientes.repository.ClienteRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    private final ClienteRepository repository;
    private final ClienteMapper mapper;

    public ClienteService(ClienteRepository repository, ClienteMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<ClienteResponse> listar() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    public ClienteResponse buscarPorId(Long id) {
        ClienteEntity entity = repository.findById(id)
                .orElseThrow(() -> new ClienteNotFoundException(id));
        return mapper.toResponse(entity);
    }

    public ClienteResponse buscarPorDni(String dni) {
        ClienteEntity entity = repository.findByDni(dni)
                .orElseThrow(() -> new IllegalArgumentException("No existe el cliente con DNI: " + dni));
        return mapper.toResponse(entity);
    }

    public ClienteResponse crear(ClienteRequest request) {
        repository.findByDni(request.getDni()).ifPresent(c -> {
            throw new IllegalArgumentException("Ya existe un cliente con el DNI: " + request.getDni());
        });
        ClienteEntity entity = mapper.toEntity(request);
        entity.setEstado("ACTIVO");
        return mapper.toResponse(repository.save(entity));
    }

    public ClienteResponse actualizar(Long id, ClienteRequest request) {
        ClienteEntity entity = repository.findById(id)
                .orElseThrow(() -> new ClienteNotFoundException(id));
        mapper.updateEntity(entity, request);
        return mapper.toResponse(repository.save(entity));
    }

    public void eliminar(Long id) {
        ClienteEntity entity = repository.findById(id)
                .orElseThrow(() -> new ClienteNotFoundException(id));
        entity.setEstado("INACTIVO");
        repository.save(entity);
    }

    public List<ClienteResponse> buscarPorNombre(String nombre) {
        return repository.findByNombreContainingIgnoreCase(nombre)
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<ClienteResponse> buscarPorApellido(String apellido) {
        return repository.findByApellidoContainingIgnoreCase(apellido)
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }
}