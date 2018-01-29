package alg;

/**
 * TODO describtion
 *
 * @author Marek Klement
 */
public class Robot {
	int beginI;
	int beginJ;
	int endI;
	int endJ;
	int total;
	boolean line;

	public Robot(int beginI, int beginJ, int endI, int endJ, boolean line){
		this.beginI = beginI;
		this.beginJ = beginJ;
		this.endI = endI;
		this.endJ = endJ;
		this.line = line;
	}

	@Override
	public String toString() {
		return "Robot: TotalCount:"+total+" ("+beginI+","+beginJ+")>>>("+endI+","+endJ+")";
	}
}
