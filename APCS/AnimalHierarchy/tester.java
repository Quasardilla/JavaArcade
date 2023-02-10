package AnimalHierarchy;

import java.util.ArrayList;

public class tester {
    public static void main(String[] args) 
    { 
       Dog d = new Dog(); 
       System.out.println(d); 
       Bird b = new Bird(); 
       System.out.println(b); 
       System.out.println(); 
  
       Careable d1 = new Dog("Sparky"); 
       Careable d2 = new Dog("Fido", "04-01-12", 42); 
       Careable b1 = new Bird("Tweety"); 
       Careable b2 = new Bird("Sam the Eagle", "07-04-02", 15, false); 
        
       ArrayList<Careable> myPets = new ArrayList<Careable>(); 
       myPets.add(d1);myPets.add(d2);myPets.add(b1);myPets.add(b2); 
        
       for(Careable c : myPets) 
          System.out.println(c); 
       System.out.println(); 
        
       System.out.println("Feeding Time:"); 
       for(Careable c : myPets) 
          c.feed(); 
        
       System.out.println(); 
       System.out.println("Grooming Time:"); 
       for(Careable c : myPets) 
          c.groom(); 
        
       System.out.println(); 
       System.out.println("Check-up Time:"); 
       for(Careable c : myPets) 
          c.checkUp(); 
    } 
}
