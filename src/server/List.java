package server;

public class List implements ListInterface {
    private Node begin;
    private Node end;
    private int size;

    public List() {
        begin = end = null;
        this.size = 0;
    }

    @Override
    public boolean isEmpty() {
        return begin == null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void addEnd(Object x) {
        Node p = new Node(x, null);
        if (isEmpty())
            begin = p;
        else
            end.setNext(p);
        end = p;
        size++;
    }

    @Override
    public void remove() {
        Node aux = begin;
        begin = aux.getNext();
        size--;
    }

    @Override
    public void removeEnd() {
        Node aux=begin;
        while (aux.getNext()!=end){
            aux=aux.getNext();
        }
        end=aux;
        end.setNext(null);
        size--;
    }
    

    public Node getBegin() {
        return begin;
    }

    public Node getEnd() {
        return end;
    }
}
