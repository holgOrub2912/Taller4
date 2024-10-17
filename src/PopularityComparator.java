/* AUTORES: Isabela Arrubla Orozco, Fernando González Rivero */
public class PopularityComparator implements ComparadorPelicula {

    @Override
    public int compare(Pelicula primeraPeli, Pelicula segundaPeli) {
       return Double.compare(primeraPeli.getPopularity(), segundaPeli.getPopularity());
    }

	@Override
	public String getCriterionName(){
		return "Popularidad";
	}

	@Override
	public double getValue(Pelicula p){
		return p.getPopularity();
	}
}
