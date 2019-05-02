import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * Your implementation of an AVL Tree.
 *
 * @author Hussain Mumtaz
 * @version 1.0
 */
public class AVL<T extends Comparable<? super T>> implements AVLInterface<T> {

    // Do not make any new instance variables.
    private AVLNode<T> root;
    private int size;

    /**
     * A no argument constructor that should initialize an empty AVL tree.
     */
    public AVL() {

    }

    /**
     * Initializes the AVL tree with the data in the Collection. The data
     * should be added in the same order it is in the Collection.
     *
     * @param data the data to add to the tree
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public AVL(Collection<T> data) throws IllegalArgumentException {
        if (data == null) {
            throw new IllegalArgumentException("Data set is null");
        }
        for (T dat : data) {
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
            AVLNode<T> newNode = get(root, data);
            calcDeterminants(newNode);
            balanceTree(newNode);
        }
    }

    /**
     * @param node the node preceding the data
     * @param data the data to add to the tree
     * @return a node of the old AVL with the new data node linked
     */
    private AVLNode<T> add(AVLNode<T> node, T data) {
        if (node == null) {
            node = new AVLNode<>(data);
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


    /**
     * Calculates the balanceFactor and Heights of all nodes above given node
     * @param node the node being taken in
     */
    private void calcDeterminants(AVLNode<T> node) {
        if (node.getLeft() == null && node.getRight() == null) {
            node.setHeight(0);
            node.setBalanceFactor(0);
        } else if (node.getLeft() == null) {
            node.setHeight(node.getRight().getHeight() + 1);
            node.setBalanceFactor(-1 - node.getRight().getHeight());
        } else if (node.getRight() == null) {
            node.setHeight(node.getLeft().getHeight() + 1);
            node.setBalanceFactor(node.getLeft().getHeight() + 1);
        } else {
            int max = Math.max(node.getLeft().getHeight(), node.getRight()
                    .getHeight());
            node.setHeight(max + 1);
            int balFact = node.getLeft().getHeight() - node.getRight()
                    .getHeight();
            node.setBalanceFactor(balFact);
        }

        if (!(node.getData().equals(root.getData()))) {
            calcDeterminants(getParent(root, node));
        }
    }

    /**
     * balances tree nodes above given node
     * @param node the node being taken in
     */
    private void balanceTree(AVLNode<T> node) {
        AVLNode<T> parent = null;
        int parBal = 0;
        if (node.getHeight() == 0) {
            if (!(node.getData().equals(root.getData()))) {
                balanceTree(getParent(root, node));
            } else {
                parent = node;
                parBal = parent.getBalanceFactor();
            }
        } else {
            if (node.getData().equals(root.getData())) {
                parent = node;
                parBal = parent.getBalanceFactor();
            } else {
                parent = getParent(root, node);
                if (parent != null) {
                    parBal = parent.getBalanceFactor();
                }
            }
        }

        if (parBal < -1 && node.getBalanceFactor() <= 0) {
            leftRotate(parent, node);
            if (!(parent.getData().equals(root.getData()))) {
                balanceTree(parent);
            }
        } else if (parBal > 1 && node.getBalanceFactor() >= 0) {
            rightRotate(parent, node);
            if (!(parent.getData().equals(root.getData()))) {
                balanceTree(parent);
            }
        } else if (parBal < -1 && node.getBalanceFactor() > 0) {
            rightLeftRotate(parent, node);
            if (!(parent.getData().equals(root.getData()))) {
                balanceTree(parent);
            }
        } else if (parBal > 1 && node.getBalanceFactor() < 0) {
            leftRightRotate(parent, node);
            if (!(parent.getData().equals(root.getData()))) {
                balanceTree(parent);
            }
        } else {
            if (parent != null && !(parent.getData().equals(root.getData()))) {
                balanceTree(parent);
            }
        }
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
     * @throws NoSuchElementException if data not in AVL
     */
    private T remove(AVLNode<T> node, T data) throws NoSuchElementException {
        if (node == null) {
            throw new NoSuchElementException("Data not in AVL");
        } else if (data.compareTo(node.getData()) < 0) {
            return remove(node.getLeft(), data);
        } else if (data.compareTo(node.getData()) > 0) {
            return remove(node.getRight(), data);
        } else {
            if (node.getLeft() == null && node.getRight() == null) {
                T remData = node.getData();
                nullLeaf(node);
                size--;
                return remData;
            } else if (node.getLeft() != null && node.getRight() != null) {
                AVLNode<T> maxLeft = findMax(node.getLeft());
                T retData = node.getData();
                node.setData(maxLeft.getData());
                remove(node.getLeft(), maxLeft.getData());
                return retData;
            } else if (node.getLeft() != null) {
                T remData = node.getData();
                node.setData(node.getLeft().getData());
                if (node.getRight() != null && node.getLeft()
                        .getRight() != null) {
                    node.getRight().setLeft(node.getLeft().getRight());
                    node.getLeft().setRight(null);
                } else {
                    if (node.getLeft().getRight() != null) {
                        node.setRight(node.getLeft().getRight());
                    }
                }
                if (node.getLeft().getLeft() != null) {
                    node.setLeft(node.getLeft().getLeft());
                } else {
                    node.setLeft(null);
                }
                size--;
                AVLNode<T> min = findMin(node);
                AVLNode<T> max = findMax(getParent(root, node));
                if (min != null) {
                    calcDeterminants(min);
                    balanceTree(min);
                }
                if (max != null) {
                    calcDeterminants(max);
                    balanceTree(max);
                }
                return remData;
            } else {
                T remData = node.getData();
                node.setData(node.getRight().getData());
                if (node.getLeft() != null && node.getRight()
                        .getLeft() != null) {
                    node.getLeft().setRight(node.getRight().getLeft());
                    node.getRight().setLeft(null);
                } else {
                    if (node.getRight().getLeft() != null) {
                        node.setLeft(node.getRight().getLeft());
                    }
                }
                if (node.getRight().getRight() != null) {
                    node.setRight(node.getRight().getRight());
                } else {
                    node.setRight(null);
                }
                size--;
                AVLNode<T> min = findMin(node);
                AVLNode<T> max = findMax(getParent(root, node));
                if (min != null) {
                    calcDeterminants(min);
                    balanceTree(min);
                }
                if (max != null) {
                    calcDeterminants(max);
                    balanceTree(max);
                }
                return remData;
            }
        }
    }

    /**
     * @param node finds the right most node of this node
     * @return the right most node
     */
    private AVLNode<T> findMax(AVLNode<T> node) {
        if (node == null) {
            return null;
        } else if (node.getRight() == null) {
            return node;
        }
        return findMax(node.getRight());
    }

    /**
     * @param node finds the right most node of this node
     * @return the right most node
     */
    private AVLNode<T> findMin(AVLNode<T> node) {
        if (node == null) {
            return null;
        } else if (node.getLeft() == null) {
            return node;
        }
        return findMin(node.getLeft());
    }

    /**
     * Removes a leaf node
     *
     * @param node the one to remove
     */
    private void nullLeaf(AVLNode<T> node) {
        AVLNode<T> prev = getParent(root, node);
        int comp = node.getData().compareTo(prev.getData());
        if (comp > 0) {
            prev.setRight(null);
            AVLNode<T> min = findMin(prev.getLeft());
            AVLNode<T> max = findMax(prev.getLeft());
            if (min != null) {
                calcDeterminants(min);
                balanceTree(min);
                calcDeterminants(min);
            }
            if (max != null) {
                calcDeterminants(max);
                balanceTree(max);
                calcDeterminants(max);
            }
        } else {
            prev.setLeft(null);
            AVLNode<T> max = findMax(prev.getRight());
            AVLNode<T> min = findMin(prev.getRight());
            if (max != null) {
                calcDeterminants(max);
                balanceTree(max);
                calcDeterminants(max);
            }
            if (min != null) {
                calcDeterminants(min);
                balanceTree(min);
                calcDeterminants(min);
            }
        }
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
     * @throws NoSuchElementException if Data not in AVL
     */
    private AVLNode<T> get(AVLNode<T> node, T data) throws
            NoSuchElementException {
        if (node == null) {
            throw new NoSuchElementException("Data not in AVL");
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
    private boolean contains(AVLNode<T> node, T data) {
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
     * @param node    adds the new node to preList
     * @return the complete preList
     */
    private List<T> preorder(ArrayList<T> preList, AVLNode<T> node) {
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
     * @param node     adds the new node to postList
     * @return the complete postList
     */
    private List<T> postorder(ArrayList<T> postList, AVLNode<T> node) {
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
     * @param node   adds the new node to inList
     * @return the complete inList
     */
    private List<T> inorder(ArrayList<T> inList, AVLNode<T> node) {
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
        Queue<AVLNode<T>> queue = new LinkedList<>();
        if (root != null) {
            queue.add(root);
            while (!queue.isEmpty()) {
                AVLNode<T> temp = queue.poll();
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
        } else {
            return root.getHeight();
        }
    }

    @Override
    public int depth(T data) throws NoSuchElementException,
            IllegalArgumentException {
        if (data == null) {
            throw new IllegalArgumentException("Data is null");
        } else if (data.equals(root.getData())) {
            return 1;
        } else {
            return depth(root, data);
        }
    }

    /**
     * @param node data is checked against data parameter for equality
     * @param data searching for this data
     * @return the depth of the data
     * @throws NoSuchElementException if Data not in AVL
     */
    private int depth(AVLNode<T> node, T data) throws NoSuchElementException {
        if (node == null) {
            throw new NoSuchElementException("Data not in AVL");
        } else if (node.getData().equals(data)) {
            return 1;
        } else {
            int comp = data.compareTo(node.getData());
            if (comp > 0) {
                return 1 + depth(node.getRight(), data);
            } else {
                return 1 + depth(node.getLeft(), data);
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
    public AVLNode<T> getRoot() {
        return root;
    }
    /**
     * @param parent checked for lineage
     * @param node looking for it's parent
     * @return the parent node of the given node
     */
    private AVLNode<T> getParent(AVLNode<T> parent, AVLNode<T> node) {
        if (node.getData().equals(root.getData())) {
            return null;
        } else if (parent.getRight() == null) {
            if (parent.getLeft().getData().equals(node.getData())) {
                return parent;
            } else {
                return getParent(parent.getLeft(), node);
            }
        } else if (parent.getLeft() == null) {
            if (parent.getRight().getData().equals(node.getData())) {
                return parent;
            } else {
                return getParent(parent.getRight(), node);
            }
        } else {
            if (parent.getLeft().getData().equals(node.getData())
                    || parent.getRight().getData().equals(node.getData())) {
                return parent;
            } else {
                int comp = node.getData().compareTo(parent.getData());
                if (comp > 0) {
                    return getParent(parent.getRight(), node);
                } else {
                    return getParent(parent.getLeft(), node);
                }
            }
        }
    }

    /**
     * Performs a right rotation on a node
     * @param parent node above node
     * @param node being rotated
     */
    private void rightRotate(AVLNode<T> parent, AVLNode<T> node) {
        AVLNode<T> oldRight = parent.getRight();
        AVLNode<T> parDat = new AVLNode<>(parent.getData());
        parent.setRight(parDat);
        if (oldRight != null) {
            parent.getRight().setRight(oldRight);
            calcDeterminants(parent.getRight().getRight());
        }
        if (node.getRight() != null) {
            parent.getRight().setLeft(node.getRight());
            calcDeterminants(parent.getRight().getLeft());
        }
        parent.setData(node.getData());
        if (node.getLeft() != null) {
            parent.setLeft(node.getLeft());
            calcDeterminants(parent.getLeft());
        } else {
            parent.setLeft(null);
            calcDeterminants(parent.getRight());
        }
    }

    /**
     * Performs a left rotation on a node
     * @param parent node above node
     * @param node being rotated
     */
    private void leftRotate(AVLNode<T> parent, AVLNode<T> node) {
        AVLNode<T> oldLeft = parent.getLeft();
        AVLNode<T> parDat = new AVLNode<>(parent.getData());
        parent.setLeft(parDat);
        if (oldLeft != null) {
            parent.getLeft().setLeft(oldLeft);
            calcDeterminants(parent.getLeft().getLeft());
        }
        if (node.getLeft() != null) {
            parent.getLeft().setRight(node.getLeft());
            calcDeterminants(parent.getLeft().getRight());
        }
        parent.setData(node.getData());
        if (node.getRight() != null) {
            parent.setRight(node.getRight());
            calcDeterminants(parent.getRight());
        } else {
            parent.setRight(null);
            calcDeterminants(parent.getLeft());
        }
    }

    /**
     * Performs a right-left rotation on a node
     * @param parent node above node
     * @param node being rotated
     */
    private void rightLeftRotate(AVLNode<T> parent, AVLNode<T> node) {
        AVLNode<T> child = node.getLeft();
        AVLNode<T> oldNodeRight = node.getRight();
        AVLNode<T> oldChildLeft = child.getLeft();
        AVLNode<T> oldChildRight = child.getRight();
        AVLNode<T> nodeDat = new AVLNode<>(node.getData());
        node.setRight(nodeDat);
        if (oldNodeRight != null) {
            node.getRight().setRight(oldNodeRight);
            calcDeterminants(node.getRight().getRight());
        }
        node.setData(child.getData());
        if (oldChildRight != null) {
            node.getRight().setLeft(oldChildRight);
            calcDeterminants(node.getRight().getLeft());
        } else {
            node.getRight().setLeft(null);
            calcDeterminants(node.getRight());
        }
        if (oldChildLeft != null) {
            node.setLeft(oldChildLeft);
            calcDeterminants(node.getLeft());
        } else {
            node.setLeft(null);
            calcDeterminants(node.getRight());
        }

        AVLNode<T> oldLeft = parent.getLeft();
        AVLNode<T> parDat = new AVLNode<>(parent.getData());
        parent.setLeft(parDat);
        if (oldLeft != null) {
            parent.getLeft().setLeft(oldLeft);
            calcDeterminants(parent.getLeft().getLeft());
        }
        if (node.getLeft() != null) {
            parent.getLeft().setRight(node.getLeft());
            calcDeterminants(parent.getLeft().getRight());
        }
        parent.setData(node.getData());
        if (node.getRight() != null) {
            parent.setRight(node.getRight());
            calcDeterminants(parent.getRight());
        } else {
            parent.setRight(null);
            calcDeterminants(parent.getLeft());
        }
    }

    /**
     * Performs a left-right rotation on a node
     * @param parent node above node
     * @param node being rotated
     */
    private void leftRightRotate(AVLNode<T> parent, AVLNode<T> node) {
        AVLNode<T> child = node.getRight();
        AVLNode<T> oldNodeLeft = node.getLeft();
        AVLNode<T> oldChildLeft = child.getLeft();
        AVLNode<T> oldChildRight = child.getRight();
        AVLNode<T> nodeDat = new AVLNode<>(node.getData());
        node.setLeft(nodeDat);
        if (oldNodeLeft != null) {
            node.getLeft().setLeft(oldNodeLeft);
            calcDeterminants(node.getLeft().getLeft());
        }
        node.setData(child.getData());
        if (oldChildLeft != null) {
            node.getLeft().setRight(oldChildLeft);
            calcDeterminants(node.getLeft().getRight());
        } else {
            node.getLeft().setRight(null);
            calcDeterminants(node.getLeft());
        }
        if (oldChildRight != null) {
            node.setRight(oldChildRight);
            calcDeterminants(node.getRight());
        } else {
            node.setRight(null);
            calcDeterminants(node.getLeft());
        }

        AVLNode<T> oldRight = parent.getRight();
        AVLNode<T> parDat = new AVLNode<>(parent.getData());
        parent.setRight(parDat);
        if (oldRight != null) {
            parent.getRight().setRight(oldRight);
            calcDeterminants(parent.getRight().getRight());
        }
        if (node.getRight() != null) {
            parent.getRight().setLeft(node.getRight());
            calcDeterminants(parent.getRight().getLeft());
        }
        parent.setData(node.getData());
        if (node.getLeft() != null) {
            parent.setLeft(node.getLeft());
            calcDeterminants(parent.getLeft());
        } else {
            parent.setLeft(null);
            calcDeterminants(parent.getRight());
        }
    }

}