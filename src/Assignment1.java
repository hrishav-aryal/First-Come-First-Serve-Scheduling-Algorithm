public class Assignment1 {

	public static void main(String[] args) {
		
		if(args.length != 4) throw new IllegalArgumentException("Must pass at least four arguments.");
		
		int n = Integer.parseInt(args[0]);
		int k = Integer.parseInt(args[1]);
		int d = Integer.parseInt(args[2]);
		int v = Integer.parseInt(args[3]);
		
		Simulator simulator = new Simulator(n, k, d, v);
		simulator.start();

	}

}
