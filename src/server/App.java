package server;

public class App {
    public static void main(String[] args) {
        List mylist = new List();
        mylist.addEnd(1);
        mylist.addEnd(2);
        mylist.addEnd(3);
        mylist.addEnd(4);
        System.out.println(mylist.isEmpty());
        System.out.println(mylist.getBegin().getNext().getNext().getData());
        System.out.println(mylist.getEnd().getData());
    }
}
