
import java.util.Scanner;


import java.math.*;

//如果想要輸入只有一個值該怎麼辦
//只能按前序後序輸出嗎
class Node

{ 
	int key, height; 
	Node left, right; 

	Node(int d) 
	{ 
		key = d; 
		height = 1; 
	} 
}
class AVLTree 
{ 
	Node root; //建立根節點
	
	//節點高度
	int height(Node N) 
	{ 
		if (N == null) 
			return 0; 
		return N.height; 
	} 

	// 找出兩個值中較大的
	int max(int a, int b) 
	{ 
		return (a > b) ? a : b; 
	} 

	// 節點的右旋
	Node rightRotate(Node y) 
	{ 
		Node x = y.left; 
		Node T2 = x.right; 
 
		x.right = y; 
		y.left = T2; 

		y.height = max(height(y.left), height(y.right)) + 1; 
		x.height = max(height(x.left), height(x.right)) + 1; 

		return x; 
	} 

	//節點的左旋
	Node leftRotate(Node x) 
	{ 
		Node y = x.right; 
		Node T2 = y.left; 

		y.left = x; 
		x.right = T2; 

		x.height = max(height(x.left), height(x.right)) + 1; 
		y.height = max(height(y.left), height(y.right)) + 1; 

		return y; 
	} 

	// 取得平衡因子 N
	int getBalance(Node N) 
	{ 
		if (N == null) 
			return 0; 
		return height(N.left) - height(N.right); 
	} 

	//加入節點
	Node insert(Node node, int key) 
	{ 
	
		if (node == null) //如果現在節點爲空，直接加入
			return (new Node(key)); 

		if (key < node.key) //如果節點不爲空且小於現在的鍵值，遞迴放入insert
			node.left = insert(node.left, key); 
		
		else if (key > node.key) //如果節點不爲空且大於現在的鍵值，遞迴放入insert
			node.right = insert(node.right, key); 
		else //如果已經含有直接直接返回現在的node
			return node; 

		//更新現在節點高度
		node.height = 1 + max(height(node.left), 
							height(node.right)); 

		//將節點放入判斷平衡係數
		int balance = getBalance(node); 

		//如果不平衡的四種情況
		
		//（1）LL 需要做一次左旋
		if (balance > 1 && key < node.left.key) 
			return rightRotate(node); 

		// （2）RR 需要做一次右旋
		if (balance < -1 && key > node.right.key) 
			return leftRotate(node); 

		// （3）LR 需要做一次左旋一次右旋 
		if (balance > 1 && key > node.left.key) 
		{ 
			node.left = leftRotate(node.left); 
			return rightRotate(node); 
		} 

		// （4）LR 需要做一次右旋一次左旋 
		if (balance < -1 && key < node.right.key) 
		{ 
			node.right = rightRotate(node.right); 
			return leftRotate(node); 
		} 

		//如果節點加入後平衡直接返回節點
		return node; 
	} 

	//找到最小的節點(之後刪除會用到)
	Node minValueNode(Node node) 
	{ 
		Node current = node; 

		//找左子樹中的葉子就是最小節點
		while (current.left != null) 
		current = current.left; 

		return current; 
	} 
	
	//節點的刪除
	Node deleteNode(Node root, int key) 
	{ 
		// 樹爲空則返回節點
		if (root == null) 
			return root; 

		//如果需要刪除的節點的節點比現在所在的節點鍵值小，則將節點丟入左子樹遞迴
		if (key < root.key) 
			root.left = deleteNode(root.left, key); 
		
		//如果需要刪除的節點的節點比現在所在的節點鍵值小，則將節點丟入右子樹遞迴
		else if (key > root.key) 
			root.right = deleteNode(root.right, key); 

		//如果需要刪除的節點的節點等於現在所在的節點
		else
		{ 

			// （1）要刪除的節點沒有子樹或只有一個子樹的情況
			if ((root.left == null) || (root.right == null)) 
			{ 
				Node temp = null; 
				if (temp == root.left) 
					temp = root.right; 
				else
					temp = root.left; 

				//沒有子樹
				if (temp == null) 
				{ 
					temp = root; 
					root = null; 
				} 
				else // 有一個子樹
					root = temp; 
			} 
			
			
			else
			{ 

				// node with two children: Get the inorder 
				// successor (smallest in the right subtree) 
				Node temp = minValueNode(root.right); 

				// Copy the inorder successor's data to this node 
				root.key = temp.key; 

				// Delete the inorder successor 
				root.right = deleteNode(root.right, temp.key); 
			} 
		} 

		

		// 更新現在的高度
		root.height = max(height(root.left), height(root.right)) + 1; 

		//再檢查平衡因子
		int balance = getBalance(root); 

		//不平衡，再將樹調平衡 
		// （1） LL 
		if (balance > 1 && getBalance(root.left) >= 0) 
			return rightRotate(root); 

		//（2）LR
		if (balance > 1 && getBalance(root.left) < 0) 
		{ 
			root.left = leftRotate(root.left); 
			return rightRotate(root); 
		} 

		//（3）RR
		if (balance < -1 && getBalance(root.right) <= 0) 
			return leftRotate(root); 

		// （4）RL
		if (balance < -1 && getBalance(root.right) > 0) 
		{ 
			root.right = rightRotate(root.right); 
			return leftRotate(root); 
		} 

		return root; 
	} 

	//印出時需要用前序印出
	void preOrder(Node node) 
	{ 
		if (node != null) 
		{ 
			System.out.print(node.key + " "); 
			preOrder(node.left); 
			preOrder(node.right); 
		} 
	} 
}


public class AVL {

	 
		//主程式
		public static void main(String[] args) 
		{ 
			AVLTree tree = new AVLTree(); 

			//放入樹的節點
			
		
			tree.root = tree.insert(tree.root, 9); 
			tree.root = tree.insert(tree.root, 5); 
			tree.root = tree.insert(tree.root, 10); 
			tree.root = tree.insert(tree.root, 0); 
			tree.root = tree.insert(tree.root, 6); 
			tree.root = tree.insert(tree.root, 11); 
			tree.root = tree.insert(tree.root, -1); 
			tree.root = tree.insert(tree.root, 1); 
			tree.root = tree.insert(tree.root, 2); 
			
			/* 現在的節點
			9 
			/ \ 
			1 10 
			/ \ \ 
			0 5 11 
			/ / \ 
			-1 2 6 
			*/
			
			System.out.println("Preorder traversal of "+ 
								"constructed tree is : "); 
			tree.preOrder(tree.root); //前序顯示插入結果

			//刪除節點
			tree.root = tree.deleteNode(tree.root, 10); 

			/* The AVL Tree after deletion of 10 
			1 
			/ \ 
			0 9 
			/	 / \ 
			-1 5 11 
			/ \ 
			2 6 
			*/
			System.out.println(""); 
			System.out.println("Preorder traversal after "+ 
							"deletion of 10 :"); 
			tree.preOrder(tree.root); //前序顯示刪除結果
		
	} 


}
