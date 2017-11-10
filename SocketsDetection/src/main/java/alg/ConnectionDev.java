package alg;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * There is information about connections
 */
public class ConnectionDev {

	private List[] conns;
	private int edges;
	private int nodes;
	private List<Integer> wentThrew = new LinkedList<>();
	private List<Integer> solutions = new LinkedList<>();

	public ConnectionDev(int nodes, int edges, List[] conns){
		this.nodes = nodes;
		this.edges = edges;
		this.conns = conns;
	}

	public void solve(){
		int node;
		for(int i=1;i<=nodes;++i){
			List<Integer> con = conns[i];
			if(con.size()==2){
				solutions.add(i);
				wentThrew.add(i);
				int left = (Integer) conns[i].get(0);
				int right = (Integer) conns[i].get(1);
				wentThrew.add(left);
				wentThrew.add(right);
				List<Integer> candidatesLeft = new LinkedList<>();
				List<Integer> candidatesRight = new LinkedList<>();
				List<Integer> leftCon = conns[left];
				List<Integer> rightCon = conns[right];
				int size = leftCon.size();
				if(size != rightCon.size())
				{
					solutions.clear();
					wentThrew.clear();
					continue;
				}
				for(int j = 0; j<size;++j){
					int r = rightCon.get(j);
					int l = leftCon.get(j);
					if(!wentThrew.contains(r)){
						candidatesRight.add(r);
					}
					if(!wentThrew.contains(l)){
						candidatesLeft.add(l);
					}
				}
				if(solveRec(candidatesLeft.size(),candidatesLeft,candidatesRight)){
					printResult();
					break;
				} else {
					continue;
				}
			}
		}
	}

	public boolean solveRec(int size, List<Integer> candidatesLeft, List<Integer> candidatesRight){
		if(wentThrew.size()==nodes) return true;
		for (int i = 0; i<size; ++i){
			int candidateI = candidatesLeft.get(i);
			if(wentThrew.contains(candidateI)) continue;
			for(int j = 0; i<size; ++j){
				int candidateJ = candidatesRight.get(i);
				if(wentThrew.contains(candidateJ)) continue;
				List<Integer> leftList = conns[candidateI];
				List<Integer> rightList = conns[candidateJ];
				int lsize = leftList.size();
				if(lsize!=rightList.size()) continue;
				List<Integer> newLCandidates = new ArrayList<>();
				List<Integer> newRCandidates = new ArrayList<>();
				for(int k = 0; k<lsize;++k){
					int l = (int) leftList.get(k);
					int r = (int) rightList.get(k);
					if(!wentThrew.contains(l)){
						newLCandidates.add(l);
					}
					if(!wentThrew.contains(r)){
						newRCandidates.add(r);
					}
				}
				if(wentThrew.contains(candidateI)) solutions.add(candidateI);
				else wentThrew.add(candidateI);
				if(wentThrew.contains(candidateJ)) solutions.add(candidateJ);
				else wentThrew.add(candidateJ);
				int newsize = newLCandidates.size();
				if(newsize!=newRCandidates.size())
				if(solveRec(newsize,newLCandidates,newRCandidates)) return true;
			}
		}
		return false;
	}

	private boolean goUp(List<Integer> con){
		int lnum = con.get(0);
		int rnum = con.get(1);
		List<Integer> left = conns[lnum];
		List<Integer> right = conns[rnum];
		int size = left.size();
		if (size == right.size()) {
			int lft = 0, rgt = 0;
			for (int i=0;i<size;++i) {
				lft = left.get(i);
				rgt = right.get(i);
				if(wentThrew.contains(lft) && !solutions.contains(lft) && lft!=lnum) solutions.add(lft);
				else if(lft!=lnum && !wentThrew.contains(lft)) wentThrew.add(lft);
				if(wentThrew.contains(rgt) && !solutions.contains(rgt) && rgt!=rnum) solutions.add(rgt);
				else if(rgt!=rnum && !wentThrew.contains(rgt)) wentThrew.add(rgt);
			}
			List<Integer> connection = conns[lft];
			if(goUpRec(connection.size(),connection,conns[rgt],lft, rgt, lnum, rnum)) return true;
			else return false;
		} else {
			solutions.clear();
			wentThrew.clear();
			return false;
		}
	}

	private boolean goUpRec(int size, List<Integer> left, List<Integer> right, int lidx, int ridx, int lnum, int rnum){
		if(wentThrew.size()==nodes) return true;
		for (int i = 0; i < size; ++i) {
			int candidateI = left.get(i);
			if(wentThrew.contains(candidateI)) continue;
			for(int j = 0;j < size; ++j){
				int candidateJ = left.get(i);
				int otherSize = conns[candidateI].size();
				if(otherSize==conns[candidateJ].size() && !wentThrew.contains(candidateJ)){
					int lft = 0, rgt = 0;
					for (int k=0;k<size;++k) {
						lft = left.get(k);
						rgt = right.get(k);
						if(wentThrew.contains(lft) && !solutions.contains(lft) && lft!=lnum) solutions.add(lft);
						else if(lft!=lnum && !wentThrew.contains(lft)) wentThrew.add(lft);
						if(wentThrew.contains(rgt) && !solutions.contains(rgt) && rgt!=rnum) solutions.add(rgt);
						else if(rgt!=rnum && !wentThrew.contains(rgt)) wentThrew.add(rgt);
					}
					if(goUpRec(otherSize,conns[lft],conns[rgt],lft, rgt, lidx, ridx)) return true;

				} else continue;
			}
		}
		return false;
	}

	private void printResult(){
		System.out.println(solutions);
	}
}
