
public class Percolation {
  private WeightedQuickUnionUF unioncontroller;
  private WeightedQuickUnionUF helpcontroller;
  private int                  N;
  // FALSE is closed and TRUE is open:
  private boolean              status[];
  private int                  topindex;
  private int                  bottomindex;

// BEGIN PUBLIC METHODS
// create N-by-N grid, with all sites blocked:
     public Percolation(int n){
       if (n <= 0) 
          throw new IllegalArgumentException();
       N = n;
       topindex = N * N;
       bottomindex = topindex + 1;
        //+2 are for additional top and bottom items:
       unioncontroller = new WeightedQuickUnionUF(topindex + 2); 
       helpcontroller =  new WeightedQuickUnionUF(topindex + 2);    
       status = new boolean[topindex + 2];
       status[topindex] = status[bottomindex] = true;
       for(int i = 0; i < (topindex); i++){
           status[i] = false;
       }            
     }  
   // open site (row i, column j) if it is not already: 
     public void open(int i, int j){ 
       checkRange(i, j);
       if(isOpen(i, j))
       	return;       
       int item = getIndex(i, j);
       status[item] = true;
       //if not top row
       if(i != 1 && isOpen(i - 1, j)){
          union(getIndex(i - 1, j), item);
       } else if(i == 1) {
          //connect to virtual top item
         union(item, topindex);
       }
       //if not bottom row
       if(i != N && isOpen(i + 1, j)){       
         union(getIndex(i + 1, j), item);
       } else if (i == N) {
          //connect to virtual bottom item
          union(item, bottomindex);
       }
       //if not left border
       if(j != 1 && isOpen(i, j - 1)){
         union(getIndex(i, j - 1), item);
       }
       //if not right border
        if(j != N && isOpen(i, j + 1)){
         union(getIndex(i, j + 1), item);
       }
     }

     // is site (row i, column j) open:
     public boolean isOpen(int i, int j){
       checkRange(i, j);
       return (status[getIndex(i, j)]);
     }   
     
      // is site (row i, column j) full:
     public boolean isFull(int i, int j){
       checkRange(i, j);
       return (unioncontroller.connected(topindex, getIndex(i, j)) && helpcontroller.connected(topindex, getIndex(i, j)));
     }   
     
      // does the system percolate:
     public boolean percolates(){
       return unioncontroller.connected(topindex, bottomindex);
     } 
// END PUBLIC METHODS

// BEGIN PRIVATE METHODS   
 
    private int getIndex(int i, int j){
       return ((N*(i-1))+j-1);
     }

     private void checkRange(int i, int j){
       if (i <= 0 || j<=0 || i > N || j > N)
       	  throw new IndexOutOfBoundsException();
     }
     
     private void union(int a, int b){
         if (!unioncontroller.connected(a, b)){
           unioncontroller.union(a, b);
         }
         if (!helpcontroller.connected(a, b) && b != bottomindex){
         	helpcontroller.union(a, b);
         }
     }       
 
// END PRIVATE METHODS
}