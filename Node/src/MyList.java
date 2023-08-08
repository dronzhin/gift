public class MyList {
    Node head;
    Node tail;
    public class Node{
        int value;
        Node next;
        Node previous;
    }
    public void add(int value){
        Node node = new Node();
        node.value = value;
        if(head == null){
            head = node;
            tail = node;
        } else {
            tail.next = node;
            node.previous = tail;
            tail = node;
        }
    }
    public void push(int value){
        Node node = new Node();
        node.value = value;
        node.next = head;
        head.previous = node; // очередь
        head = node;
    }
    public Integer peek(){
        Integer result = null;
        if (tail != null){
            result = tail.value;
            tail = tail.previous;
            tail.next = null;
        }
        return result;
    }
    public void revert(){
        Node currentNode = head;
        while(currentNode != null){
            Node next = currentNode.next;
            Node previous = currentNode.previous;
            currentNode.next = previous;
            currentNode.previous = next;
            if (currentNode.next == null){
                tail = currentNode;
            }
            if (currentNode.previous == null){
                head = currentNode;
            }
            currentNode = next;
        }
    }
    public void sort(){
        if(head == null || head.next == null) {return;}
        Node first = head;
        Node second = head.next;
        while (first.next != null){
            while (second != null){
                if (first.value > second.value){
                    int temp = first.value;
                    first.value = second.value;
                    second.value = temp;
                }
                second = second.next;
            }
            first = first.next;
        }
    }
    public void add(int value, Node node){
        Node next = node.next;
        Node newNode = new Node();
        newNode.value = value;
        node.next = newNode;
        newNode.previous = node;
        if (next == null){
            tail = newNode;
        } else {
            next.previous = newNode;
            newNode.next = next;
        }
    }
    public void delete(Node node){
        Node next = node.next;
        Node previous = node.previous;
        if (previous == null){
            head = next;
            next.previous = null;
        } else if (next == null) {
            previous.next = null;
            tail = previous;
        } else {
            next.previous = previous;
            previous.next = next;
        }
    }
    public Node find(int value){
        Node currentNode = head;
        while (currentNode != null){
            if (currentNode.value == value){
                return currentNode;
            }
            currentNode = currentNode.next;
        }
        return null;
    }
    public void print(){
        if (head == null){
            System.out.println("Нет данных");
            return;
        }
        Node currentNode = head;
        String result = "{ ";
        while (currentNode != null){
            result += currentNode.value + " ";
            currentNode = currentNode.next;
        }
        System.out.println(result + "}");
    }
    public MyList myRevert(){
        MyList newList = new MyList();
        if (head == null || head.next == null) {return newList;}
        Node node = tail;
        while (node != null){
            newList.add(node.value);
            node = node.previous;
        }
        return newList;
    }
}
