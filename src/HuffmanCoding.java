import java.io.*;
import java.util.*;  
public class HuffmanCoding {
	
	static String charCode[] = new String[256];
	static BinaryTree huffmanTree = new BinaryTree();
	
	public static void main(String args[]) throws IOException {
        System.out.println("program start\n");
        
		
		LinkedList huffmanLL = new LinkedList();
		int charCountAry[] = new int[256];
		
		String nameInFile = args[0];
		String nameDebugFile = nameInFile + "_DeBug.txt";
		
		File inFile = new File(nameInFile);
		File DebugFile = new File(nameDebugFile);
		
		computeCharCount(inFile, charCountAry);
		printCountAry(charCountAry, DebugFile);
		huffmanLL.constructHuffmanLList(charCountAry, DebugFile);
		huffmanTree.constructHuffmanBinTree(huffmanLL, DebugFile);
		huffmanTree.constructCharCode();
		for (int i = 0; i < BinaryTree.charCodeInTree.length; i++) {
			charCode[i] = BinaryTree.charCodeInTree[i];
	    }
		FileWriter output = new FileWriter(DebugFile,true);
		output.write("***********************after constructCharCode\n");
		output.close();
		huffmanLL.printList(huffmanLL.getListHead(), DebugFile);
		huffmanTree.preOrderTraversal(DebugFile);
		huffmanTree.inOrderTraversal(DebugFile);
		huffmanTree.postOrderTraversal(DebugFile);
		System.out.println("***********************finish three traversal");
		userInterface(DebugFile);
		
		System.out.println("program ends");
	}
	public static void computeCharCount(File inFile, int charCountAry[]) {
        Scanner sc = null;
        int index;
        try {
			sc = new Scanner(inFile);
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
	public static void printCountAry(int charCountAry[], File outFile) throws IOException { // to debug file
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
			}
			
		}
		System.out.println("********************finish prinCountAry");
		outStream.close();
	}
	public static void userInterface(File debugFile) {
		String nameOrg = "";
		String nameCompress = "";
		String nameDeCompress = "";
		char yesNo;
		System.out.println("Do you want to compress the text?  Y/N?");
		Scanner sc = new Scanner(System.in);
		yesNo = Character.toLowerCase(sc.next().charAt(0));
		while(yesNo != 'n') {
			if(Character.toLowerCase(yesNo) == 'n') {
				return;
			}
			else {
				System.out.println("Please enter the name of text file withou .txt extension:");
				nameOrg = sc.next();
				nameCompress = nameOrg + "_Compressed.txt";
				nameDeCompress = nameOrg + "_DeCompress.txt";
				nameOrg = nameOrg + ".txt";
				File orgFile = new File(nameOrg);
				File compFile = new File(nameCompress);
				File deCompFile = new File(nameDeCompress);
				Encode(orgFile , compFile, debugFile);
				compFile = new File(nameCompress);
				Decode(compFile,deCompFile);
			}
			System.out.println("Do you want to keep going? input n to exit:");
			yesNo = Character.toLowerCase(sc.next().charAt(0));
		}

	}
	public static void Encode(File inFile, File outFile, File debugFile) {
		try {
			Scanner sc = sc = new Scanner(inFile);
			FileWriter output = new FileWriter(outFile,true);
			FileWriter debugOutput = new FileWriter(debugFile,true);
			char charIn;
			int index;
			String code;
			while(sc.hasNext()) {
				String temp = sc.next();
				for (int i = 0; i < temp.length(); i++) {
					charIn = temp.charAt(i);
					index = (int)charIn;
					code = charCode[index];
					debugOutput.write("index: " + index + ", code: " + code + "\n");
					output.write(code);
				}
			}
			debugOutput.close();
			output.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		
	}
	public static void Decode(File inFile, File outFile) {
		try {
			FileInputStream input= new FileInputStream(inFile);
			FileWriter output = new FileWriter(outFile,true);
			TreeNode spot = huffmanTree.getRoot();
			int oneBit = 0;
			while((oneBit = input.read()) != -1) {
				    if(huffmanTree.isLeaf(spot)) {
					output.write(spot.getChStr());
					spot = huffmanTree.getRoot();
				    }
					if((char)oneBit == '0') {
						spot = spot.getLeft();
					}
					else if((char)oneBit == '1') {
						spot = spot.getRight();
					}
					else {
						output.write("Error! The compress file contains invalid character!");
						System.exit(-1);
					}
				}
			if(!huffmanTree.isLeaf(spot)) {
				System.out.println("Error: The compress file is corrupted!");
			}
			input.close();
			output.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

}

