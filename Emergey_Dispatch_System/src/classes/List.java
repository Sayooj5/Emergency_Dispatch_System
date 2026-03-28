package classes;

public class List<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public List() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }
    //
    public List(Node<T> head) {
        this.head = head;
        this.tail = head;
        if (head != null) {
            this.size = 1;
        } else {
            this.size = 0;
        }
    }

    public Node<T> getHead() {
        return this.head;
    }

    public Node<T> getTail() {
        return this.tail;
    }

    public int getSize() {
        return this.size;
    }

    //Adds a node at the end of the list
    public boolean append(Node<T> node) {
        if (node == null) {
            return false;
        }
        if (head == null) { 
            head = node;
            tail = node;
        } else {
            tail.setNext(node);
            node.setPrev(tail);
            tail = node;
        }
        size++;
        return true;
    }
//Inserts a node at the given index of the list.
    public boolean insert(int index, Node<T> node) {
    	 if (index < 0 || index > size || node == null) {
             return false;
         }

         if (index == 0) {
             node.setNext(head);
             if (head != null) {
                 head.setPrev(node);
             }
             head = node;
             if (tail == null) {
                 tail = node; 
             }
         } else if (index == size) {
             append(node);
         } else {
             Node<T> currentnode = head;
             for (int i = 0; i < index - 1; i++) {
                 currentnode = currentnode.getNext();
             }

             node.setNext(currentnode.getNext());
             node.setPrev(currentnode);
             if (currentnode.getNext() != null) {
                 currentnode.getNext().setPrev(node);
             }
             currentnode.setNext(node);
         }

         size++;
         return true;
    }
    public boolean remove(int index) {
        if (index < 0 || index >= size) {
            return false;
        }

        if (index == 0) {
            // Remove the head node
            head = head.getNext();
            if (head != null) {
                head.setPrev(null);
            } else {
                tail = null; 
            }
        } else if (index == size - 1) {
            // Remove the tail node
            tail = tail.getPrev();
            if (tail != null) {
                tail.setNext(null);
            } else {
                head = null; 
            }
        } else {
            // Remove a node in the middle
            Node<T> currentnode = head;
            for (int i = 0; i < index; i++) {
                currentnode = currentnode.getNext();
            }

            currentnode.getPrev().setNext(currentnode.getNext());
            currentnode.getNext().setPrev(currentnode.getPrev());
        }

        size--;
        return true;
    }


    public boolean remove(T content) {
        if (head == null) { 
            return false;
        }

        Node<T> currentnode = head;

        
        while (currentnode != null) {
            if (currentnode.getContent().equals(content)) {
               //Removing the head node
                if (currentnode == head) {
                    head = currentnode.getNext();
                    if (head != null) {
                        head.setPrev(null); 
                    } else {
                        tail = null; 
                    }
                }
                //  Removing the tail node
                else if (currentnode == tail) {
                    tail = currentnode.getPrev();
                    if (tail != null) {
                        tail.setNext(null); 
                    }
                }
                //  Removing a middle node
                else {
                    currentnode.getPrev().setNext(currentnode.getNext());
                    currentnode.getNext().setPrev(currentnode.getPrev());
                }

                size--; 
                return true; 
            }

            currentnode = currentnode.getNext(); 
        }

        return false; 
    }
    
    public Node<T> get(int index) {
    	//chatgpt
        if (index < 0 || index >= size) {
            return null; 
        }

        Node<T> current;
        if (index < size / 2) {
            // Start from the head for indexes in the first half
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
        } else {
            // Start from the tail for indexes in the second half
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.getPrev();
            }
        }

        return current; // Return the node at the given index
    }


public boolean swap(int indexOne, int indexTwo) {
        
        Node<T> nodeOne = get(indexOne), nodeTwo = get(indexTwo);
        if (nodeOne == null || nodeTwo == null) 
        	return false;

        // Update neighbors of nodeOne
        if (nodeOne.getPrev() != null) 
        	nodeOne.getPrev().setNext(nodeTwo);
        if (nodeOne.getNext() != null) 
        	nodeOne.getNext().setPrev(nodeTwo);

        // Update neighbors of nodeTwo
        if (nodeTwo.getPrev() != null) 
        	nodeTwo.getPrev().setNext(nodeOne);
        if (nodeTwo.getNext() != null) 
        	nodeTwo.getNext().setPrev(nodeOne);
        
        
        Node<T> temp = nodeOne.getPrev();
        nodeOne.setPrev(nodeTwo);
        nodeOne.setNext(nodeTwo.getNext());
        nodeTwo.setPrev(temp);
        nodeTwo.setNext(nodeOne);

        // Update head and tail if necessary
        if (indexOne == 0) 
        	head = nodeTwo;
        if (indexTwo == size - 1) 
        	tail = nodeOne;

        return true;
    }
}