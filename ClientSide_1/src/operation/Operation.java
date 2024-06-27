package operation;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Operation extends Remote {
    public int sum(int x,int y)throws RemoteException;
    public int sub(int x,int y)throws RemoteException;
    public int mul(int x,int y)throws RemoteException;
    public float div(float x,float y)throws RemoteException;
}
