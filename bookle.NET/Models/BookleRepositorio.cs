
using Repositorio;
using MongoDB.Driver;
using System.Collections.Generic;
using System.Linq;
using MongoDB.Bson;
using Bookle.Modelo;

namespace Bookle.Repositorio
{

    public interface RepositorioActividades : Repositorio<Actividad, string>
    {

        // Se pueden incluir métodos adicionales


    }

    public class RepositorioActividadesMongoDB : RepositorioActividades
    {

        
        private readonly IMongoCollection<Actividad> actividades;

       
        public RepositorioActividadesMongoDB()
        {
            var client = new MongoClient("mongodb://arso:Ux1WkJEbALErSIm2@cluster0-shard-00-00.mp1o0.azure.mongodb.net:27017,cluster0-shard-00-01.mp1o0.azure.mongodb.net:27017,cluster0-shard-00-02.mp1o0.azure.mongodb.net:27017/myFirstDatabase?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin&retryWrites=true&w=majority");
            var database = client.GetDatabase("test");

            actividades = database.GetCollection<Actividad>("bookle");
        }

        public string add(Actividad entity)
        {
            entity.Id = new ObjectId();
            actividades.InsertOne(entity);

            return entity.Id.ToString();
        }

        public void update(Actividad entity)
        {
            actividades.ReplaceOne(actividad => actividad.Id == entity.Id, entity);
        }

        public void delete(Actividad entity)
        {
            actividades.DeleteOne(actividad => actividad.Id == entity.Id);
        }

        public List<Actividad> getAll()
        {
            return actividades.Find<Actividad>(_ => true).ToList();
        }

        public Actividad getById(string id)
        {
            return actividades
                .Find<Actividad>(actividad => actividad.Id == new ObjectId(id))
                .FirstOrDefault();
        }

        public List<string> getIds()
        {
            var todas =  actividades.Find<Actividad>(_ => true).ToList();

            return todas.Select(p => p.Id.ToString()).ToList();

        }


    }

    

}