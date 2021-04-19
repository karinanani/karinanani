package grilhothread;

public class ThreadController extends Thread {
	Cricket cricket;

    public ThreadController(Cricket cricket) 
    {
    	this.cricket = cricket;
    }

    @Override
    public void run() 
    {	
    	cricket.Jump();
    }
}
