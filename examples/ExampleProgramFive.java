public class fifthTestProgram {
    public static void main(String[] args) {
        LinkedList<Integer> il = new LinkedList<Integer>();
        il.addFirst(20);
        il.addLast(15);
        il.addFirst(10);
        
        PrintLinkedListInfo(il);

        il.addFirst(56);
        il.push(44);

        PrintLinkedListInfo(il);

        if (il.isEmpty()) {
            System.out.println("List is empty.");
        }
	    else {
	        if (il.contains(10)) {
	            System.out.println("il contains 10.");
	        }
	    }
    }

    public static <T> void PrintLinkedListInfo(LinkedList<T> list) {
        System.out.println("Size Of Linked List: " + list.sizeOf());
        System.out.println("Last Value: " + list.peek());
        System.out.println("Popped Value: " + list.pop());
        System.out.println("New Size Of: " + list.sizeOf());
        System.out.print("Values: ");
        Node<T> current = list.head;
        while (current != null) {
            System.out.print(current.getValue());

            if(current.getNext() != null) {
                System.out.print(" - ");
            }
            current = current.getNext();
        }

        System.out.println();
    }

    public class Node<T> {
        private T value;
        private Node<T> next;
        private Node<T> prev;
    
        public Node () {}
    
        public Node (T t) {
            this.value = t;
        }
    
        public T getValue() {return this.value;}
        public Node<T> getNext() {return this.next;}
        public Node<T> getPrev() {return this.prev;}
    
        public void setNext(Node<T> n) {this.next = n;}
        public void setPrev(Node<T> p) {this.prev = p;}
    }

    public class IntegerNode extends Node<Integer> {
        public IntegerNode(int i) {
            super(i);
        }
    }

    public interface ExampleInterface {}

    public class ConstrainedNode<T extends ExampleInterface> extends Node<T> {
        public ConstrainedNode(T t) {
            super(t);
        }
    } 

    public abstract class ExampleAbstractClass {}

    public class MultipleConstraintsNode<T extends ExampleAbstractClass & ExampleInterface> extends Node<T> {
        public ConstrainedNode(T t) {
            super(t);
        }
    }     

    public class MultipleTypeParametersNode<T, K, M extends ExampleInterface, L extends ExampleAbstractClass> extends Node<T> {
        public MultipleTypeParametersNode(T t) {
            super(t);
        }
    }

    public  class LinkedList<T> {
        private int length = 0;
        public Node<T> head;
        public Node<T> tail;
    
        public boolean isEmpty() {
            return this.length == 0;
        }
    
        public void push(T t) {
            Node<T> newNode = new Node<T> (t);
            if (this.isEmpty()) {
                this.head = newNode;
                this.tail = newNode;
            }
            else {
                newNode.setPrev(this.tail);
                this.tail.setNext(newNode);
                this.tail = newNode;
            }
           this.length += 1;
        }
    
        public T pop() {//fix this to throw exception
            if (!this.isEmpty()) {
                T temp = this.tail.getValue();
                this.tail.getPrev().setNext(null);
                this.length -= 1;
                return temp;
            }
            return null;
        }
    
        public T peek() {
            return this.tail.getValue();
        }
    
        public int sizeOf() {
            return this.length;
        }
    
        public void addFirst(T t) {
            Node<T> newFirst = new Node<T> (t);
            if (this.isEmpty()) {
                this.head = newFirst;
                this.tail = newFirst;
            }
            else {
                newFirst.setNext(this.head);
                this.head.setPrev(newFirst);
                this.head = newFirst;
            }
            this.length += 1;
        }
    
        public void addLast(T t) {
            Node<T> newLast = new Node<T> (t);
            if (this.isEmpty()) {
                this.head = newLast;
                this.tail = newLast;
            }
            else {
                newLast.setPrev(this.tail);
                this.tail.setNext(newLast);
                this.tail = newLast;
            }
            this.length += 1;
        }
    
        public void clear() {
            while (!this.isEmpty()) {
                this.pop();
            }
        }
    
        public boolean contains(T t) {
            Node<T> iter = this.head;
            while (iter != null) {
                if (iter.getValue().equals(t)) {
                    return true;
                }
                iter = iter.getNext();
            }
            return false;
        }
    
        public T getFirstValue() {
            return this.head.getValue();
        }
    
        public T getLastValue() {
            return this.tail.getValue();
        }
    }
}



