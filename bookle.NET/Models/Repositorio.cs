
using System;
using System.Collections.Generic;


namespace Repositorio
{
    public interface Repositorio<T, K>
    {

        K add(T entity);

        void update(T entity);

        void delete(T entity);

        T getById(K id);

        List<T> getAll();

        List<K> getIds();
    }

    public class EntidadNoEncontrada : SystemException
    {

        public EntidadNoEncontrada(String msg, Exception causa) : base(msg, causa)
        {

        }

        public EntidadNoEncontrada(String msg) : base(msg)
        {

        }
    }

    public class RepositorioException : SystemException
    {

        public RepositorioException(String msg, Exception causa) : base(msg, causa)
        {

        }

        public RepositorioException(String msg) : base(msg)
        {

        }

    }

}