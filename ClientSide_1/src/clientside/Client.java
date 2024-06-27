
package clientside;

import java.rmi.*;
import java.util.Scanner;
import operation.Operation;




public class Client {

   
    public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
        try {
            Operation P = (Operation) Naming.lookup("rmi://192.168.1.15:1234/calc");
            int x = sc.nextInt();
            int y = sc.nextInt();

            int r = P.sum(x, y);
            int z = P.sub(x, y);
            int m = P.mul(x, y);
            float d = P.div(x, y);

            System.out.println("Sum = " + r);
            System.out.println("Subtraction = " + z);
            System.out.println("Multipication = " + m);
            System.out.println("Division = " + d);

        } catch (Exception e) {
            System.out.println("Unvalid");
        }
        
    }
    
}
