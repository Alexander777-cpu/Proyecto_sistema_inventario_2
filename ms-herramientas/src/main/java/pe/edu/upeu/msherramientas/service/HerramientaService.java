package pe.edu.upeu.msherramientas.service;

import org.springframework.stereotype.Service;
import pe.edu.upeu.msherramientas.dto.HerramientaDTO;
import pe.edu.upeu.msherramientas.entity.HerramientaEntity;
import pe.edu.upeu.msherramientas.repository.HerramientaRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HerramientaService {

    private final HerramientaRepository herramientaRepository;

    public HerramientaService(HerramientaRepository herramientaRepository) {
        this.herramientaRepository = herramientaRepository;
    }

    public List<HerramientaDTO> listar(){
        return herramientaRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public HerramientaDTO buscarPorId(Long id){
        return toDTO(herramientaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró la herramienta con el ID: " + id)));
    }

    public HerramientaDTO crear(HerramientaDTO dto){
        if (dto == null) {
            throw new IllegalArgumentException("Los datos de la herramienta no pueden ser nulos");
        }


        if (herramientaRepository.existsByNombreIgnoreCase(dto.getNombre())) {
            throw new IllegalArgumentException("No se puede crear. Ya existe una herramienta con el nombre: " + dto.getNombre());
        }

        return toDTO(herramientaRepository.save(toEntity(dto)));
    }

    public List<HerramientaDTO> buscarPorNombre(String nombre){
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de búsqueda no puede estar vacío");
        }
        return herramientaRepository.findByNombreContainingIgnoreCase(nombre)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    public HerramientaDTO actualizar(Long id, HerramientaDTO dto){
        if (dto == null) {
            throw new IllegalArgumentException("Los datos a actualizar no pueden ser nulos");
        }

        HerramientaEntity e = herramientaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No se puede actualizar. No se encontró la herramienta con ID: " + id));


        if (!e.getNombre().equalsIgnoreCase(dto.getNombre()) &&
                herramientaRepository.existsByNombreIgnoreCase(dto.getNombre())) {
            throw new IllegalArgumentException("No se puede actualizar. Ya existe OTRA herramienta con el nombre: " + dto.getNombre());
        }

        e.setNombre(dto.getNombre());
        e.setTipo(dto.getTipo());
        e.setMarca(dto.getMarca());
        e.setEstado(dto.getEstado());
        e.setCompra(dto.getCompra());
        e.setVidaUtil(dto.getVidaUtil());

        return toDTO(herramientaRepository.save(e));
    }

    public void eliminar(Long id){
        if (!herramientaRepository.existsById(id)) {
            throw new IllegalArgumentException("No se puede eliminar. No existe la herramienta con ID: " + id);
        }
        herramientaRepository.deleteById(id);
    }

    private HerramientaDTO toDTO(HerramientaEntity e){
        HerramientaDTO d = new HerramientaDTO();
        d.setId(e.getId());
        d.setNombre(e.getNombre());
        d.setTipo(e.getTipo());
        d.setMarca(e.getMarca());
        d.setEstado(e.getEstado());
        d.setCompra(e.getCompra());
        d.setVidaUtil(e.getVidaUtil());
        return d;
    }

    private HerramientaEntity toEntity(HerramientaDTO d){
        HerramientaEntity e = new HerramientaEntity();
        e.setId(d.getId());
        e.setNombre(d.getNombre());
        e.setTipo(d.getTipo());
        e.setMarca(d.getMarca());
        e.setEstado(d.getEstado());
        e.setCompra(d.getCompra());
        e.setVidaUtil(d.getVidaUtil());
        return e;
    }
}