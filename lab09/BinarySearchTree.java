package lab09;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BinarySearchTree<T> {
	T item;
	private BinarySearchTree<T> leftTree;
	private BinarySearchTree<T> rightTree;
	
	private Comparator<T> compare;
	
	public BinarySearchTree(Comparator<T> compare) {
		this.compare = compare;
		
	}
	
	public void insert(T i) {
		if(this.item == null) {
			this.item = i;
		}
		else if(compare.compare(this.item,i) < 0) {
			if(rightTree == null) {
				rightTree = new BinarySearchTree<T>(compare);
				rightTree.item = i;
			}
			else {
				rightTree.insert(i);
			}
		}
		else if(compare.compare(this.item,i) > 0) {
			if(leftTree == null) {
				leftTree = new BinarySearchTree<T>(compare);
				leftTree.item = i;
			}
			else {
				leftTree.insert(i);
			}
		}
	}
	
	public T find(T i) {
		/*
		String a = "";
		if(leftTree!=null && leftTree.item!=null) {
			a+=leftTree.item+" ";
		}
		if(rightTree!=null && rightTree.item!=null) {
			a+=rightTree.item+" ";
		}
		System.out.println(a);
		*/
		
		if(compare.compare(this.item,i) == 0) {
			return this.item;
		}
		else if(compare.compare(this.item,i)<0) {
			if(rightTree == null) {
				return null;
			} else {
				return rightTree.find(i);
			}
		}
		else {
			if(leftTree == null) {
				return null;
			} else {
				return leftTree.find(i);
			}
		}
	}
	
	public ArrayList<T> getElems() {
		ArrayList<T> a = new ArrayList<>();
		a.add(this.item);
		if(rightTree != null) {
			a.addAll(rightTree.getElems());
		}
		if(leftTree != null) {
			a.addAll(leftTree.getElems());
		}
		return a;
	}
}
