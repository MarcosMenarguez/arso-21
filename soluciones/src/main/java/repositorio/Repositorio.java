package repositorio;

import java.util.List;

// https://medium.com/@pererikbergman/repository-design-pattern-e28c0f3e4a30

// Repositorio para entidades gestionadas con id

public interface Repositorio <T, K> {
    
    K add(T entity) throws RepositorioException;
    
    void update(T entity) throws RepositorioException, EntidadNoEncontrada;
    
    void delete(T entity) throws RepositorioException, EntidadNoEncontrada;

    T getById(K id) throws RepositorioException, EntidadNoEncontrada;
    
	List<T> getAll() throws RepositorioException;

	List<K> getIds();
}
