package rhw;

import org.rosuda.JRI.Rengine;

public class HelloRWorld {
   static Rengine re; // initialized in constructor or autowired

   public static void main (String[] args)
   {
       // new R-engine
       Rengine re=new Rengine (new String [] {"--vanilla"}, false, null);
       if (!re.waitForR())
       {
           System.out.println ("Cannot load R");
           return;
       }
       
       // print a random number from uniform distribution
       System.out.println (re.eval ("runif(1)").asDouble ());
       
       // done...
       re.end();
   }
}
