import java.io.*;

import java.io.IOException;

public class TreeNode {
	String chStr;
	int frequency;
	String code;
	TreeNode left;
	TreeNode right;
	TreeNode next;
	public String getChStr() {
		return chStr;
	}

	public int getFrequency() {
		return frequency;
	}

	public String getCode() {
		return code;
	}

	public TreeNode getLeft() {
		return left;
	}

	public TreeNode getRight() {
		return right;
	}

	public TreeNode getNext() {
		return next;
	}

	public void setChStr(String chStr) {
		this.chStr = chStr;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setLeft(TreeNode left) {
		this.left = left;
	}

	public void setRight(TreeNode right) {
		this.right = right;
	}

	public void setNext(TreeNode next) {
		this.next = next;
	}

	public TreeNode(String _chStr, int _frequency, String _code, TreeNode _left, TreeNode _right) {
		chStr = _chStr;
		frequency = _frequency;
		code = _code;
		left = _left;
		right = _right;
		next = null;
	}

	public void printNode(File outFile) { // to debug file
		try {
		String s = "(";
		if(this.chStr != null) {
			s = s + this.chStr + ", " + this.frequency + ", ";
		}
		else {
			s = s + "null, " + this.frequency + ", ";
		}
		if(this.code != null) {
			s = s + this.code + ", ";
		}
		else {
			s = s + "null, ";
		}
		if(this.next != null) {
			s = s + this.next.chStr + ", ";
		}
		else {
			s = s + "null, ";
		}
		if(this.left != null) {
			s = s + this.left.chStr + ", ";
		}
		else {
			s = s + "null, ";
		}
		if(this.right != null) {
			s = s + this.right.chStr + ", ";
		}
		else {
			s = s + "null, ";
		}
		s += ")\n";
			FileWriter output = new FileWriter(outFile,true);
			output.write(s + "\n");
			output.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}

