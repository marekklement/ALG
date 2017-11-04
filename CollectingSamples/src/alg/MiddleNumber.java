package alg;

/**
 * @author Marek Klement
 */
public class MiddleNumber {

	private int resultCount;
	private int ones;

	public MiddleNumber(int resultCount, int ones){
		this.resultCount=resultCount;
		this.ones=ones;

	}

	public int getResultCount() {
		return resultCount;
	}

	public int getOnes() {
		return ones;
	}

	@Override
	public String toString() {
		String output = resultCount+"/"+ones;
		return output;
	}
}
