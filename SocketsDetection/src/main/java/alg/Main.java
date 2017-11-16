package alg;

import java.io.IOException;

/**
 * Main class for solution
 * @author Marek Klement
 */
public class Main {
	public static void main(String[] args) throws IOException {
		//long start = System.currentTimeMillis();
		FileReader reader = new FileReader();
//		FileReader reader = new FileReader(args[0]);
		ConnectionDev dev = reader.read();
		dev.solve();
		dev.printResult();
		//long end = System.currentTimeMillis() - start;
		//System.out.println();
		//System.out.println(end);
	}
}
