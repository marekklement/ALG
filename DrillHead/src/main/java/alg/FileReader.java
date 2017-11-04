package alg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Marek Klement
 */
public class FileReader {

	public CabelsDevices read() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int numberOfItems = Integer.parseInt(reader.readLine());
		int i,j, k,parseFromString, minim;
		int [][] arr = new int[numberOfItems][numberOfItems];
		minim = Integer.MAX_VALUE;
		for(i=0; i<numberOfItems;++i){
			String [] line = reader.readLine().trim().split("\\s+");
			for (j=0;j<numberOfItems;++j){
				parseFromString = Integer.parseInt(line[j]);
				if(parseFromString<minim) minim = parseFromString;
				arr[i][j] = parseFromString;
			}
		}
		int end = numberOfItems - 1;
		int [] conectionsCounter = new int[numberOfItems];
		List[] conns = new List[numberOfItems];
		int it = 0;
		for(int dev : conectionsCounter){
			dev = 0;
			conns[it]= new LinkedList<Integer>();
			++it;
		}
		Tuple[] tuples = new Tuple[end];

		for (k=0;k<end;++k){
			String [] both = reader.readLine().split(" ");
			int from = Integer.parseInt(both[0]);
			int to = Integer.parseInt(both[1]);
			conectionsCounter[from]+=1;
			conectionsCounter[to]+=1;
			conns[from].add(to);
			conns[to].add(from);
			tuples[k]= new Tuple(from,to);
		}
		return new CabelsDevices(numberOfItems,arr,tuples, minim, conectionsCounter, conns);
	}
}
