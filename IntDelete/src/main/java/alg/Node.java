package alg;

/**
 * @author Marek Klement
 */
public class Node implements Comparable<Node>{
	int value;
	Node parent;
	Node left;
	Node right;
	boolean inInterval;
	boolean moreThan;
	boolean lessThan;

	public Node(int value, Node parent, boolean inInterval, boolean lessThan, boolean moreThan){
		this.value = value;
		this.left = null;
		this.right = null;
		this.parent = parent;
		this.inInterval = inInterval;
		this.lessThan = lessThan;
		this.moreThan = moreThan;

	}

	public StringBuilder toString(StringBuilder prefix, boolean isTail, StringBuilder sb) {
		if(right!=null) {
			right.toString(new StringBuilder().append(prefix).append(isTail ? "│   " : "    "), false, sb);
		}
		sb.append(prefix).append(isTail ? "└── " : "┌── ").append(value+"\n");
		if(left!=null) {
			left.toString(new StringBuilder().append(prefix).append(isTail ? "    " : "│   "), true, sb);
		}
		return sb;
	}

	@Override
	public String toString() {
		return this.toString(new StringBuilder(), true, new StringBuilder()).toString();
	}

	@Override
	public int compareTo(Node o) {
		if(this.value==o.value){
			return 0;
		} else {
			return 1;
		}
	}

	public void addLeft(Node toAdd){
		Node current = this;
		if(current == null) System.out.println("Something went wrong");
		Node parent = null;
		while(current!=null){
			parent = current;
			current = current.left;
		}
		parent.left = toAdd;
	}

	public void addRight(Node toAdd){
		Node current = this;
		if(current == null) System.out.println("Something went wrong");
		Node parent = null;
		while(current!=null){
			parent = current;
			current = current.right;
		}
		parent.right = toAdd;
	}
}
