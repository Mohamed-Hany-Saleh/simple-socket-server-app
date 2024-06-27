
package serverside;
import java.rmi.RemoteException;
import operation.Operation;
import java.rmi.server.UnicastRemoteObject;



public class Calculator extends UnicastRemoteObject implements Operation{

    public Calculator()throws RemoteException
    {
        
        
    }

    @Override
    public int sum(int x, int y) throws RemoteException {
        return (x+y);
    }

    @Override
    public int sub(int x, int y) throws RemoteException {
        return (x-y);
    }

    @Override
    public int mul(int x, int y) throws RemoteException {
        return (x*y);
    }

    @Override
    public float div(float x, float y) throws RemoteException {
       return (x/y);
    }
    
}
