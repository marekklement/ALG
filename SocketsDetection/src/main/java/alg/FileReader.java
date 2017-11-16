package alg;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * File reader
 * @author Marek Klement
 */
public class FileReader {

//	private String path;
//
//	public FileReader(String path){
//		this.path = path;
//	}

	public ConnectionDev read() throws IOException {
		//
		int nodes;
		int edges;
		List<Integer>[] conns;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
		String[] firstLine = reader.readLine().split(" ");
		nodes = Integer.parseInt(firstLine[0]);
		edges = Integer.parseInt(firstLine[1]);
		int i;
		String[] line;

		conns = new List[edges + 1];
		for (i = 1; i <= edges; ++i) {
			conns[i] = new ArrayList();
		}
		for (i = 1; i <= edges; ++i) {
			line = reader.readLine().split(" ");
			int from = Integer.parseInt(line[0]);
			int to = Integer.parseInt(line[1]);
			conns[from].add(to);
			conns[to].add(from);
		}
		return new ConnectionDev(nodes,edges,conns);
	}

}
