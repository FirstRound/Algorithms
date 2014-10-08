public class PercolationStats {

  private double []tests;
  
  // perform T independent computational experiments on an N-by-N grid:
  public PercolationStats(int N, int T){
    if (N <= 0 || T <= 0) 
    	throw new IllegalArgumentException();
    tests = new double[T];
    for(int i = 0; i < T; i++){
      Percolation percolation=new Percolation(N);
      int step = 0;
      while(!percolation.percolates()){
        int row=StdRandom.uniform(N)+1;
        int column=StdRandom.uniform(N)+1;
        if(!percolation.isOpen(row,column)){
          percolation.open(row,column);
          step++;
        }
      }
      tests[i] = (1.0*step)/(N*N);
    }
  } 
     // sample standard deviation of percolation threshold:
  public double stddev(){
    return StdStats.stddev(tests);
  } 
     // sample mean of percolation threshold:
  public double mean(){      
    return StdStats.mean(tests);
  }  
     // returns lower bound of the 95% confidence interval:
  public double confidenceLo(){
    return mean()-((1.96*stddev())/Math.sqrt(tests.length)); 
  }       
     // returns upper bound of the 95% confidence interval:
  public double confidenceHi(){
    return mean()+((1.96*stddev())/Math.sqrt(tests.length)); 
  }          
     // test client, described below:
 public static void main(String[] args){

 	/*
   PercolationStats ps=new PercolationStats(Integer.parseInt(args[0]),Integer.parseInt(args[1])); 
   StdOut.print("mean = "+ps.mean()+"\n");
   StdOut.print("std dev = "+ps.stddev()+"\n");
   StdOut.print("95% confidence interval = "+ps.confidenceLo()+", "+ps.confidenceHi());
   */

   WeightedQuickUnionUF a = new WeightedQuickUnionUF(10);
   if(!a.connected(6, 0))
   	a.union(6, 0);

   if(!a.connected(1, 5))
   	a.union(1, 5);

   if(!a.connected(5, 4))
   	a.union(5, 4);

   if(!a.connected(5, 3))
   	a.union(5, 3);

   if(!a.connected(7, 2))
   	a.union(7, 2);

   if(!a.connected(9, 0))
   	a.union(9, 0);

 }   
}