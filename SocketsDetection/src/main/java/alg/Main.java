package alg;

import java.io.IOException;

/**
 * Main class for solution
 * @author Marek Klement
 */
public class Main {
	public static void main(String[] args) throws IOException {
		FileReader reader = new FileReader();
		ConnectionDev dev = reader.read();
		dev.solve();
	}
}
