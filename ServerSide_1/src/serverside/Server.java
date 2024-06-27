package serverside;

import java.net.InetAddress;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.net.UnknownHostException;
        
public class Server {

  
    public static void main(String[] args) throws RemoteException , UnknownHostException{
        InetAddress ip = InetAddress.getLocalHost();
        System.out.println("ServerIp /" + ip);
        try{
        Registry r = LocateRegistry.createRegistry(1234);
        
        Calculator calculator=new Calculator();
       r.rebind("calc", calculator);
       
                   System.out.println("*Waiting....*");

        }catch(RemoteException e){
        }
    }
    
}
