package functionObject;

class PrintString implements Traverser<String>
{
   
   public static void main (String[] args) 
   {
         PrintString g = new PrintString();
         g.visit("Does this really work?");
         g.visit("Apparently");   
   }
   
   public void visit(String s)
   {
      System.out.print( s + " ");
   }
}
