public class Main {
    public static void main(String[] args) {

        MyList list = new MyList();
        list.print();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(100);
        list.print();
        list.myRevert().print();
    }
}