import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;


public class BST<T extends Comparable<? super T>> implements BSTInterface<T> {
    // DO NOT ADD OR MODIFY INSTANCE VARIABLES.
    private BSTNode<T> root;
    private int size;

    /**
     * A no argument constructor that should initialize an empty BST
     */
    public BST() {

    }

    /**
     * Initializes the BST with the data in the Collection. The data in the BST
     * should be added in the same order it is in the Collection.
     *
     * @param data the data to add to the tree
     * @throws java.lang.IllegalArgumentException if data or any element
     * in data is null
     */
    public BST(Collection<T> data) throws IllegalArgumentException {
        if (data == null) {
            throw new IllegalArgumentException("Data set is null");
        }
        for (T dat: data) {
            if (dat == null) {
                throw new IllegalArgumentException("Data is null");
            } else {
                add(dat);
            }
        }
    }

    @Override
    public void add(T data) throws IllegalArgumentException {
        if (data == null) {
            throw new IllegalArgumentException("Data is null");
        } else {
            root = add(root, data);
        }
    }

    /**
     * @param node the node preceding the data
     * @param data the data to add to the tree
     * @return a node of the old BST with the new data node linked
     */
    private BSTNode<T> add(BSTNode<T> node, T data) {
        if (node == null) {
            node = new BSTNode<>(data);
            size++;
        } else {
            int comp = data.compareTo(node.getData());
            if (comp < 0) {
                node.setLeft(add(node.getLeft(), data));
            } else if (comp > 0) {
                node.setRight(add(node.getRight(), data));
            }
        }
        return node;
    }

    @Override
    public T remove(T data) throws IllegalArgumentException,
            NoSuchElementException {
        if (data == null) {
            throw new IllegalArgumentException("Data is invalid");
        } else if (size == 1 && root.getData() == data) {
            T retData = root.getData();
            size--;
            root = null;
            return retData;
        } else {
            return remove(root, data);
        }
    }
    /**
     * @param node the node checked against data
     * @param data the data node to remove
     * @return the data of the removed node
     * @throws NoSuchElementException if data not in BST
     */
    private T remove(BSTNode<T> node, T data) throws NoSuchElementException {
        if (node == null) {
            throw new NoSuchElementException("Data not in BST");
        } else if (data.compareTo(node.getData()) < 0) {
            return remove(node.getLeft(), data);
        } else if (data.compareTo(node.getData()) > 0) {
            return remove(node.getRight(), data);
        } else {
            if (node.getLeft() == null && node.getRight() == null) {
                T remData = node.getData();
                nullLeaf(root, node);
                size--;
                return remData;
            } else if (node.getLeft() != null && node.getRight() != null) {
                BSTNode<T> maxLeft = findMax(node.getLeft());
                T retData = node.getData();
                node.setData(maxLeft.getData());
                remove(node.getLeft(), maxLeft.getData());
                return retData;
            } else if (node.getLeft() != null) {
                BSTNode<T> removed = new BSTNode<>(node.getData());
                removed.setRight(node.getRight());
                removed.setLeft(node.getLeft());
                T remData = node.getData();
                node.setData(removed.getLeft().getData());
                node.setLeft(removed.getLeft().getLeft());
                node.setRight(removed.getLeft().getRight());
                size--;
                return remData;
            } else {
                BSTNode<T> removed = new BSTNode<>(node.getData());
                removed.setRight(node.getRight());
                removed.setLeft(node.getLeft());
                T remData = node.getData();
                node.setData(removed.getRight().getData());
                node.setLeft(removed.getRight().getLeft());
                node.setRight(removed.getRight().getRight());
                size--;
                return remData;
            }
        }
    }

    /**
     * @param node finds the right most node of this node
     * @return the right most node
     */
    private BSTNode<T> findMax(BSTNode<T> node) {
        if (node.getRight() == null) {
            return node;
        }
        return findMax(node.getRight());
    }

    @Override
    public T get(T data) throws IllegalArgumentException,
            NoSuchElementException {
        if (data == null) {
            throw new IllegalArgumentException("Data is null");
        } else {
            return (get(root, data)).getData();
        }
    }

    /**
     * @param node data is checked against data parameter for equality
     * @param data searching for this data
     * @return the node containing data equal to parameter data
     * @throws NoSuchElementException if Data not in BST
     */
    private BSTNode<T> get(BSTNode<T> node, T data) throws
            NoSuchElementException {
        if (node == null) {
            throw new NoSuchElementException("Data not in BST");
        } else if (node.getData().equals(data)) {
            return node;
        } else {
            int comp = data.compareTo(node.getData());
            if (comp > 0) {
                return get(node.getRight(), data);
            } else {
                return get(node.getLeft(), data);
            }
        }
    }

    @Override
    public boolean contains(T data) throws IllegalArgumentException {
        if (data == null) {
            throw new IllegalArgumentException("Data is null");
        } else {
            return contains(root, data);
        }
    }

    /**
     * @param node data is checked against data parameter for equality
     * @param data searching for this data
     * @return the boolean true/false if data is in the data set
     */
    private boolean contains(BSTNode<T> node, T data) {
        if (node == null) {
            return false;
        } else if (node.getData().equals(data)) {
            return true;
        } else {
            int comp = data.compareTo(node.getData());
            if (comp > 0) {
                return contains(node.getRight(), data);
            } else {
                return contains(node.getLeft(), data);
            }
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public List<T> preorder() {
        ArrayList<T> preList = new ArrayList<>();
        return preorder(preList, root);
    }

    /**
     * @param preList stores the preorder arrayList
     * @param node adds the new node to preList
     * @return the complete preList
     */
    private List<T> preorder(ArrayList<T> preList, BSTNode<T> node) {
        if (node != null) {
            preList.add(node.getData());
            preorder(preList, node.getLeft());
            preorder(preList, node.getRight());
        }
        return preList;
    }

    @Override
    public List<T> postorder() {
        ArrayList<T> postList = new ArrayList<>();
        return postorder(postList, root);
    }

    /**
     * @param postList stores the postorder arrayList
     * @param node adds the new node to postList
     * @return the complete postList
     */
    private List<T> postorder(ArrayList<T> postList, BSTNode<T> node) {
        if (node != null) {
            postorder(postList, node.getLeft());
            postorder(postList, node.getRight());
            postList.add(node.getData());
        }
        return postList;
    }

    @Override
    public List<T> inorder() {
        ArrayList<T> inList = new ArrayList<>();
        return inorder(inList, root);
    }

    /**
     * @param inList stores the inorder arrayList
     * @param node adds the new node to inList
     * @return the complete inList
     */
    private List<T> inorder(ArrayList<T> inList, BSTNode<T> node) {
        if (node != null) {
            inorder(inList, node.getLeft());
            inList.add(node.getData());
            inorder(inList, node.getRight());
        }
        return inList;
    }

    @Override
    public List<T> levelorder() {
        ArrayList<T> levList = new ArrayList<>();
        Queue<BSTNode<T>> queue = new LinkedList<>();
        if (root != null) {
            queue.add(root);
            while (!queue.isEmpty()) {
                BSTNode<T> temp = queue.poll();
                levList.add(temp.getData());
                if (temp.getLeft() != null) {
                    queue.add(temp.getLeft());
                }
                if (temp.getRight() != null) {
                    queue.add(temp.getRight());
                }
            }
        }
        return levList;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public int height() {
        if (root == null) {
            return -1;
        } else if (root.getLeft() == null && root.getRight() == null) {
            return 0;
        } else {
            return (height(root)) - 1;
        }
    }
    /**
     * @param node gets height of node
     * @return the integer height + 1
     */
    private int height(BSTNode<T> node) {
        if (node == null) {
            return 0;
        } else {
            return (Math.max(height(node.getLeft()),
                    height(node.getRight())) + 1);
        }
    }
    /**
     * Adjusts in the right node of the previous left node
     * if root node is removed
     * @param compNode node being compared against
     * @param posNode node at current position
     */
    private void adjust(BSTNode<T> compNode, BSTNode<T> posNode) {
        if (compNode != null) {
            int comp = compNode.getData().compareTo(posNode.getData());
            if (comp > 0) {
                BSTNode<T> newCompNode = posNode.getRight();
                posNode.setRight(compNode);
                adjust(newCompNode, posNode.getRight().getLeft());
            } else if (comp < 0) {
                BSTNode<T> newCompNode = posNode.getLeft();
                posNode.setLeft(compNode);
                adjust(newCompNode, posNode.getRight().getLeft());
            }
        }
    }

    /**
     * Removes a leaf node
     * @param prev nodes before removal point
     * @param node the one to remove
     */
    private void nullLeaf(BSTNode<T> prev, BSTNode<T> node) {
        int comp = node.getData().compareTo(prev.getData());
        if (comp > 0) {
            if (prev.getRight() == node) {
                prev.setRight(null);
            } else {
                nullLeaf(prev.getRight(), node);
            }
        } else {
            if (prev.getLeft() == node) {
                prev.setLeft(null);
            } else {
                nullLeaf(prev.getLeft(), node);
            }
        }

    }

    /**
     * THIS METHOD IS ONLY FOR TESTING PURPOSES.
     * DO NOT USE IT IN YOUR CODE
     * DO NOT CHANGE THIS METHOD
     *
     * @return the root of the tree
     */
    public BSTNode<T> getRoot() {
        return root;
    }
}