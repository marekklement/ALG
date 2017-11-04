package alg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Marek Klement
 */
public class MatrixReader {

	private int rows = 0;
	private int cols = 0;

	public MiddleNumber[][] read() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String [] prep = reader.readLine().split(" ");
		rows = Integer.parseInt(prep[0]);
		cols = Integer.parseInt(prep[1]);
		MiddleNumber [][] result = new MiddleNumber[rows][cols];

		for (int i=0; i<rows; ++i){
			String [] line = reader.readLine().split(" ");

			for (int j = 0;j<cols;++j){
				int parseFromString = reverseTwo(Integer.parseInt(line[j]));
				MiddleNumber operandOne;
				MiddleNumber operandTwo;
				MiddleNumber together;
				int ones;
				if (j==0 && i==0){
					result[i][j]=new MiddleNumber(parseFromString,parseFromString== 1 ? 1 : 0);
				}else {
					if (i ==0 || j == 0) together = new MiddleNumber(0,0);
					else together= result[i-1][j-1];
					if (j == 0) operandOne = new MiddleNumber(0,0);
					else operandOne = result[i][j-1];
					if (i==0) operandTwo = new MiddleNumber(0,0);
					else operandTwo = result[i-1][j];
					int resultCount = parseFromString+operandOne.getResultCount()+operandTwo.getResultCount()-together.getResultCount();
					if (parseFromString==1) {
						ones = 1 + operandOne.getOnes() + operandTwo.getOnes() - together.getOnes();
					}else{
						ones = operandOne.getOnes() + operandTwo.getOnes() - together.getOnes();
					}
					result[i][j] = new MiddleNumber(resultCount,ones);
				}
			}
		}
		return result;
	}

	private int reverseTwo(int number){
		if (number==2) return -2;
		else return number;
	}

	public int getRows() {
		return rows;
	}

	public int getCols() {
		return cols;
	}
}
