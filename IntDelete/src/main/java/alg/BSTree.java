package alg;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * @author Marek Klement
 */
public class BSTree {

	Node root = null;
	int size;
	int from;
	int to;
	int deletionCount = 0;
	int layers = 0;

	public void add(int val){
		boolean in = false;
		boolean less = false;
		boolean more = false;
		if(val>=from && val<=to){
			in = true;
			++deletionCount;
		} else if(val<from){
			less = true;
		} else {
			more = true;
		}
		Node newNode = new Node(val, null, in, less, more);
		if(root==null){
			root = newNode;
			return;
		}
		Node current = root;
		Node parent = null;
		while(true){
			parent = current;
			if(val<current.value){
				current = current.left;
				if(current==null){
					newNode.parent = parent;
					parent.left = newNode;
					return;
				}
			}else{
				current = current.right;
				if(current==null){
					newNode.parent = parent;
					parent.right = newNode;
					newNode.parent = parent;
					return;
				}
			}
		}
	}

	public LinkedList<Node> setOfSubtrees = new LinkedList<>();

	public void intDelete(){
		if(!root.inInterval){
			setOfSubtrees.add(root);
		}
		delete(root);
	}

	private void delete(Node current){
		if(current!=null){
			Node parent = current.parent;
			if(current.lessThan){
				if(parent!=null) {
					if (parent.moreThan) {
						parent.left = null;
						current.parent = null;
						setOfSubtrees.add(current);
						addUperParent(parent);
					}
				}
				delete(current.right);
			}else if(current.moreThan){
				if(parent!=null) {
					if (parent.lessThan) {
						parent.right = null;
						current.parent = null;
						setOfSubtrees.add(current);
						addUperParent(parent);
					}
				}
				delete(current.left);
			// in interval
			}else {
				actuallyRemove(current);
			}
		}else {
			return;
		}
	}

	private void addUperParent(Node node){
		if(node!=null){
			while (node.parent!=null){
				node = node.parent;
			}
			if(!setOfSubtrees.contains(node))
				setOfSubtrees.add(node);
		}
	}

	private void actuallyRemove(Node node){
		// parent delete
		Node parent = node.parent;
		if(parent!=null){
			if(parent.left==node){
				parent.left = null;
			} else {
				parent.right=null;
			}
			node.parent = null;
		}
		// left delete
		Node left = node.left;
		if(left!=null) {
			if (left.lessThan) {
				left.parent = null;
				node.left = null;
				setOfSubtrees.add(left);
				Node nextR = left.right;
				while (nextR!=null){
					if(nextR.inInterval) break;
					nextR = nextR.right;
				}
				if(nextR!=null){
					actuallyRemove(nextR);
				}
			} else if (left.inInterval) {
				actuallyRemove(left);
			} else {
				System.out.println("Something went horribly wrong! LEFT");
			}
		}

		// right delete
		Node right = node.right;
		if(right!=null) {
			if (right.moreThan) {
				right.parent = null;
				node.right = null;
				setOfSubtrees.add(right);
				Node nextL = right.left;
				while (nextL!=null){
					if(nextL.inInterval) break;
					nextL = nextL.left;
				}
				if(nextL!=null){
					actuallyRemove(nextL);
				}
			} else if (right.inInterval) {
				actuallyRemove(right);
			} else {
				System.out.println("Something went horribly wrong! RIGHT");
			}
		}
	}

	public void makeTreeFromArr(int[] arr){
		for(int number: arr){
			add(number);
		}
	}

	int counter = 0;

	public void addComponent(int[] arr, HashMap<Integer,Node> hashMap, int from, int to, Node before){
		if(from>to) return;
		int numb = (to-from+1);
		int middle = ((numb+1)/2)+from-1;
//		if((numb)%2==0){
//			middle = (numb/2)+from-1;
//		} else {
//			middle = ((numb+1)/2)+from-1;
//		}

		int newRootInt = arr[middle];
		Node t = before;
		int nextMin = -1, nextPlu = -1;
		if(newRootInt!=-1) {
			int nextMid = middle - 1;
			nextMin = middle;
			for (int i = nextMid; i >= from; --i) {
				if (arr[i] == newRootInt) {
					arr[i] = -1;
					counter++;
					nextMin = i;
				}
				else break;
			}
			nextPlu = middle;
			for (int i = middle; i <= to; ++i) {
				if (arr[i] == newRootInt) {
					arr[i] = -1;
					counter++;
					nextPlu = i;
				}
				else break;
			}

			t= hashMap.get(newRootInt);
			if (before.value > t.value) {
				before.addLeft(t);
			} else {
				before.addRight(t);
			}
		}
		addComponent(arr, hashMap, from, nextMin-1,t);
		addComponent(arr, hashMap, nextPlu+1, to,t);
	}

	public void printTree(){
		System.out.println(root);
	}

	public void sortSet(){
		Collections.sort(setOfSubtrees, new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return (o1.value - o2.value);
			}
		});
	}

	public void printRoots(){
		sortSet();
		for (Node tree: setOfSubtrees){
			System.out.println(tree.value);
		}
	}
}
