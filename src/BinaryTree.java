import java.io.*;
import java.util.Scanner;

public class BinaryTree {
	
	TreeNode root;
	public static String[] charCodeInTree = new String[256];
	public TreeNode getRoot() {
		return root;
	}
	public void setRoot(TreeNode root) {
		this.root = root;
	}
	public BinaryTree() {
		root = null;
	}
	public BinaryTree(TreeNode _root) {
		root = _root;
	}
	public void computeCharCount(File inFile, int charCountAry[]) {
		Scanner sc = null;
		 int index;
		 try {sc = new Scanner(inFile);
		} catch (FileNotFoundException e) {
		e.printStackTrace();
		}
		while(sc.hasNext()) {
		String temp = sc.next();
		for (int i = 0; i < temp.length(); i++) {
		index = (int)temp.charAt(i);
		charCountAry[index]++;
		}
		}
	}
	public void printCountAry(int charCountAry[], File outFile) throws IOException { // to debug file
		
		FileWriter output = new FileWriter(outFile,true);
		BufferedWriter outStream= new BufferedWriter(output);
		output.write("*****************printCountAry: \n");
		for (int i = 0; i < charCountAry.length; i++) {
		if(charCountAry[i] == 0) {
		continue;
		}
		try {
		outStream.write((char)i + " " + charCountAry[i]);
		outStream.newLine();
		} catch (IOException e) {
		e.printStackTrace();
		}}
		System.out.println("********************finish prinCountAry");
		outStream.close();
	}
	public void constructCharCode() {
		constructCharCode(this.root, "");		
		System.out.println("********************finish constructCharCode");
	}
	private void constructCharCode(TreeNode currentNode, String code) {
		
		if(isLeaf(currentNode)) {
			currentNode.code = code;
			int index = (int)currentNode.chStr.toCharArray()[0];
			charCodeInTree[index] = code;
		}
		else {
			constructCharCode(currentNode.left, code + "0");
			constructCharCode(currentNode.right, code + "1");
		}
	}
	public void constructHuffmanBinTree(LinkedList huffmanLL, File outFile) { // to debug file
		try {
			FileWriter output = new FileWriter(outFile,true);
			output.write("****************constructHuffmanBinTree:\n");
			output.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TreeNode firstNode;
		TreeNode secondNode;
		TreeNode newNode;
		while(huffmanLL.listHead.next.next != null) {
			firstNode = huffmanLL.listHead.next;
			secondNode = huffmanLL.listHead.next.next;
			newNode = new TreeNode(firstNode.chStr + secondNode.chStr,firstNode.frequency + secondNode.frequency, "", firstNode, secondNode );
			huffmanLL.insertNewNode(huffmanLL.listHead, newNode);
			huffmanLL.printList(huffmanLL.listHead, outFile);
		    huffmanLL.listHead.next = huffmanLL.listHead.next.next.next;
		}
        this.root = huffmanLL.listHead.next;
        System.out.println("*****************finish constructHuffmanBinTree");
	}	
	public void preOrderTraversal(File outFile) {
		try {
			FileWriter output = new FileWriter(outFile,true);
			output.write("***********************in preOrderTraversal\n");
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		preOrderTraversal(this.root, outFile);
		
	}
	private void preOrderTraversal(TreeNode currentNode, File outFile) {
		if(isLeaf(currentNode)) {
			currentNode.printNode(outFile);
		}
		else {
			currentNode.printNode(outFile);
			preOrderTraversal(currentNode.left, outFile);
			preOrderTraversal(currentNode.right, outFile);
		}
	}
	public void inOrderTraversal(File outFile) {
		try {
			FileWriter output = new FileWriter(outFile,true);
			output.write("***********************in inOrderTraversal\n");
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		inOrderTraversal(this.root, outFile);	
	}
    private void inOrderTraversal(TreeNode currentNode,File outFile) { // to debug file
		if(isLeaf(currentNode)) {
			currentNode.printNode(outFile);
		}
		else {
			inOrderTraversal(currentNode.left, outFile);
			currentNode.printNode(outFile);
			inOrderTraversal(currentNode.right, outFile);
		}
	}
	public void postOrderTraversal(File outFile) {
		try {
			FileWriter output = new FileWriter(outFile,true);
			output.write("***********************in postOrderTraversal\n");
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		postOrderTraversal(this.root, outFile);	
	}
    private void postOrderTraversal(TreeNode currentNode, File outFile) {
    	if(isLeaf(currentNode)) {
    		currentNode.printNode(outFile);
		}
    	else {
    		postOrderTraversal(currentNode.left, outFile);
    		postOrderTraversal(currentNode.right, outFile);
    		currentNode.printNode(outFile);
		}
    	
    }
    public static boolean isLeaf(TreeNode currentNode){
    	if(currentNode.left == null && currentNode.right == null) {
    		return true;
    	}
    	return false;
    }
     
}
