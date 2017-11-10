package alg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * File reader
 * @author Marek Klement
 */
public class FileReader {

	public ConnectionDev read() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String[] firstLine = reader.readLine().trim().split("\\s+");
		int nodes = Integer.parseInt(firstLine[0]);
		int edges = Integer.parseInt(firstLine[1]);
		int i;
		String[] line;

		List[] conns = new List[edges+1];
		for(i=1;i<=edges;++i){
			conns[i] = new ArrayList<>();
		}
		for(i=1;i<=edges;++i){
			line = reader.readLine().trim().split("\\s+");
			int from = Integer.parseInt(line[0]);
			int to = Integer.parseInt(line[1]);
			conns[from].add(to);
			conns[to].add(from);
		}
		return new ConnectionDev(nodes,edges,conns);
	}

}
