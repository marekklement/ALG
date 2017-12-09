import java.io.IOException;

/**
 * @author Marek Klement
 */
public class Main {

	public static void main(String[] args) throws IOException {
		FileReader reader = new FileReader();
		Graph graph = reader.read();
	}
}
