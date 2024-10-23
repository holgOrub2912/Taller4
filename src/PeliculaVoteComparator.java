/* AUTORES: Isabela Arrubla Orozco, Fernando González Rivero */
import java.util.Comparator;

public class PeliculaVoteComparator implements Comparator<Pelicula> {
    @Override
    public int compare(Pelicula p1, Pelicula p2) {
        return Double.compare(p1.getVoteAverage(), p2.getVoteAverage());
    }
}
