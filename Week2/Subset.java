public class Subset {  
    public static void main(String[] args){  
        RandomizedQueue<String> queue = new RandomizedQueue<String>();  
        int count = Integer.valueOf(args[0]);  
        while (!StdIn.isEmpty()) {  
            String str = StdIn.readString();  
            queue.enqueue(str);  
        }  
        while (count > 0){  
            StdOut.println(queue.dequeue());  
            count--;  
        }  
    }  
}  