package alg;

import java.io.IOException;

/**
 * @author Marek Klement
 */
public class Main {

	public static int checkForMax(int currentMax, int candidate){
		if (currentMax>=candidate)
			return currentMax;
		else
			return  candidate;
	}

	public static void printMatrix(MiddleNumber[][] arr, int rows, int cols){
		for (int i = 0;i<rows;++i){
			for (int j = 0;j<cols;++j){
				if (j==cols-1){
					System.out.print(arr[i][j]+"\n");
				} else {
					System.out.print(arr[i][j]+" ");
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		MatrixReader mr = new MatrixReader();
		MiddleNumber myMatrix[][] = mr.read();
		int number;
		int currentMax = 0;
		int ourNumber,finalNumb, a,b,c,d,e,f,g,h;
		for (int i = 1;i<mr.getRows();++i){
			for (int j = 1;j<mr.getCols();++j){
				if(i==j){
					number = i;
				}else if(i>j){
					number=j;
				}else{
					number=i;
				}
				for(int k = 1;k<=number;++k){
					a = myMatrix[i][j].getResultCount();
					d = myMatrix[i-1][j-1].getResultCount();
					e = myMatrix[i-1][j-k].getResultCount();
					f = myMatrix[i-k][j-1].getResultCount();
					g = myMatrix[i-k][j-k].getResultCount();
					if((j-k-1)<0 && (i-k-1)<0) {
						b = 0;
						c = 0;
						h = 0;
					}else if ((j-k-1)<0){
						b = 0;
						c = myMatrix[i - k - 1][j].getResultCount();
						h = 0;
					} else if ((i-k-1)<0){
						b = myMatrix[i][j - k - 1].getResultCount();
						c = 0;
						h = 0;
					} else {
						b = myMatrix[i][j - k - 1].getResultCount();
						c = myMatrix[i - k - 1][j].getResultCount();
						h = myMatrix[i - k - 1][j - k - 1].getResultCount();
					}
					ourNumber = a +e +f +h -b -c -d -g;
					if(ourNumber>=0){
						a = myMatrix[i][j].getOnes();
						d = myMatrix[i-1][j-1].getOnes();
						e = myMatrix[i-1][j-k].getOnes();
						f = myMatrix[i-k][j-1].getOnes();
						g = myMatrix[i-k][j-k].getOnes();
						if((j-k-1)<0 && (i-k-1)<0) {
							b = 0;
							c = 0;
							h = 0;
						}else if ((j-k-1)<0){
							b = 0;
							c = myMatrix[i - k - 1][j].getOnes();
							h = 0;
						} else if ((i-k-1)<0){
							b = myMatrix[i][j - k - 1].getOnes();
							c = 0;
							h = 0;
						} else {
							b = myMatrix[i][j - k - 1].getOnes();
							c = myMatrix[i - k - 1][j].getOnes();
							h = myMatrix[i - k - 1][j - k - 1].getOnes();
						}
						finalNumb = a +e +f +h -b -c -d -g;
						currentMax = checkForMax(currentMax, finalNumb);
					}
				}
			}
		}
		System.out.println(currentMax);
	}
}
