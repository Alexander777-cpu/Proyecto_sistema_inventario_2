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
public class ClienteService implements IClienteService {

    private final ClienteRepository repository;
    private final ClienteMapper mapper;

    public ClienteService(ClienteRepository repository, ClienteMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<ClienteResponse> listar() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ClienteResponse buscarPorId(Long id) {
        ClienteEntity entity = repository.findById(id)
                .orElseThrow(() -> new ClienteNotFoundException(id));
        return mapper.toResponse(entity);
    }

    @Override
    public ClienteResponse crear(ClienteRequest request) throws Exception {
        if (!repository.findByNombreContainingIgnoreCase(request.getNombre()).isEmpty()) {
            throw new IllegalArgumentException("Ya existe un cliente con el nombre: " + request.getNombre());
        }
        ClienteEntity entity = mapper.toEntity(request);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    public ClienteResponse actualizar(Long id, ClienteRequest request) {
        ClienteEntity entity = repository.findById(id)
                .orElseThrow(() -> new ClienteNotFoundException(id));
        mapper.updateEntity(entity, request);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    public void eliminar(Long id) {
        ClienteEntity entity = repository.findById(id)
                .orElseThrow(() -> new ClienteNotFoundException(id));
        repository.delete(entity);
    }

    @Override
    public List<ClienteResponse> buscarPorNombre(String nombre) {
        return repository.findByNombreContainingIgnoreCase(nombre).stream()
                .map(mapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<ClienteResponse> buscarPorApellido(String apellido) {
        return repository.findByApellidoContainingIgnoreCase(apellido).stream()
                .map(mapper::toResponse).collect(Collectors.toList());
    }
}