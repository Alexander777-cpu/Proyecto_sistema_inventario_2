package pe.edu.upeu.msaccesorios.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;
import pe.edu.upeu.msaccesorios.dto.AccesorioRequest;
import pe.edu.upeu.msaccesorios.dto.AccesorioResponse;
import pe.edu.upeu.msaccesorios.errors.AccesorioNotFoundException;
import pe.edu.upeu.msaccesorios.manager.IAccesorioManager;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccesorioService {

    private final IAccesorioManager manager;

    public AccesorioService(IAccesorioManager manager) {
        this.manager = manager;
    }

    @CircuitBreaker(name = "accesorioService", fallbackMethod = "fallbackListar")
    public List<AccesorioResponse> listar() {
        return manager.listar();
    }

    @CircuitBreaker(name = "accesorioService", fallbackMethod = "fallbackBuscarPorId")
    public AccesorioResponse buscarPorId(Long id) {
        return manager.buscarPorId(id);
    }

    @CircuitBreaker(name = "accesorioService", fallbackMethod = "fallbackCrear")
    public AccesorioResponse crear(AccesorioRequest request) {
        return manager.crear(request);
    }

    @CircuitBreaker(name = "accesorioService", fallbackMethod = "fallbackActualizar")
    public AccesorioResponse actualizar(Long id, AccesorioRequest request) {
        return manager.actualizar(id, request);
    }

    @CircuitBreaker(name = "accesorioService", fallbackMethod = "fallbackEliminar")
    public void eliminar(Long id) {
        manager.eliminar(id);
    }

    @CircuitBreaker(name = "accesorioService", fallbackMethod = "fallbackBuscarPorNombre")
    public List<AccesorioResponse> buscarPorNombre(String nombre) {
        List<AccesorioResponse> resultado = manager.listar().stream()
                .filter(a -> a.getNombre().toLowerCase().contains(nombre.toLowerCase()))
                .collect(Collectors.toList());
        if (resultado.isEmpty()) {
            throw new IllegalArgumentException("No se encontraron accesorios con el nombre: " + nombre);
        }
        return resultado;
    }

    @CircuitBreaker(name = "accesorioService", fallbackMethod = "fallbackBuscarPorCategoria")
    public List<AccesorioResponse> buscarPorCategoria(String categoria) {
        List<AccesorioResponse> resultado = manager.listar().stream()
                .filter(a -> a.getCategoria() != null &&
                        a.getCategoria().toLowerCase().startsWith(categoria.toLowerCase()))
                .collect(Collectors.toList());
        if (resultado.isEmpty()) {
            throw new IllegalArgumentException("No se encontraron accesorios con la categoría: " + categoria);
        }
        return resultado;
    }

    @CircuitBreaker(name = "accesorioService", fallbackMethod = "fallbackListarConStock")
    public List<AccesorioResponse> listarConStock() {
        return manager.listar().stream()
                .filter(a -> a.getStock() != null && a.getStock() > 0)
                .collect(Collectors.toList());
    }


    public List<AccesorioResponse> fallbackListar(Throwable t) {
        return Collections.emptyList();
    }

    public AccesorioResponse fallbackBuscarPorId(Long id, Throwable t) {
        if (t instanceof AccesorioNotFoundException) throw (AccesorioNotFoundException) t;
        AccesorioResponse response = new AccesorioResponse();
        response.setNombre("Servicio no disponible");
        response.setEstado("NO DISPONIBLE");
        return response;
    }


    public AccesorioResponse fallbackCrear(AccesorioRequest request, Throwable t) {
        if (t instanceof IllegalArgumentException) throw (IllegalArgumentException) t;
        AccesorioResponse response = new AccesorioResponse();
        response.setNombre("Servicio no disponible temporalmente");
        response.setEstado("NO DISPONIBLE");
        return response;
    }

    public AccesorioResponse fallbackActualizar(Long id, AccesorioRequest request, Throwable t) {
        if (t instanceof AccesorioNotFoundException) throw (AccesorioNotFoundException) t;
        AccesorioResponse response = new AccesorioResponse();
        response.setNombre("No se pudo actualizar, servicio no disponible");
        response.setEstado("NO DISPONIBLE");
        return response;
    }

    public void fallbackEliminar(Long id, Throwable t) {
        if (t instanceof AccesorioNotFoundException) throw (AccesorioNotFoundException) t;
    }

    public List<AccesorioResponse> fallbackBuscarPorNombre(String nombre, Throwable t) {
        if (t instanceof IllegalArgumentException) throw (IllegalArgumentException) t;
        return Collections.emptyList();
    }

    public List<AccesorioResponse> fallbackBuscarPorCategoria(String categoria, Throwable t) {
        if (t instanceof IllegalArgumentException) throw (IllegalArgumentException) t;
        return Collections.emptyList();
    }

    public List<AccesorioResponse> fallbackListarConStock(Throwable t) {
        return Collections.emptyList();
    }
}