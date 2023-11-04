import java.util.ArrayList;

public class AvlTree<K extends Comparable<K>, V> implements MultiMap<K, V> {

    public static class AvlNode<K extends Comparable<K>, V> implements NodeStrategy<K, V> {
        private K key;
        private V value;
        private int height;

        public AvlNode<K, V> left, right;
        public AvlNode(K key, V value){
            setKey(key);
            setValue(value);
        }

        public void setKey(K key){
            this.key = key;
        }

        public K getKey(){
            return key;
        }

        public void setValue(V value){
            this.value = value;
        }

        public V getValue(){
            return value;
        }

    }

    private AvlNode<K, V> root;
    int size = 0;

    public void inOrder(){
        inOrderAux(root);
    }

    private void inOrderAux(AvlNode<K, V> root){
        if(root != null){
            inOrderAux(root.left);
            System.out.print(root.getValue() + "\n");
            inOrderAux(root.right);
        }
    }

    public void put(K key, V value){
        root = put(root, key, value);
        size++;
    }

    private AvlNode<K, V> put(AvlNode<K, V> node, K key, V value){
        if(node == null){
            return new AvlNode<>(key, value);
        }

        int cmp = key.compareTo(node.getKey());
        if(cmp < 0){
            node.left = put(node.left, key, value);
        } else {
            node.right = put(node.right, key, value);
        }

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);

        if (balance > 1 && getBalance(node.left) > 0) {
            return rotateRight(node);
        }

        if (balance > 1 && getBalance(node.left) <= 0) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        if (balance < -1 && getBalance(node.right) < 0) {
            return rotateLeft(node);
        }

        if (balance < -1 && getBalance(node.right) >= 0) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    public void delete(K key){
        root = delete(root, key);
        size--;
    }

    private AvlNode<K, V> delete(AvlNode<K, V> node, K key) {
        if(node == null){
            return null;
        }

        int cmp = key.compareTo(node.getKey());
        if (cmp < 0) {
            node.left = delete(node.left, key);
        } else if (cmp > 0) {
            node.right = delete(node.right, key);
        } else {
            if (node.left == null || node.right == null) {
                if(node.left != null){
                    node = node.left;
                } else {
                    node = node.right;
                }
            } else {
                AvlNode<K, V> successor = treeMinimun(node.right);
                node.setKey(successor.getKey());
                node.setValue(successor.getValue());
                node.right = delete(node.right, successor.getKey());
            }
        }

        if (node == null) {
            return null;
        }

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);

        if (balance > 1 && getBalance(node.left) > 0) {
            return rotateRight(node);
        }

        if (balance > 1 && getBalance(node.left) <= 0) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        if (balance < -1 && getBalance(node.right) < 0) {
            return rotateLeft(node);
        }

        if (balance < -1 && getBalance(node.right) >= 0) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    public V get(K key){
        AvlNode<K, V> x = root;
        while(x != null && x.getKey().compareTo(key) != 0){
            if(x.getKey().compareTo(key) > 0){
                x = x.left;
            } else{
                x = x.right;
            }
        }

        return x.getValue();
    }

    public AvlNode<K, V> treeMinimun(AvlNode<K, V> root){
        AvlNode<K, V> x = root;
        while(x.left != null){
            x = x.left;
        }
        return x;
    }

    private int height(AvlNode<K, V> node){
        if(node == null){
            return 0;
        }

        return node.height;
    }

    private int getBalance(AvlNode<K, V> node){
        if(node == null){
            return 0;
        }
        return height(node.left) - height(node.right);
    }

    private AvlNode<K, V> rotateRight(AvlNode<K, V> y) {
        if(y == null || y.left == null){
            return y;
        }

        AvlNode<K, V> x = y.left;
        AvlNode<K, V> T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = 1 + Math.max(height(y.left), height(y.right));
        x.height = 1 + Math.max(height(x.left), height(x.right));

        return x;
    }

    private AvlNode<K, V> rotateLeft(AvlNode<K, V> x) {
        if(x == null || x.right == null){
            return x;
        }
        AvlNode<K, V> y = x.right;
        AvlNode<K, V> T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = 1 + Math.max(height(x.left), height(x.right));
        y.height = 1 + Math.max(height(y.left), height(y.right));

        return y;
    }

    public ArrayList<V> findAllK(K key) {
        ArrayList<V> values = new ArrayList<>();
        findAllK(root, key, values);
        return values;
    }

    private void findAllK(AvlNode<K, V> node, K key, ArrayList<V> values) {
        if (node == null) {
            return;
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            findAllK(node.left, key, values);
        } else if (cmp > 0) {
            findAllK(node.right, key, values);
        } else {
            values.add(node.value);
            findAllK(node.left, key, values);
            findAllK(node.right, key, values);
        }
    }

    public boolean contains(K key) {
        return contains(root, key);
    }

    private boolean contains(AvlNode<K, V> node, K key) {
        if (node == null) {
            return false;
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return contains(node.left, key);
        } else if (cmp > 0) {
            return contains(node.right, key);
        } else {
            return true;
        }
    }
}
