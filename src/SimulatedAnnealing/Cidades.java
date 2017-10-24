package SimulatedAnnealing;

public enum Cidades {
	A(0, "A"), B(1, "B"), C(2, "C"), D(3, "D"), E(4, "E"), F(5, "F"), G(6, "G"), 
	H(7, "H"),	I(8, "I"), J(9, "J"), L(10, "L"), M(11, "M"), N(12, "N"), O(13, "O"), 
	P(14, "P"), Q(15, "Q"), R(16, "R"), S(17, "S"), T(18, "T"), U(19, "U"), V(20, "V");
	
	private int value;
	private String name;
	
	private Cidades(int value, String name) {
		this.value = value;
		this.name = name;
	}
	
	public int getValue() {
		return value;
	}
	
	public String getName() {
		return name;
	}
}
