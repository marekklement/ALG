package alg;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

/**
 * There is information about connections
 */
public class ConnectionDev {

	private List<Integer>[] conns;
	private int edges;
	private int nodes;

	public ConnectionDev(int nodes, int edges, List<Integer>[] conns){
		this.nodes = nodes;
		this.edges = edges;
		this.conns = conns;
	}

	Collection<Integer> lefts = new ArrayList<>();
	Collection<Integer> rights = new ArrayList<>();
	boolean[] wrongs;
	boolean[] solutions;

	public void solve(){
		int len = nodes+1;
		wrongs = new boolean[len];
		solutions = new boolean[len];
		for (int i = 1; i<nodes;++i){
			List<Integer> nod = conns[i];
			if(nod.size()==2){
				wrongs[i]=true;
				solutions[i]=true;
				int left = (int) conns[i].get(0);
				int right = (int) conns[i].get(1);
				lefts.add(left);
				rights.add(right);
				boolean endOfCycle = false;
				while(lefts.size()==rights.size()){
					lefts = addChidren(lefts);
					rights = addChidren(rights);
					//addIT();
					int size = lefts.size();
					if(size!=rights.size()) break;
					if(size == 0){
						endOfCycle = true;
						break;
					}
					checkSame();
				}
				if(endOfCycle){
					break;
				} else {
					wrongs = new boolean[len];
					solutions = new boolean[len];
					lefts.clear();
					rights.clear();
				}
			}
		}
	}
	public void printResult(){
		int solutionsCounter = 0;
		int size = solutions.length;
		int check = size-1;
		for(int i = 0; i<size;++i){
			if(solutions[i]){
				++solutionsCounter;
				if(solutionsCounter==100 || i==check)
					System.out.printf("%d",i);
				else
					System.out.printf("%d ",i);
			}
			if(solutionsCounter==100) break;
		}
	}

	private Collection<Integer> addChidren(Collection<Integer> children){
		Collection<Integer> newArr = new TreeSet<>();
		//int size = children.size();
		for (int node : children){
			//int node = children.get(i);
			List<Integer> nod = conns[node];
			wrongs[node]=true;
			if (!solutions[node]){
				int size2 = nod.size();
				for (int j=0;j<size2;++j){
					int current = (int) nod.get(j);
					if(!wrongs[current] && !solutions[current]){
						newArr.add(current);
					}
				}
			}
		}
		return newArr;
	}

//	private void addIT(){
//		List<Integer> newArrLeft = new ArrayList<>();
//		List<Integer> newArrRight = new ArrayList<>();
//		int size = lefts.size();
//		for (int i=0;i<size;++i){
//			int nodeLF = lefts.iterator().next();
//			int nodeRG = rights.iterator().next();
//			List<Integer> nodLF = conns[nodeLF];
//			List<Integer> nodRG = conns[nodeRG];
//			wrongs[nodeLF]=true;
//			wrongs[nodeRG]=true;
//			int size2 = nodLF.size();
//			for (int j=0;j<size2;++j){
//				int current = (int) nodLF.get(j);
//				if(!wrongs[current] && !solutions[current]){
//					newArrLeft.add(current);
//				}
//			}
//
//			size2 = nodRG.size();
//			for (int j=0;j<size2;++j){
//				int current = (int) nodRG.get(j);
//				if(!wrongs[current] && !solutions[current]){
//					newArrRight.add(current);
//				}
//			}
//		}
//		lefts = newArrLeft;
//		rights = newArrRight;
//	}

	private void checkSame(){
		int size = lefts.size();
		for (int current : lefts){
			if(rights.contains(current)){
				solutions[current] = true;
			}
		}
	}
}
