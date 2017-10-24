package SimulatedAnnealing;

public class CidadesV {

	public int x;
	public int y;
	public int c;
	public int sort;
	
	
	
	public CidadesV(int c, int x, int y) {
		this.x = x;
		this.y = y;
		this.c = c;
		this.sort = x + y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getC() {
		return c;
	}
	
	public int calcDist() {
		return sort;
	}
	
	
}
