package alg;

import java.io.IOException;
import java.util.HashMap;

/**
 * @author Marek Klement
 */
public class Main {
	public static int[] deptCount;
	public static void main(String[] args) throws IOException {
		FileReader reader = new FileReader();
		BSTree tree = reader.read();
//		tree.printTree();
//		System.out.println(getSize(tree.root));
//		System.out.println();
//		System.out.println(maxDepth(tree.root)-1);
//		System.out.println();
		tree.intDelete();
//		tree.printRoots();
		int numberOfNodes = tree.size - tree.deletionCount;
		int lastPointer = 0, toSize = 0;
		tree.sortSet();
		HashMap<Integer,Node> hashSet = new HashMap<>();
		int[] getNewArr = new int[numberOfNodes];

		for(Node node: tree.setOfSubtrees){
			int size = getSize(node);
			toSize +=size;
			int numb = node.value;
			for(int i=lastPointer;i<toSize;++i){
				getNewArr[i]=numb;
			}
			hashSet.put(numb,node);
			lastPointer +=size;
		}


		int middle = (numberOfNodes+1)/2 -1;
//		if(numberOfNodes%2==0){
//			middle = (numberOfNodes/2)-1;
//		} else {
//			middle = ((numberOfNodes+1)/2)-1;
//		}


		deptCount = new int[tree.size-tree.deletionCount];
//		System.out.println();
//		for(int number: getNewArr){
//			System.out.print(number+" ");
//		}
//		System.out.println();
//		System.out.println(hashSet.get(getNewArr[middle]));
		int midNum = getNewArr[middle];
		tree.root = hashSet.get(midNum);
		int middleMin = middle-1;
		int middlePlu = middle+1;
		int ln = getNewArr.length;
		int nextMin = middle;
		int nextPlu = middle;
		if(tree.root!=null) {
			for (int i = middleMin; i >= 0; --i) {
				if (getNewArr[i] == midNum) {
					getNewArr[i] = -1;
					tree.counter++;
					nextMin = i;
				}
				else break;
			}
			for (int i = middle; i < ln; ++i) {
				if (getNewArr[i] == midNum) {
					getNewArr[i] = -1;
					tree.counter++;
					nextPlu = i;
				}
				else break;
			}
			tree.addComponent(getNewArr, hashSet, 0, nextMin-1, tree.root);
			tree.addComponent(getNewArr, hashSet, nextPlu+1, ln-1, tree.root);
//			System.out.println();
//			tree.printTree();
		}
		int dep = maxDepth(tree.root)-1;
		System.out.println(dep+" "+deptCount[dep-1]);
//		System.out.println();
//		System.out.println(tree.counter+" || "+(tree.size-tree.deletionCount));
		System.out.println(tree.root);
	}

	public static int getSize(Node root){
		if(root==null){
			return 0;
		}
		return 1 + getSize(root.left) + getSize(root.right);
	}
	public static int maxDepth(Node node){
		int depth = 0;
		return maxDepth(node,depth);
	}

	public static int maxDepth(Node node, int depth) {
		if (node == null) {
			return (0);
		} else {
			deptCount[depth]++;
			depth++;
			// compute the depth of each subtree
			int leftDepth = maxDepth(node.left,depth);
			int rightDepth = maxDepth(node.right,depth);
			// use the larger one
			if (leftDepth > rightDepth )
				return (leftDepth + 1);
			else
				return (rightDepth + 1);
		}
	}

}
