package server;

public class Node {
    private Object data;
    private Node next;

    Node(){
        data = null;
        next = null;
    }
    Node(Object data){
        this.data = data;
        next = null;
    }
    Node(Object data, Node next){
        this.data = data;
        this.next = next;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
