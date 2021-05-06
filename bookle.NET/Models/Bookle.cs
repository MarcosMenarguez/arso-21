using MongoDB.Bson;
using MongoDB.Bson.Serialization.Attributes;
using System;
using System.Collections.Generic;

namespace Bookle.Modelo
{
    [ToString]
    public class Actividad
    {
        [BsonId]
        [BsonRepresentation(BsonType.ObjectId)]
        public ObjectId Id { get; set; }

        public string Titulo { get; set; }
        public string Descripcion { get; set; }

        public string Profesor { get; set; }

        public string Email { get; set; }

        public List<DiaActividad> Agenda { get; set; }

    }

    [ToString]
    public class DiaActividad {

        public DateTime Fecha { get; set; }

        public List<Turno> Turnos { get; set; }

    }

    [ToString]
    public class Turno {

        public string Horario { get; set; }

        public Reserva Reserva { get; set; }
    }

    [ToString]
    public class Reserva {

        public string Alumno { get; set; }
        public string Email { get; set; }
    }
}
