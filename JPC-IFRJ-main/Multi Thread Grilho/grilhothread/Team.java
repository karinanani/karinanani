package grilhothread;

import java.util.concurrent.Semaphore;

public class Team {   
	Semaphore mutex;
	public Team(int id) {
	this.id = id;
	this.mutex = new Semaphore(1);
	}
	
	int id = 0;
    boolean winner = false;
    int totalJumps = 0;
    int totalTraversedDistance;
  

	public synchronized void Update(int distance) {
		try {
		mutex.acquire();
		totalJumps += 1;
		totalTraversedDistance += distance;
		}
		catch(InterruptedException e1) {
            e1.printStackTrace();
		}
		finally {
		mutex.release();
		}
	}
	public void Finish() {

		System.out.println("Time " + (id+1) + " : Total de pulos: " + totalJumps + " Distância Percorrida: " + totalTraversedDistance + "cm");
		
	}
	public void ShowFinalWinner() {
		
		if(winner) {
			System.out.println("Time " + (id+1) + " foi o vencedor");

			System.out.println("Fim da Corrida!");
		}
	}
}