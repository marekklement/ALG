package alg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * TODO describtion
 *
 * @author Marek Klement
 */
public class FileReader {

	int[][] pole;
	boolean[][] went;
	LinkedList<Robot> listOfRobots;
	int robots;
	int rows;
	int cols;
	int solution = 0;

	public void read() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String[] firstLine = reader.readLine().split(" ");
		rows = Integer.parseInt(firstLine[0]);
		cols = Integer.parseInt(firstLine[1]);
		pole = new int[rows][cols];
		went = new boolean[rows][cols];
		//System.out.println(went[0][0]);
		for(int i = 0; i<rows;++i){
			String[] line = reader.readLine().split(" ");
			for (int j = 0; j< cols;++j){
				pole[i][j] = Integer.parseInt(line[j]);
			}
		}
		robots = Integer.parseInt(reader.readLine().split(" ")[0]);
		listOfRobots = new LinkedList<Robot>();
		for(int i =0; i < robots;++i){
			String[] ln = reader.readLine().split(" ");
			int beginI = Integer.parseInt(ln[0]);
			int beginJ = Integer.parseInt(ln[1]);
			int endI = Integer.parseInt(ln[2]);
			int endJ = Integer.parseInt(ln[3]);
			Robot robot = new Robot(beginI,beginJ,endI,endJ,beginI==endI);
			listOfRobots.add(robot);
		}
//		printArr(pole);
//		printRobots();
	}

	public void solving(){
		while(!listOfRobots.isEmpty()){
			solve();
		}
		System.out.println(solution);
	}

	private void solve(){
		int maxNum = Integer.MIN_VALUE;
		Robot maxRob = null;
		for (Robot r : listOfRobots){
			if(r.line){
				r.total = countTotalsRows(r.beginI,r.beginJ,r.endJ);
			} else {
				r.total = countTotalsCols(r.beginJ,r.beginI,r.endI);
			}
			if(r.total>maxNum){
				maxNum = r.total;
				maxRob = r;
			}
		}
		fillWent(maxRob);
		solution+=maxNum;
		listOfRobots.remove(maxRob);
	}

	private void fillWent(Robot robot){
		if(robot.line){
			if(robot.beginJ<=robot.endJ){
				for (int i = robot.beginJ;i<=robot.endJ;++i){
					went[robot.beginI][i]=true;
				}
			} else {
				for (int i = robot.beginJ;i>=robot.endJ;--i){
					went[robot.beginI][i]=true;
				}
			}
		}else {
			if(robot.beginI<=robot.endI){
				for (int i = robot.beginI;i<=robot.endI;++i){
					went[i][robot.beginJ]=true;
				}
			} else {
				for (int i = robot.beginI;i>=robot.endI;--i){
					went[i][robot.beginJ]=true;
				}
			}
		}
	}

	private int countTotalsRows(int stable, int iI, int jJ){
		int total = 0;
		if(iI<=jJ) {
			for (int i = iI; i <= jJ; ++i) {
				if(!went[stable][i])
					total += pole[stable][i];
				else break;
			}
		} else {
			for (int i = iI; i >= jJ; --i) {
				if(!went[stable][i])
					total += pole[stable][i];
				else break;
			}
		}
		return total;
	}

	private int countTotalsCols(int stable, int iI, int jJ){
		int total = 0;
		if(iI<=jJ) {
			for (int i = iI; i <= jJ; ++i) {
				if (!went[i][stable])
					total += pole[i][stable];
				else break;
			}
		} else {
			for (int i = iI; i >= jJ; --i) {
				if(!went[i][stable])
					total += pole[i][stable];
				else break;
			}
		}
		return total;
	}

	private void printArr(int[][]arr){
		for (int i = 0; i<rows;++i){
			for (int j = 0 ; j<cols;++j){
				System.out.printf(arr[i][j]+" ");
			}
			System.out.println();
		}
	}

	private void printRobots(){
		for(Robot rob : listOfRobots){
			System.out.println(rob);
		}
	}
}
