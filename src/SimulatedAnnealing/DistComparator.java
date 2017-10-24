package SimulatedAnnealing;

import java.util.Comparator;
import java.util.List;

public class DistComparator<T> implements Comparator<Object> {

	private List<CidadesV> cidades;
	
	public DistComparator(List<CidadesV> cidades) {
		this.cidades = cidades ;
	}

	@Override
	public int compare(Object o1, Object o2) {
		int x = 0;
		int y = 0;
		for(int i = 0;i<cidades.size();i++) {
			if(cidades.get(i).getC() == (int)o1) {
				x = i;
			}
			if(cidades.get(i).getC() == (int)o2) {
				y = i;
			}
				
		}
		
		if(cidades.get(x).calcDist() > cidades.get(y).calcDist()) {
			return 1;
		}
		if(cidades.get(x).calcDist() <= cidades.get(y).calcDist()) {
			return -1;
		}
		return 0;
	}
	
	

}
