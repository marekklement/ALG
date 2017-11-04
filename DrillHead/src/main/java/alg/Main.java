package alg;

import java.io.IOException;

/**
 * @author Marek Klement
 */

public class Main {

	public static void main(String[] args) throws IOException {
		FileReader reader = new FileReader();
		CabelsDevices data = reader.read();
		data.solve();
//		System.out.println(Integer.MAX_VALUE);
		System.out.println(data.minCost);
		//System.out.println(data.toString());
	}
}
