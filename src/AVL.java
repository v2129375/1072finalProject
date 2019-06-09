import java.util.*;
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
	Node(){}
}

class AVLTree
{
	Node root=new Node(); //建立根節點
	Node add(int in){
		//System.out.println("root:"+root.key);
		return insert(root,in);
	}

	boolean search (int i){return searchnode(root,i);}

	boolean searchnode(Node node, int in){
		/*if(insert(root,in).key==in){
        if(insert(root,in).key==in){
            return true;
        }else{
            return false;*/
        if(node.key==in){
                return true;
		}else{
			if(in < node.key){
			    if(node.left==null){
			    	return false;
				}else{
					return searchnode(node.left,in);
				}
			}else{
				if(node.right==null){
					return  false;
				}else{
					return searchnode(node.right, in);
				}
			}
		}
    }

	AVLTree(int input[]){
		int size=input.length;
		root.key=input[0];
		for(int i=1;i<size;i++){
			root=add(input[i]);
		}
	}

	Node delete(int in){
		return deleteNode(root,in);
	}

	void show(Node root) {
		Queue<Node> q = new LinkedList<>();
		q.add(root);//將根節點放入q
		//System.out.print(" (root:"+root.key+") ");
		//System.out.print(" (root.right:"+root.right.key+")");
		int cnt = 1;//每一層個數
		//int big=0;
		boolean test=false;

		while (cnt > 0) {
			int tmp = 0;//記錄子樹數量

			for (int i = 0; i < cnt; i++) {
				Node cur = q.poll();
				//System.out.print("( a:"+cur.height+" )");
				Node zero = new Node();
				zero.key = 0;


				if(cur.left!=null && cur.right!=null && min(cur.left.height,cur.right.height)==1 && max(cur.left.height,cur.right.height)!=1){

					test=true;
				}

				if (cur.left != null) {
					if (cur.right != null) {
						q.add(cur.left);//加入左子節點
						tmp++;//計算子節點總數
					} else {
						q.add(cur.left);
						q.add(zero);
						tmp += 2;
					}
				}
				if (cur.right != null) {
					if (cur.left != null) {
						q.add(cur.right);
						tmp++;
					} else {
						q.add(zero);
						q.add(cur.right);
						tmp += 2;
					}

				}


				if(cur.right == null && cur.left == null && test){
					q.add(zero);
					q.add(zero);
					//cur.height=1;
					tmp+=2;
					test=false;
				}
				//System.out.print("(b:"+cur.height+") ");
				System.out.print(cur.key +"	");

			}
			cnt = tmp;
			System.out.println();
		 }


	}

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

	int min(int a, int b)
	{
		return (a < b) ? a : b;
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
		/* 1. Perform the normal BST insertion */
		if (node == null)
			return (new Node(key));

		if (key < node.key)
			node.left = insert(node.left, key);
		else if (key > node.key)
			node.right = insert(node.right, key);
		else // Duplicate keys not allowed
			return node;

		/* 2. Update height of this ancestor node */
		node.height = 1 + max(height(node.left),
				height(node.right));


		int balance = getBalance(node);

		// If this node becomes unbalanced, then there
		// are 4 cases Left Left Case
		if (balance > 1 && key < node.left.key)
			return rightRotate(node);

		// Right Right Case
		if (balance < -1 && key > node.right.key)
			return leftRotate(node);

		// Left Right Case
		if (balance > 1 && key > node.left.key) {
			node.left = leftRotate(node.left);
			return rightRotate(node);
		}

		// Right Left Case
		if (balance < -1 && key < node.right.key) {
			node.right = rightRotate(node.right);
			return leftRotate(node);
		}

		/* return the (unchanged) node pointer */

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
		if (root == null){
		    System.out.println("此節點不存在");
            return root;
        }


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
				if (temp == root.left)//如果左節點為NULL
					temp = root.right;
				else//如果左節點不爲NULL
					temp = root.left;

				if (temp == null)//如果沒有子樹
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

		if (root == null)
			return root;


		//try{
			root.height = max(height(root.left),height(root.right)) + 1;// 更新現在的高度
		//}catch (NullPointerException e){}

		if(root!=null){
			root.height = max(height(root.left),height(root.right)) + 1;// 更新現在的高度
		}else{
			return root;
		}



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
			root.right = rightRotate(root);
			return leftRotate(root);
		}

		return root;
	}



	//印出時需要用前序印出
	/*void preOrder(Node node)
	{
		if (node != null)
		{
			System.out.print(node.key + " ");
			preOrder(node.left);
			preOrder(node.right);
		}
		else{
			System.out.print("0");
		}
	}*/
}


//public class AVL {


	//主程式
	//public static void main(String[] args)
	//{
		//AVLTree tree = new AVLTree(int a[]);

		//放入樹的節點
		//tree.root = tree.add(9);
		//tree.root = tree.insert(tree.root, 9);
		//tree.root = tree.insert(tree.root, 5);
		//tree.root = tree.insert(tree.root, 10);
		//tree.root = tree.insert(tree.root, 0);
		//tree.root = tree.insert(tree.root, 6);
		//tree.root = tree.insert(tree.root, 11);
		//tree.root = tree.insert(tree.root, -1);
		//tree.root = tree.insert(tree.root, 1);
		//tree.root = tree.insert(tree.root, 2);

			/* 現在的節點
			9
			/ \
			1 10
			/ \ \
			0 5 11
			/ / \
			-1 2 6
			*/

		/* System.out.println("Preorder traversal of "+
				"constructed tree is : ");
		tree.preOrder(tree.root); //前序顯示插入結果

		//刪除節點
		tree.root = tree.deleteNode(tree.root, 10);*/

			/* The AVL Tree after deletion of 10
			1
			/ \
			0 9
			/	 / \
			-1 5 11
			/ \
			2 6
			*/
		/*System.out.println("");
		System.out.println("Preorder traversal after "+
				"deletion of 10 :");
		tree.preOrder(tree.root); //前序顯示刪除結果

	}


}*/

