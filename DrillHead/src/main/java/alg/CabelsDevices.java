package alg;

import java.util.List;
import java.util.Stack;

/**
 * @author Marek Klement
 */
public class CabelsDevices {

	private int nuberOfItems;
	private int[] conectionCounter;
	List[] conns;
	private int[][] costs;
	private Tuple[] ways;
	private int holesMin;
	private int memoryJ=0;
	private Stack<Integer> stack = new Stack<>();
	int minCost = Integer.MAX_VALUE;

	public CabelsDevices(int nuberOfItems, int [][] costs, Tuple[] ways, int holesMin, int[] conectionCounter, List[] conns){
		this.nuberOfItems = nuberOfItems;
		this.costs = costs;
		this.ways = ways;
		this.holesMin = holesMin;
		this.conectionCounter = conectionCounter;
		this.conns = conns;
	}

	public void solve(){
		int [] holes = new int[nuberOfItems];
		int [] placed = new int[nuberOfItems];
		for (int i = 0; i<this.nuberOfItems; ++i){
			holes[i]=i;
			placed[i]=-1;
		}
		solveRec(holes,placed,0,0);
	}

	private void solveRec(int [] holes,int [] placed, int j, int costOfNode){
		int i, totalCost, putHere, size;
		int [] otherHoles;
		int to = holes.length;
		for(i = 0; i<to; ++i){
			putHere = holes[i];
			placed[j] = putHere;
			// rows=holes cols=device
			totalCost = costOfNode + costs[j][putHere];
			otherHoles = getOthers(putHere, holes);
			size=otherHoles.length;
			int nextJ = j+1;
//			System.out.println(isCrossed(nextJ, placed));
//			System.out.println(predicateCost(size,totalCost));
//			System.out.println("//");
			if( predicateCost(size,totalCost) || isCrossed(nextJ, placed)) continue;
			if(size==0){
				if(minCost>totalCost){
					minCost = totalCost;
					continue;
				}
			}
			solveRec(otherHoles,placed,nextJ,totalCost);
		}
	}

	private int[] getOthers(int placedHole, int [] freeHoles){
		int i=0;
		int to = freeHoles.length;
		int [] finalArr = new int[to-1];
		for(int hole: freeHoles){
			if(hole!=placedHole){
				finalArr[i]=hole;
				++i;
			}
		}
		return finalArr;
	}

	private boolean isCrossed(int j, int [] placed){
//		System.out.println(stack);
		if(memoryJ>=j) {
			stack.clear();
			for(int i = 0; i<j;++i){
				int couner = 0;
				int node = placed[i];
				Stack<Integer> copy = (Stack<Integer>) stack.clone();
				for(int k=0;k<conectionCounter[node];++k){
					List<Integer> ls = conns[node];
					if(copy.contains(ls.get(k))){
						int poped = stack.pop();
						if(poped != ls.get(k) && !ls.contains(poped)){
							stack.push(poped);
							return true;
						}
					}else{
						couner+=1;
					}
				}
				for (int l=0;l<couner;++l){
					stack.push(node);
				}
				//return false;
			}
		} else {
			int couner = 0;
			int node = placed[j-1];
			Stack<Integer> copy = (Stack<Integer>) stack.clone();
			for(int k=0;k<conectionCounter[node];++k){
				List<Integer> ls = conns[node];
				if(copy.contains(ls.get(k))){
					int poped = stack.pop();
					if(poped != ls.get(k) && !ls.contains(poped)){
						stack.push(poped);
						return true;
					}
				}else{
					couner+=1;
				}
			}
			for (int l=0;l<couner;++l){
				stack.push(node);
			}
			//return false;
		}

		memoryJ = j;
		boolean ret = (memoryJ == nuberOfItems) && !stack.isEmpty();
		return ret;
	}

	private void goThrueTuples(){

	}

	private boolean predicateCost(int toGo, int cost){
		int ret = cost + (holesMin*toGo);
		return ret >= minCost;
	}

	@Override
	public String toString() {
		String output = String.format("%d\n", nuberOfItems);
		for (int i = 0; i<nuberOfItems;++i) {
			for (int j = 0;j<nuberOfItems;++j){
				if(j==nuberOfItems-1){
					output += costs[i][j] + "\n";
				} else {
					output += costs[i][j] + " ";
				}
			}
		}
		for (int i = 0;i<nuberOfItems-1;++i){
			output += ways[i];
		}
		return output;
	}
}
