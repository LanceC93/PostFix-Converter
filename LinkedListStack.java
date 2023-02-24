//Name: Lance Cross
//Due Date: 2/26/23
//represents a stack using a singly linked list

public class LinkedListStack<E> {
    
    //holds the stack
    private SinglyLinkedList<E> stack;

    /**constructs a new object with an empty list */
    public LinkedListStack() {
        stack = new SinglyLinkedList<>();
    }

    /**
     * creates a string reprentation of the stack with the first element as the top
     */
    public String toString() {
        return stack.toString();
    }

    /**
     * gives the value of the top element in the stack
     * @return value in the top node in the stack
     */
    public E top() {
        return stack.first();
    }


    /**
     * removes top element from stack and returns its value
     * returns null if stack is empty
     * @return value in the top node
     */
    public E pop() {
        return stack.removeFirst();;
    }

    /**
     * pushes a new element onto the top of the stack
     * @param e value of new element being added to stack
     */
    public void push(E e) {
        stack.addFirst(e);
    }
}
