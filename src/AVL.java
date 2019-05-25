
import java.util.Scanner;


import java.math.*;

//�����Ҫݔ��ֻ��һ��ֵԓ���N�k
//ֻ�ܰ�ǰ������ݔ����
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
	Node root; //���������c
	
	//���c�߶�
	int height(Node N) 
	{ 
		if (N == null) 
			return 0; 
		return N.height; 
	} 

	// �ҳ��ɂ�ֵ���^���
	int max(int a, int b) 
	{ 
		return (a > b) ? a : b; 
	} 

	// ���c������
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

	//���c������
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

	// ȡ��ƽ������ N
	int getBalance(Node N) 
	{ 
		if (N == null) 
			return 0; 
		return height(N.left) - height(N.right); 
	} 

	//���빝�c
	Node insert(Node node, int key) 
	{ 
	
		if (node == null) //����F�ڹ��c���գ�ֱ�Ӽ���
			return (new Node(key)); 

		if (key < node.key) //������c��������С춬F�ڵ��Iֵ���fޒ����insert
			node.left = insert(node.left, key); 
		
		else if (key > node.key) //������c�������Ҵ�춬F�ڵ��Iֵ���fޒ����insert
			node.right = insert(node.right, key); 
		else //����ѽ�����ֱ��ֱ�ӷ��جF�ڵ�node
			return node; 

		//���¬F�ڹ��c�߶�
		node.height = 1 + max(height(node.left), 
							height(node.right)); 

		//�����c�����Д�ƽ��S��
		int balance = getBalance(node); 

		//�����ƽ����ķN��r
		
		//��1��LL ��Ҫ��һ������
		if (balance > 1 && key < node.left.key) 
			return rightRotate(node); 

		// ��2��RR ��Ҫ��һ������
		if (balance < -1 && key > node.right.key) 
			return leftRotate(node); 

		// ��3��LR ��Ҫ��һ������һ������ 
		if (balance > 1 && key > node.left.key) 
		{ 
			node.left = leftRotate(node.left); 
			return rightRotate(node); 
		} 

		// ��4��LR ��Ҫ��һ������һ������ 
		if (balance < -1 && key < node.right.key) 
		{ 
			node.right = rightRotate(node.right); 
			return leftRotate(node); 
		} 

		//������c������ƽ��ֱ�ӷ��ع��c
		return node; 
	} 

	//�ҵ���С�Ĺ��c(֮��h�����õ�)
	Node minValueNode(Node node) 
	{ 
		Node current = node; 

		//�����Ә��е��~�Ӿ�����С���c
		while (current.left != null) 
		current = current.left; 

		return current; 
	} 
	
	//���c�Ąh��
	Node deleteNode(Node root, int key) 
	{ 
		// �䠑�Մt���ع��c
		if (root == null) 
			return root; 

		//�����Ҫ�h���Ĺ��c�Ĺ��c�ȬF�����ڵĹ��c�IֵС���t�����c�G�����Ә��fޒ
		if (key < root.key) 
			root.left = deleteNode(root.left, key); 
		
		//�����Ҫ�h���Ĺ��c�Ĺ��c�ȬF�����ڵĹ��c�IֵС���t�����c�G�����Ә��fޒ
		else if (key > root.key) 
			root.right = deleteNode(root.right, key); 

		//�����Ҫ�h���Ĺ��c�Ĺ��c��춬F�����ڵĹ��c
		else
		{ 

			// ��1��Ҫ�h���Ĺ��c�]���Ә��ֻ��һ���Ә����r
			if ((root.left == null) || (root.right == null)) 
			{ 
				Node temp = null; 
				if (temp == root.left) 
					temp = root.right; 
				else
					temp = root.left; 

				//�]���Ә�
				if (temp == null) 
				{ 
					temp = root; 
					root = null; 
				} 
				else // ��һ���Ә�
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

		

		// ���¬F�ڵĸ߶�
		root.height = max(height(root.left), height(root.right)) + 1; 

		//�ٙz��ƽ������
		int balance = getBalance(root); 

		//��ƽ�⣬�ٌ����{ƽ�� 
		// ��1�� LL 
		if (balance > 1 && getBalance(root.left) >= 0) 
			return rightRotate(root); 

		//��2��LR
		if (balance > 1 && getBalance(root.left) < 0) 
		{ 
			root.left = leftRotate(root.left); 
			return rightRotate(root); 
		} 

		//��3��RR
		if (balance < -1 && getBalance(root.right) <= 0) 
			return leftRotate(root); 

		// ��4��RL
		if (balance < -1 && getBalance(root.right) > 0) 
		{ 
			root.right = rightRotate(root.right); 
			return leftRotate(root); 
		} 

		return root; 
	} 

	//ӡ���r��Ҫ��ǰ��ӡ��
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

	 
		//����ʽ
		public static void main(String[] args) 
		{ 
			AVLTree tree = new AVLTree(); 

			//�����Ĺ��c
			
		
			tree.root = tree.insert(tree.root, 9); 
			tree.root = tree.insert(tree.root, 5); 
			tree.root = tree.insert(tree.root, 10); 
			tree.root = tree.insert(tree.root, 0); 
			tree.root = tree.insert(tree.root, 6); 
			tree.root = tree.insert(tree.root, 11); 
			tree.root = tree.insert(tree.root, -1); 
			tree.root = tree.insert(tree.root, 1); 
			tree.root = tree.insert(tree.root, 2); 
			
			/* �F�ڵĹ��c
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
			tree.preOrder(tree.root); //ǰ���@ʾ����Y��

			//�h�����c
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
			tree.preOrder(tree.root); //ǰ���@ʾ�h���Y��
		
	} 


}
