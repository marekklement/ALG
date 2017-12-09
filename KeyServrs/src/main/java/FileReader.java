import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

/**
 * @author Marek Klement
 */
public class FileReader {

	public Graph read() throws IOException {
		int i;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String [] line = reader.readLine().trim().split("\\s+");
		int numberOfServers = Integer.parseInt(line[0]);
		int numberOfKeys = Integer.parseInt(line[1]);
		List<Edge> listOfEdges = new LinkedList<Edge>();
		List[] connections = new List[numberOfServers];
		for (i=0; i<numberOfServers;++i){
			connections[i] = new LinkedList();
		}
		for (i=0;i<numberOfServers;++i){
			line = reader.readLine().trim().split("\\s+");
			int from = Integer.parseInt(line[0]);
			int to = Integer.parseInt(line[1]);
			int cost = Integer.parseInt(line[2]);
			Edge edge = new Edge(from,to,cost);
			listOfEdges.add(edge);
			connections[from].add(to);
			connections[to].add(from);
		}
		boolean[] servers = new boolean[numberOfKeys];
		String[] lineSer = reader.readLine().trim().split("\\s+");
		int len = line.length;
		for (i=0;i<len;++i){
			int server = Integer.parseInt(line[i]);
			servers[server] = true;
		}
		return new Graph(numberOfServers,numberOfKeys,listOfEdges,servers, connections);
	}
}
