public class AvlTree<T extends Comparable<T>> {

    public static class AvlNode<T extends Comparable<T>> {
        private T value;
        private int height;

        public AvlNode<T> left, right;
        public AvlNode(T value){
            setValue(value);
        }

        public void setValue(T value){
            this.value = value;
        }

        public T getValue(){
            return value;
        }

    }

    private AvlNode<T> root;
    int size = 0;

    public void inOrder(){
        inOrderAux(root);
    }

    private void inOrderAux(AvlNode<T> root){
        if(root != null){
            inOrderAux(root.left);
            System.out.print(root.getValue() + "\n");
            inOrderAux(root.right);
        }
    }

    public void insert(T value){
        root = insert(root, value);
        size++;
    }

    private AvlNode<T> insert(AvlNode<T> node, T value){
        if(node == null){
            return new AvlNode<>(value);
        }

        int cmp = value.compareTo(node.getValue());
        if(cmp < 0){
            node.left = insert(node.left, value);
        } else {
            node.right = insert(node.right, value);
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

    public void delete(T value){
        root = delete(root, value);
        size--;
    }

    private AvlNode<T> delete(AvlNode<T> node, T value) {
        if(node == null){
            return null;
        }

        int cmp = value.compareTo(node.getValue());
        if (cmp < 0) {
            node.left = delete(node.left, value);
        } else if (cmp > 0) {
            node.right = delete(node.right, value);
        } else {
            if (node.left == null || node.right == null) {
                if(node.left != null){
                    node = node.left;
                } else {
                    node = node.right;
                }
            } else {
                AvlNode<T> successor = treeMinimun(node.right);
                node.setValue(successor.getValue());
                node.right = delete(node.right, successor.getValue());
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

    public AvlNode<T> treeMinimun(AvlNode<T> root){
        AvlNode<T> x = root;
        while(x.left != null){
            x = x.left;
        }
        return x;
    }

    private int height(AvlNode<T> node){
        if(node == null){
            return 0;
        }

        return node.height;
    }

    private int getBalance(AvlNode<T> node){
        if(node == null){
            return 0;
        }
        return height(node.left) - height(node.right);
    }

    private AvlNode<T> rotateRight(AvlNode<T> y) {
        if(y == null || y.left == null){
            return y;
        }

        AvlNode<T> x = y.left;
        AvlNode<T> T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = 1 + Math.max(height(y.left), height(y.right));
        x.height = 1 + Math.max(height(x.left), height(x.right));

        return x;
    }

    private AvlNode<T> rotateLeft(AvlNode<T> x) {
        if(x == null || x.right == null){
            return x;
        }
        AvlNode<T> y = x.right;
        AvlNode<T> T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = 1 + Math.max(height(x.left), height(x.right));
        y.height = 1 + Math.max(height(y.left), height(y.right));

        return y;
    }

}
