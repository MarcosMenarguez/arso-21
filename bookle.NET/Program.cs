using System;
using System.Collections.Generic;
using Bookle.Modelo;
using Bookle.Repositorio;
using Bookle.Servicio;

namespace bookle1
{
    class Program
    {
        static void Main(string[] args)
        {
            // Creación de una actividad    

            Actividad actividad = new Actividad
            {
                Profesor = "Marcos",
                Titulo = "Entrevistas de prácticas",
                Descripcion = "Enlace Zoom ...",
                Email = "marcos@um.es",
                Agenda = new List<DiaActividad>()

            };

            // Dia 1

            DiaActividad dia1 = new DiaActividad
            {
                Fecha = new DateTime(2021, 5, 4, 0, 0, 0, DateTimeKind.Utc),
                Turnos = new List<Turno>()

            };
            dia1.Turnos = new List<Turno>();
            for (int hora = 10; hora <= 14; hora++)
            {
                Turno turno = new Turno()
                {
                    Horario = hora + ":00h"
                };

                dia1.Turnos.Add(turno);
            }
            actividad.Agenda.Add(dia1);

            // Día 2

            DiaActividad dia2 = new DiaActividad
            {
                Fecha = new DateTime(2021, 5, 5, 0, 0, 0, DateTimeKind.Utc),
                Turnos = new List<Turno>()

            };
            for (int hora = 17; hora <= 19; hora++)
            {
                Turno turno = new Turno()
                {
                    Horario = hora + ":00h"
                };

                dia2.Turnos.Add(turno);
            }
            actividad.Agenda.Add(dia2);


            
            RepositorioActividades repositorio = new RepositorioActividadesMongoDB();

            IServicioBookle servicio = new ServicioBookle(repositorio);

            // 1. Creación

            string id = servicio.create(actividad);

            // 2. Actualización

            actividad = servicio.getActividad(id);

            actividad.Descripcion = "Enlace Zoom";

            servicio.update(actividad);

            // 3. Reserva

            DateTime fechaReserva = new DateTime(2021, 5, 4, 0, 0, 0, DateTimeKind.Utc);

            bool resultado = servicio.reservar(id, fechaReserva, 1, "Juan", "juan@um.es");

            Console.WriteLine("Reserva aceptada: " + resultado);

            // 4. Listado de resúmenes

            Console.WriteLine("Listado:");
            foreach (ActividadResumen resumen in servicio.getListadoActividades())
                Console.WriteLine("\t" + resumen);


            Console.WriteLine("Fin.");


        }
    }
}
