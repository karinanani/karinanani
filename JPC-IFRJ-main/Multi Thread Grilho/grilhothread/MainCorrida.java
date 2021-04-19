package grilhothread;

import java.util.Scanner;

public class MainCorrida {

    static int cricketsThatFinished = 0;
    static int finishLine = 200;
	public static Team[] teams;
    static int teamCount = 0;
    static boolean winnerDeclared = false;
    static int finishPlace = 1;
    
	public static void main(String[] args) {
        int cricketCount = 0;
        int desiredCricketCountbyTeam = 2;
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quantos grilos se juntarão a essa corrida? ");  
        cricketCount = scanner.nextInt();
        System.out.println("Grilos na corrida " + cricketCount);
        System.out.println("Qual será a distância da corrida? ");
        
        
        finishLine = scanner.nextInt();

        ThreadController threads[] = null;
        Cricket[] cricketMain = new Cricket[cricketCount];
        scanner.close();
        

        if(cricketCount % desiredCricketCountbyTeam == 0) {
        	teamCount = (int)(cricketCount / desiredCricketCountbyTeam);
        }
        else {
        	teamCount = (int) Math.ceil(cricketCount / desiredCricketCountbyTeam); 
        }

        teams = new Team[teamCount];      
        
		System.out.println("Times : " + teamCount );
		
        for(int i = 0; i < cricketCount; i++) {
        	
        	cricketMain[i] = new Cricket("Grilo_" + (i+1),  i % teamCount);;
        	
        }
        for(int i = 0; i < teamCount; i++) {
        	
        teams[i] = new Team(i);
        
        }
        IniciarCorrida(cricketCount, threads, cricketMain);	

	}
	
    public static void IniciarCorrida(int cricketsCount, ThreadController[] thread, Cricket[] cricket) {
    	thread = new ThreadController[cricketsCount];
    	
    	for(int i = 0; i < cricketsCount; i++) 
    	{
    		thread[i] = new ThreadController(cricket[i]);
    		thread[i].start();
    	}
        for (int i = 0; i < thread.length; i++) 
        {
          try 
          {
            thread[i].join();
          } 
          catch (InterruptedException e ) 
          { 	
              e.printStackTrace();
          }
        }
        
    	while(cricketsThatFinished < cricketsCount) {
    	Race(cricketsCount, thread, cricket);    	
    	}
		for(int i = 0; i < teamCount; i++) 
		{
			teams[i].Finish();
		}
		for(int i = 0; i < teamCount; i++) 
		{
			teams[i].ShowFinalWinner();
			
		}
    }

    public static void Race(int cricketsCount, ThreadController[] thread, Cricket[] cricket) {
    	for(int i = 0; i < cricketsCount; i++) 
        {
    		if(cricket[i].finalizou != true)
    		{
    			if(cricket[i].currentPosition >= finishLine) 
    			{	
    			cricketsThatFinished ++;
    			System.out.println("O " + cricket[i].cricketName +" foi o "+ finishPlace +"º colocado com " + cricket[i].jumpCount + " pulos.");
    			finishPlace++;
    			cricket[i].finalizou = true;
    			if(!winnerDeclared) {
    				cricket[i].Winner();
    				winnerDeclared = true;
    				}
    			}
    			else 
    			{
    				thread[i].run();
        		}
    		}
        }
            for (int i = 0; i < thread.length; i++) 
            {
              try 
              {
                thread[i].join();
              } 
              catch (InterruptedException e ) 
              {
                  e.printStackTrace();
              }
            }
    }
}