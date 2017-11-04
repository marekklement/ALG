package alg;

/**
 * @author Marek Klement
 */
public class Tuple {
	private int from;
	private int to;

	public Tuple(int from, int to){
		this.from = from;
		this.to = to;

	}

	public int getFrom() {
		return from;
	}

	public int getTo() {
		return to;
	}

	@Override
	public String toString() {
		String output = from + " " + to + "\n";
		return output;
	}
}
