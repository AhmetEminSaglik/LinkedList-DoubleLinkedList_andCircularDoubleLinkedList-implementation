package Business;

public class Node {

    public Object data;
    public Node next;
    public Node before;

    public Node() {
        this.before = null;
        this.next = null;
    }

    public Node(Object data) {
        this.data = data;
    }
}
