import java.util.List;

/**
 * @author Marek Klement
 */
public class Graph {

	private int numberOfServers;
	private int numberOfKeys;
	private List<Edge> listOfEdges;
	private boolean[] keyServs;
	private List[] conns;

	public Graph(int numberOfServers, int numberOfKeys, List<Edge> listOfEdges, boolean[] keyServs, List[] conns){
		this.numberOfKeys = numberOfKeys;
		this.numberOfServers = numberOfServers;
		this.listOfEdges = listOfEdges;
		this.keyServs = keyServs;
		this.conns = conns;
	}

	public void solve(){

	}

	private void findLoop(){
		boolean[] beenIn = new boolean[numberOfServers];
		for(int i = 0; i < numberOfServers;++i){

		}

	}
}
