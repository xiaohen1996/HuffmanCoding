import java.io.*;

public class LinkedList {
	TreeNode listHead;

	public TreeNode getListHead() {
		return listHead;
	}
	public void setListHead(TreeNode listHead) {
		this.listHead = listHead;
	}
	public LinkedList(){
		TreeNode dummy = new TreeNode("dummy", 0, "", null, null);
		listHead = dummy;
	}
	public void insertNewNode(TreeNode listHead,TreeNode newNode){
		TreeNode headPointer = listHead;
        while (headPointer.next != null && headPointer.next.frequency < newNode.frequency){
        	headPointer = headPointer.next;
        }
        newNode.next = headPointer.next;
        headPointer.next = newNode;
	}
	public void printList(TreeNode listHead, File outFile) { // to debugfile
		TreeNode headPointer = listHead;
		while(headPointer.next != null) {
			headPointer.printNode(outFile);
			headPointer = headPointer.next;
		}
		
	}
	public void constructHuffmanLList(int charCountAry[], File outFile) { //to debug file
		try {
			FileWriter output = new FileWriter(outFile,true);
			output.write("****************constructHuffmanLList:\n");
			output.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		listHead = new TreeNode("dummy", 0, "", null, null);
		int index = 0;
		while(index < 256) {
			if(charCountAry[index] > 0) {
				String chr = Character.toString((char)index);
				int prob = charCountAry[index];
				TreeNode newNode = new TreeNode(chr, prob, "", null, null);
				insertNewNode(listHead,newNode);
				printList(listHead, outFile);
			}
			index ++;
		}
		System.out.println("***************finish constructHuffmanList");
	}

	
}
