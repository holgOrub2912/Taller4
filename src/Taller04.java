import java.io.IOException;
import java.util.List;
import edu.princeton.cs.algs4.BST;

public class Taller04 {
    public static final String RUTA_CSV = "final_dataset.csv";
    public static void main(String[] args) throws Exception {
        try {
            // Leer las películas desde el archivo CSV
            List<Pelicula> lista_pelis = Pelicula.leerPelisdeCSV(RUTA_CSV);
            
            // Convertir la lista a un arreglo de Pelicula
            Pelicula[] array_pelis = lista_pelis.toArray(new Pelicula[0]);
            
            // Clasificar las películas por genero y año
            ST<String, BST<Integer, List<Pelicula>>> clasificacion = Pelicula.clasificarPeliculas(array_pelis);
            
            // Imprimir la clasificación
           Pelicula.imprimirClasificacion(clasificacion);


            // Determinar las top-M películas de un genero en un rango de años
            String generoBuscado = "Action";
            int anoInicio = 2009;
            int anoFin = 2012;
            int M = 10; 
            
            List<Pelicula> topPelis = Pelicula.topGeneroAnios(clasificacion, generoBuscado, anoInicio, anoFin, M);
            
            // Imprimir las top-M películas
            /* 
            System.out.println("Top " + M + " películas de genero " + generoBuscado + " entre " + anoInicio + " y " + anoFin + ":");
            for (Pelicula peli : topPelis) {
                System.out.println(peli);
            }
            */
        } catch (IOException e) {
            System.err.println("Error al leer el archivo CSV: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Ocurrió un error inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
