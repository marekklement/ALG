package alg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Marek Klement
 */
public class FileReader {

	public BSTree read() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		BSTree tree = new BSTree();
		int nodesBer = Integer.parseInt(reader.readLine());
		tree.size = nodesBer;
		String[] arr = reader.readLine().split(" ");
		String[] interval = reader.readLine().split(" ");
		tree.from = Integer.parseInt(interval[0]);
		tree.to = Integer.parseInt(interval[1]);
		for(int i = 0;i<nodesBer;++i){
			tree.add(Integer.parseInt(arr[i]));
		}
		return tree;
	}
}
