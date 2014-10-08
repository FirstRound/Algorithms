public class Deque<Item> implements Iterable<Item> {  
    private int size;         
    private Node first;     
    private Node last;     
  
        private class Node {  
            private Item item;  
            private Node next;  
            private Node prev;  
        }  
     
     
    public Deque() {  
        first = null;  
        last  = null;  
        size  = 0;  
    }

        public int size() {  
        return size;      
    }  
     
     
    public boolean isEmpty() {  
        return size == 0;  
    }  
      
     
    // insert the item at the front  
    public void addFirst(Item item){  
        if (item == null){  
            throw new java.lang.NullPointerException();  
        }  
        Node oldfirst = first;  
        first = new Node();  
        first.item = item;  
        first.prev = null;  
        if (isEmpty()){  
            last = first;  
            first.next = null;  
        }else{  
            oldfirst.prev = first;  
            first.next = oldfirst;  
        }  
        size++;  
    }  
     
    // insert the item at the end  
    public void addLast(Item item){  
        if (item == null){  
            throw new java.lang.NullPointerException();  
        }  
        Node oldlast = last;  
        last = new Node();  
        last.item = item;  
        last.next = null;  
        if (isEmpty()){  
            first = last;  
            last.prev = null;  
        }else{  
            oldlast.next = last;  
            last.prev = oldlast;  
        }  
        size++;  
    }  
     
    // delete and return the item at the front  
    public Item removeFirst(){  
        if (isEmpty()) throw new java.util.NoSuchElementException();  
        Item item = first.item;  
        first = first.next;  
        size--;  
        if (isEmpty()){  
            last = null;  
            first = null;  
        }else{  
            first.prev = null;  
        }  
        return item;  
    }  
     
    // delete and return the item at the end  
    public Item removeLast(){  
        if (isEmpty()) throw new java.util.NoSuchElementException();  
        Item item = last.item;  
        last = last.prev;  
        size--;  
        if (isEmpty()){  
            last = null;  
            first = null;  
        }else{  
            last.next = null;  
        }  
        return item;  
    }  
     
     
    public java.util.Iterator<Item> iterator()  {  
        return new ListIterator();   
    }  
  
    // an iterator, doesn't implement remove() since it's optional  
    private class ListIterator implements java.util.Iterator<Item> {  
        private Node current = first;  
  
        public boolean hasNext()  { 
            return current != null;                     
        }  
        public void remove() { 
            throw new java.lang.UnsupportedOperationException();  
        }  
  
        public Item next() {  
            if (!hasNext()) 
                throw new java.util.NoSuchElementException();  
            Item item = current.item;  
            current = current.next;  
            return item;  
        }  
    }  
} 