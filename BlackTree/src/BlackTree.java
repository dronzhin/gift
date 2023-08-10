public class BlackTree<V extends Comparable<V>>{
    private Node root;
    private class Node{
        private V value;
        private Color color;
        private Node leftChild;
        private Node rightChild;

        @Override
        public String toString() {
            return "Node{ " + "value=" + value + ", color=" + color + "}";
        }
    }
    private enum Color{
        RED, BLACK
    }
    private boolean addNode(Node node, V value){
        if(node.value.equals(value)){
            return false;
        }else{
            if (node.value.compareTo(value) > 0){
                if (node.leftChild != null){
                    boolean result = addNode(node.leftChild, value);
                    node.leftChild = rebalnce(node.leftChild);
                    return result;
                } else {
                    node.leftChild = new Node();
                    node.leftChild.color = Color.RED;
                    node.leftChild.value = value;
                    return true;
                }
            } else {
                if (node.rightChild != null){
                    boolean result = addNode(node.rightChild, value);
                    node.leftChild = rebalnce(node.rightChild);
                    return result;
                } else {
                    node.rightChild = new Node();
                    node.rightChild.color = Color.RED;
                    node.rightChild.value = value;
                    return true;
                }
            }
        }
    }
    public boolean add(V value){
        if (root != null){
            boolean result = addNode(root, value);
            root = rebalnce(root);
            root.color = Color.BLACK;
            return result;
        } else {
            root = new Node();
            root.color = Color.BLACK;
            root.value = value;
            return true;
        }
    }
    private void colorSwap(Node node){
        node.leftChild.color = Color.BLACK;
        node.rightChild.color = Color.BLACK;
        node.color = Color.RED;
    }
    private Node leftSwap(Node node){
        Node leftChild = node.leftChild;
        Node beetweenChild = leftChild.rightChild;
        leftChild.rightChild = node;
        node.leftChild = beetweenChild;
        leftChild.color = node.color;
        node.color = Color.RED;
        return leftChild;
    }
    private Node rightSwap(Node node){
        Node rightChild = node.rightChild;
        Node beetweenChild = rightChild.rightChild;
        rightChild.leftChild = node;
        node.rightChild = beetweenChild;
        rightChild.color = node.color;
        node.color = Color.RED;
        return rightChild;
    }
    private Node rebalnce(Node node){
        Node result = node;
        boolean needRebalance;
        do{
            needRebalance = false;
            if (result.rightChild != null && result.rightChild.color == Color.RED &&
                    (result.leftChild == null || result.leftChild.color == Color.BLACK)){
                needRebalance = true;
                result = rightSwap(result);
            }
            if (result.leftChild != null && result.leftChild.color == Color.RED &&
                    result.leftChild.leftChild != null && result.leftChild.leftChild.color == Color.RED){
                needRebalance = true;
                result = leftSwap(result);
            }
            if (result.leftChild != null && result.leftChild.color == Color.RED &&
                    result.rightChild != null && result.rightChild.color == Color.RED){
                needRebalance = true;
                colorSwap(result);
            }

        } while (needRebalance);
        return result;
    }
}