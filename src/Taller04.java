import java.io.IOException;
import java.util.InputMismatchException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.IntPredicate;
import edu.princeton.cs.algs4.BST;
import edu.princeton.cs.algs4.StdIn;

public class Taller04 {
    public static final String RUTA_CSV = "final_dataset.csv";
    public static int promptInteger(String prompt, IntPredicate constraint){
		int r;
		do {
			System.out.print(prompt);
			try {
				r = StdIn.readInt();
				if ( !constraint.test(r) )
				    throw new InputMismatchException();
				return r;
			} catch(InputMismatchException e) {
				System.out.println("Valor inválido.");
			}
		} while (true);
    }
    public static String menuGenero(Iterable<String> generosIt){
		System.out.println("Por favor elige el género de películas del Top: ");
		int i = 0;
		ArrayList<String> generosLst = new ArrayList<String>();
        for (String genero: generosIt){
            System.out.printf("%2d) %s\n", ++i, genero);
            generosLst.add(genero);
        }
        int selection = promptInteger("", sel -> (sel > 0 && sel <= generosLst.size()));
        return generosLst.get(selection-1);
    }
    public static void main(String[] args) throws Exception {
        try {
            // Leer las películas desde el archivo CSV
            List<Pelicula> lista_pelis = Pelicula.leerPelisdeCSV(RUTA_CSV);
            
            // Convertir la lista a un arreglo de Pelicula
            Pelicula[] array_pelis = lista_pelis.toArray(new Pelicula[0]);
            
            // Clasificar las películas por genero y año
            ST<String, BST<Integer, List<Pelicula>>> clasificacion = Pelicula.clasificarPeliculas(array_pelis);
            
	          // Imprimir la clasificación
						// Pelicula.imprimirClasificacion(clasificacion);


            // Determinar las top-M películas de un genero en un rango de años
            String generoBuscado = menuGenero(clasificacion.keys());
            int anoInicio = promptInteger("Ingrese el año de inicio: ", sel -> true);
            int anoFin = promptInteger("Ingrese el año de finalización: ", sel -> sel > anoInicio);
            int M = promptInteger("Ingresse la cantidad de películas en el top: ", sel -> sel > 0); 
            
            List<Pelicula> topPelis = Pelicula.topGeneroAnios(clasificacion, generoBuscado, anoInicio, anoFin, M);
            
            System.out.println();
            // Imprimir las top-M películas
            System.out.println("Top " + M + " películas de genero " + generoBuscado + " entre " + anoInicio + " y " + anoFin + ":");
            if (topPelis != null)
	            for (int i = 0; i < topPelis.size(); i++) {
	                System.out.printf("%2d) %s\n", i+1, topPelis.get(i).toString());
	            }
	          else
	          	System.out.println("No se encontraron películas");
        } catch (IOException e) {
            System.err.println("Error al leer el archivo CSV: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Ocurrió un error inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
