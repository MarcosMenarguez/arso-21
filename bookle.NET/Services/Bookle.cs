
using System;
using System.Collections.Generic;
using Bookle.Modelo;
using Bookle.Repositorio;
using Repositorio;

namespace Bookle.Servicio
{

    [ToString]
    public class ActividadResumen
    {

        public String Id { get; set; }
        public String Titulo { get; set; }
        public String Profesor { get; set; }

    }
    public interface IServicioBookle
    {

        string create(Actividad actividad);

        void update(Actividad actividad);

        Actividad getActividad(string id);

        void removeActividad(string id);

        bool reservar(string idActividad, DateTime fecha, int indice, string alumno, string email);

        List<ActividadResumen> getListadoActividades();
    }

    public class ServicioBookle : IServicioBookle
    {

        private RepositorioActividades repositorio;

        public ServicioBookle(RepositorioActividades repositorio)
        {

            this.repositorio = repositorio;
        }


        public String create(Actividad actividad)
        {

            return repositorio.add(actividad);

        }

        public void update(Actividad actividad)
        {

            repositorio.update(actividad);
        }

        public Actividad getActividad(String id)
        {

            return repositorio.getById(id);
        }

        public void removeActividad(String id)
        {

            Actividad actividad = repositorio.getById(id);

            repositorio.delete(actividad);
        }

        public bool reservar(String id, DateTime fecha, int indice, String alumno, String email)
        {
            if (indice < 1)
                throw new ArgumentException("El primer turno tiene índice 1");

            if (alumno == null || alumno == "")
                throw new ArgumentException("El nombre del alumno no debe ser vacío");

            // email es opcional

            Actividad actividad = repositorio.getById(id);

            DiaActividad diaActividad = actividad.Agenda.Find(dia => dia.Fecha == fecha);

            if (diaActividad == null)
                throw new ArgumentException("La fecha no esta en la agenda: " + fecha);

            if (indice > diaActividad.Turnos.Count)
                throw new ArgumentException("No existe el turno " + indice + " para la fecha " + fecha);

            Turno turno = diaActividad.Turnos[indice - 1];

            if (turno.Reserva != null)
                return false;

            turno.Reserva = new Reserva()
            {
                Alumno = alumno,
                Email = email
            };

            repositorio.update(actividad);

            return true;
        }

        public List<ActividadResumen> getListadoActividades()
        {

            List<ActividadResumen> resultado = new List<ActividadResumen>();

            foreach (String id in repositorio.getIds())
            {

                try
                {
                    Actividad actividad = getActividad(id);
                    ActividadResumen resumen = new ActividadResumen
                    {
                        Id = actividad.Id.ToString(),
                        Titulo = actividad.Titulo,
                        Profesor = actividad.Profesor
                    };
                    resultado.Add(resumen);
                }
                catch (EntidadNoEncontrada e)
                {
                    // No debe suceder
                    Console.WriteLine(e.StackTrace); // para depurar
                }
            }

            return resultado;
        }

    }


}