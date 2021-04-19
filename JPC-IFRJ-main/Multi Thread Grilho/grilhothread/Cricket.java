package grilhothread;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Cricket{
	public Cricket(String name, int time) {
		this.cricketName = name;
		this.teamID = time;
		this.controller = new Semaphore(3);
	}
	Semaphore controller;
    Team teams[] = null;
	String cricketName;
	int movementDistance;
	Random rand = new Random();
	int currentPosition = 0;
	int jumpCount = 0;
	boolean finalizou = false;
	int teamID = 0;
	boolean first = false;
	
	public synchronized void Jump() {
		try {
			controller.acquire();
			movementDistance = rand.nextInt(70);
			currentPosition += movementDistance;
			jumpCount += 1;
			MainCorrida.teams[teamID].Update(movementDistance);
			System.out.println("O " + cricketName + " pulou " + movementDistance + "cm e já percorreu : " + currentPosition + "cm");
		}
		catch(InterruptedException e1) {
            e1.printStackTrace();
		}
		finally {		
			controller.release();
		}
	}
	public void Winner() {
		first = true;
		MainCorrida.teams[teamID].winner = true;
	}
}
