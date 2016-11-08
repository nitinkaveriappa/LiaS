import java.util.Arrays;
import java.util.Random;

/*
 *  Java Program to Implement AVL Tree
 */
 /* Class AVLNode */
 class AVLNode
 {    
     AVLNode left, right;
     int data;
     int index;
     int height;
 
     /* Constructor */
     public AVLNode()
     {
         left = null;
         right = null;
         data = 0;
         index = 0;
         height = 0;
     }
     /* Constructor */
     public AVLNode(int n,int m)
     {
         left = null;
         right = null;
         data = n;
         index = m;
         height = 1;
     }     
     public AVLNode(int n)
     {
    	 left = null;
         right = null;
         data = n;
         index = 0;
         height = 1;
     }
 }
 
 /* Class AVLTree */
 class AVLTree
 {
     private AVLNode root;  
     int COUNT=0;
 
     /* Constructor */
     public AVLTree()
     {
         root = null;
     }
     /* Function to check if tree is empty */
     public boolean isEmpty()
     {
         return root == null;
     }
     /* Make the tree logically empty */
     public void makeEmpty()
     {
         root = null;
     }
     
     /* Function to insert data */
     public void insert(int data,int ind)
     {
         root = insert(root,data,ind);
     }
     public void insert(int data)
     {
         root = insert(root,data);
     }
     
     int height(AVLNode N) {
         if (N == null) {
             return 0;
         }
         return N.height;
     }
  
     // A utility function to get maximum of two integers
     int max(int a, int b) {
         return (a > b) ? a : b;
     }
  
     // A utility function to right rotate subtree rooted with y
     // See the diagram given above.
     AVLNode rightRotate(AVLNode y) {
         AVLNode x = y.left;
         AVLNode T2 = x.right;
  
         // Perform rotation
         x.right = y;
         y.left = T2;
  
         // Update heights
         y.height = max(height(y.left), height(y.right)) + 1;
         x.height = max(height(x.left), height(x.right)) + 1;
  
         // Return new root
         return x;
     }
  
     // A utility function to left rotate subtree rooted with x
     // See the diagram given above.
     AVLNode leftRotate(AVLNode x) {
    	 AVLNode y = x.right;
    	 AVLNode T2 = y.left;
  
         // Perform rotation
         y.left = x;
         x.right = T2;
  
         //  Update heights
         x.height = max(height(x.left), height(x.right)) + 1;
         y.height = max(height(y.left), height(y.right)) + 1;
  
         // Return new root
         return y;
     }
  
     // Get Balance factor of node N
     int getBalance(AVLNode N) {
         if (N == null) {
             return 0;
         }
         return height(N.left) - height(N.right);
     }
  
     AVLNode insert(AVLNode node, int key, int ind) {
  
         /* 1.  Perform the normal BST rotation */
         if (node == null) {
             return (new AVLNode(key,ind));
         }
  
         if (key < node.data) {
             node.left = insert(node.left,key,ind);
         } else {
             node.right = insert(node.right,key,ind);
         }
  
         /* 2. Update height of this ancestor node */
         node.height = max(height(node.left), height(node.right)) + 1;
  
         /* 3. Get the balance factor of this ancestor node to check whether
          this node became unbalanced */
         int balance = getBalance(node);
  
         // If this node becomes unbalanced, then there are 4 cases
         // Left Left Case
         if (balance > 1 && key < node.left.data) {
             return rightRotate(node);
         }
  
         // Right Right Case
         if (balance < -1 && key > node.right.data) {
             return leftRotate(node);
         }
  
         // Left Right Case
         if (balance > 1 && key > node.left.data) {
             node.left = leftRotate(node.left);
             return rightRotate(node);
         }
  
         // Right Left Case
         if (balance < -1 && key < node.right.data) {
             node.right = rightRotate(node.right);
             return leftRotate(node);
         }
  
         /* return the (unchanged) node pointer */
         return node;
     }
     
     AVLNode insert(AVLNode node, int key) {
    	  
         /* 1.  Perform the normal BST rotation */
         if (node == null) {
             return (new AVLNode(key));
         }
  
         if (key < node.data) {
             node.left = insert(node.left,key);
         } else {
             node.right = insert(node.right,key);
         }
  
         /* 2. Update height of this ancestor node */
         node.height = max(height(node.left), height(node.right)) + 1;
  
         /* 3. Get the balance factor of this ancestor node to check whether
          this node became unbalanced */
         int balance = getBalance(node);
  
         // If this node becomes unbalanced, then there are 4 cases
         // Left Left Case
         if (balance > 1 && key < node.left.data) {
             return rightRotate(node);
         }
  
         // Right Right Case
         if (balance < -1 && key > node.right.data) {
             return leftRotate(node);
         }
  
         // Left Right Case
         if (balance > 1 && key > node.left.data) {
             node.left = leftRotate(node.left);
             return rightRotate(node);
         }
  
         // Right Left Case
         if (balance < -1 && key < node.right.data) {
             node.right = rightRotate(node.right);
             return leftRotate(node);
         }
  
         /* return the (unchanged) node pointer */
         return node;
     }
  
     /* Given a non-empty binary search tree, return the node with minimum
      key value found in that tree. Note that the entire tree does not
      need to be searched. */
     AVLNode minValueNode(AVLNode node) {
         AVLNode current = node;
  
         /* loop down to find the leftmost leaf */
         while (current.left != null) {
        	 inccount();
             current = current.left;
         }
  
         return current;
     }
     
     public int maxValueNodeIndex()
     {
    	 return maxValueNode(root).data;
     }
     AVLNode maxValueNode(AVLNode node) {
  
         /* loop down to find the leftmost leaf */
         while (node.right != null) {
        	 inccount();
             node = node.right;
         }
  
         return node;
     }
  
     public void delete(int data)
     {
         root = deleteNode(root,data);
     }
     
     public AVLNode deleteNode(AVLNode root, int key) {
  
         // STEP 1: PERFORM STANDARD BST DELETE
         if (root == null) {
             return root;
         }
  
         // If the key to be deleted is smaller than the root's key,
         // then it lies in left subtree      
         if (key < root.data) {
             root.left = deleteNode(root.left, key);
         } 
  
         // If the key to be deleted is greater than the root's key,
         // then it lies in right subtree
         else if (key > root.data) {
             root.right = deleteNode(root.right, key);
         } 
          
         // if key is same as root's key, then this is the node
         // to be deleted
         else {
  
             // node with only one child or no child
             if ((root.left == null) || (root.right == null)) {
                 AVLNode temp = null;
                 if (temp == root.left) {
                     temp = root.right;
                 } else {
                     temp = root.left;
                 }
  
                 // No child case
                 if (temp == null) {
                     temp = root;
                     root = null;
                 } else // One child case
                 {
                     root = temp; // Copy the contents of the non-empty child
                 }
             } else {
  
                 // node with two children: Get the inorder successor (smallest
                 // in the right subtree)
                 AVLNode temp = minValueNode(root.right);
  
                 // Copy the inorder successor's data to this node
                 root.data = temp.data;
                 root.index = temp.index;
  
                 // Delete the inorder successor
                 root.right = deleteNode(root.right, temp.data);
             }
         }
  
         // If the tree had only one node then return
         if (root == null) {
             return root;
         }
  
         // STEP 2: UPDATE HEIGHT OF THE CURRENT NODE
         root.height = max(height(root.left), height(root.right)) + 1;
  
         // STEP 3: GET THE BALANCE FACTOR OF THIS NODE (to check whether
         //  this node became unbalanced)
         int balance = getBalance(root);
  
         // If this node becomes unbalanced, then there are 4 cases
         // Left Left Case
         if (balance > 1 && getBalance(root.left) >= 0) {
             return rightRotate(root);
         }
  
         // Left Right Case
         if (balance > 1 && getBalance(root.left) < 0) {
             root.left = leftRotate(root.left);
             return rightRotate(root);
         }
  
         // Right Right Case
         if (balance < -1 && getBalance(root.right) <= 0) {
             return leftRotate(root);
         }
  
         // Right Left Case
         if (balance < -1 && getBalance(root.right) > 0) {
             root.right = rightRotate(root.right);
             return leftRotate(root);
         }
  
         return root;
     }    
     /* Functions to count number of nodes */
     public int countNodes()
     {
         return countNodes(root);
     }
     private int countNodes(AVLNode r)
     {
         if (r == null)
             return 0;
         else
         {
             int l = 1;
             l += countNodes(r.left);
             l += countNodes(r.right);
             return l;
         }
     }
     /* Functions to search for an element */
     public boolean search(int val)
     {
         return search(root, val);
     }
     private boolean search(AVLNode r, int val)
     {
         boolean found = false;
         while ((r != null) && !found)
         {
        	 inccount();
             int rval = r.data;
             if (val < rval)
                 r = r.left;
             else if (val > rval)
                 r = r.right;
             else
             {
                 found = true;
                 break;
             }
             found = search(r, val);
         }
         return found;
     }
     /* Function for inorder traversal */
     public void inorder()
     {
         inorder(root);
     }
     private void inorder(AVLNode r)
     {
         if (r != null)
         {
             inorder(r.left);
             System.out.print(r.data +" ");
             inorder(r.right);
         }
     }
     /* Function for preorder traversal */
     public void preorder()
     {
         preorder(root);
     }
     private void preorder(AVLNode r)
     {
         if (r != null)
         {
             System.out.print(r.data +" ");
             preorder(r.left);             
             preorder(r.right);
         }
     }
     /* Function for postorder traversal */
     public void postorder()
     {
         postorder(root);
     }
     private void postorder(AVLNode r)
     {
         if (r != null)
         {
             postorder(r.left);             
             postorder(r.right);
             System.out.print(r.data +" ");
         }
     }
     
    public int predecessor( int x ) 
    {
    	int p;
    	InorderSuccessorPredecessor i = new InorderSuccessorPredecessor();
		i.successorPredecessor(root, x);
		p=i.predecessor();
		return p;
	}
    public int successor( int x ) 
    {
    	int s;
    	InorderSuccessorPredecessor i = new InorderSuccessorPredecessor();
		i.successorPredecessor(root, x);
		s=i.successor();
		return s;
	}
    
    
    public int getindex( int val )
    {
    	return getindex(root, val).index;
    }
    public AVLNode getindex( AVLNode r,int val )
    {
    	 int found = 0;
         while ((r != null) && (found==1))
         {
        	 inccount();
             int rval = r.data;
             if (val < rval)
                 r = r.left;
             else if (val > rval)
                 r = r.right;
             else
             {
                 found = 1;
                 break;
             }
             getindex(r, val);
         }
         return r;
    }

    public int getArrayIndex(int[] arr,int value) {

        int k=0;
        for(int i=0;i<arr.length;i++){
        	inccount();
            if(arr[i]==value){
                k=i;
                break;
            }
        }
    return k;
    }
    
	public int count() {
		
		return COUNT;
	}
	public void inccount() {
		COUNT++;
	}
	
 }
 
 class InorderSuccessorPredecessor {
		static int successor, predecessor;

		public int successor()
		{
			return successor;
		}
		public int predecessor()
		{
			return predecessor;
		}
		
		public void successorPredecessor(AVLNode root, int val) {
			if (root != null) {
				if (root.data == val) {
					// go to the right most element in the left subtree, it will the
					// predecessor.
					if (root.left != null) {
						AVLNode t = root.left;
						while (t.right != null) {
							t = t.right;
						}
						predecessor = t.data;
					}
					if (root.right != null) {
						// go to the left most element in the right subtree, it will
						// the successor.
						AVLNode t = root.right;
						while (t.left != null) {
							t = t.left;
						}
						successor = t.data;
					}
				} else if (root.data > val) {
					// we make the root as successor because we might have a
					// situation when value matches with the root, it wont have
					// right subtree to find the successor, in that case we need
					// parent to be the successor
					successor = root.data;
					successorPredecessor(root.left, val);
				} else if (root.data < val) {
					// we make the root as predecessor because we might have a
					// situation when value matches with the root, it wont have
					// right subtree to find the predecessor, in that case we need
					// parent to be the predecessor.
					predecessor = root.data;
					successorPredecessor(root.right, val);
				}
			}
		}
 }
 
 
 /* Class AVL Tree Test */
 public class AVLTreeTest
 {
     public static void main(String[] args)
     {
 		System.out.println("\n <================== Start =================> \n");

    	 
    	 // remove comment and run for 1000 iterations
    	 Random rand = new Random();
    	 int inarr[] = new int[Integer.parseInt(args[0])];
    	 for (int j = 0; j<inarr.length; j++)
    	 {
    	     inarr[j] = rand.nextInt(1000)+1;
    	 }
    	 
    	//or use this for individual specific input
    	//int inarr[] = new int[]{7,15,2,14,14,6,8,11,17,15,14,13};
    	 
    	 /* Creating object of AVLTree */
        AVLTree avlt = new AVLTree(); 
        int COUNT=0;
 
        
		int c = 2;
		int pred=0;
		int succ=0;
		int p[]=new int[inarr.length];
		int m = 0,t=0;
		
		System.out.println(Arrays.toString(inarr));
	    System.out.println(Arrays.toString(p));
		
	    for(int i=0;i<inarr.length;i++)
		{
	    	COUNT++;
	    	System.out.println("Insert value "+inarr[i]+" index i "+i);
			avlt.insert(inarr[i]);
			
			pred = avlt.predecessor(inarr[i]);
			System.out.println("Predecessor of "+inarr[i]+" is "+pred);
			if(pred>0)
			{
				int indexNum = avlt.getArrayIndex(inarr,pred);
				p[i] = indexNum;
				//p[i] = avlt.getindex(pred);
				System.out.println("Index of "+pred+" is "+p[i]);
			}
			else
			{
				p[i] = i;
				System.out.println("P["+i+"] is "+p[i]);
			}
			
			//System.out.println("Insert "+inarr[i]+" index i "+i);
			//avlt.insert(inarr[i],i);
			System.out.println(Arrays.toString(p));
			int pre=avlt.predecessor(inarr[i]+c);
			succ = avlt.successor(pre);
		
			if(succ!=0)
			{
				System.out.println("Delete Successor "+succ);
				avlt.delete(succ);
			}
			System.out.println();
		}
		
		System.out.print("\nIn order : ");
        avlt.inorder();
        System.out.println();
        
        System.out.println(Arrays.toString(inarr));
        System.out.println(Arrays.toString(p));
        
        COUNT=COUNT+ avlt.count();
    	System.out.println("COUNT is "+COUNT);	
		
        System.out.println("\n <================== End =================> \n");
       /* //System.out.println("Xi ");
		m = avlt.maxValueNodeIndex();
		for(int j=p.length-1;j>m;j--)
		{
			if(((inarr[m]-c) < inarr[j]) && (inarr[j]<= inarr[m]))
				System.out.print(inarr[j]+" ");
		}
		//System.out.println("Xm "+inarr[m]);
		
		t = m;
		while(p[t]!=t)
		{
			for(int k=t-1;k>p[t]+1;k--)
			{
				if(((inarr[p[t]]-c)<inarr[k]) && (inarr[k]<=inarr[p[t]]))
				{
					System.out.print("Xi "+inarr[k]+" ");
				}
			}
			t = p[t];
		}*/
		
		
    }
 }