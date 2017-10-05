package Risk.Model;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;


public class RiskGameModel {


        //Game States
        public static final int NEW_GAME = 0;
        public static final int INITIAL_REINFORCE = 1;
        public static final int ACTIVE_TURN = 2;
        public static final int TURN_BONUS = 3;
        public static final int REINFORCE = 4;
        public static final int TRADE_CARDS = 5;
        public static final int START_TURN = 6;
        public static final int ATTACK = 7;
        public static final int ATTACKING = 8;
        public static final int ATTACK_PHASE = 9;
        public static final int BATTLING = 10;
        public static final int CAPTURE = 11;
        public static final int FORTIFY = 12;
        public static final int FORTIFYING = 13;
        public static final int FORTIFY_PHASE = 14;


        
        public static final int GAME_OVER = 99;

	static public Vector<RiskTerritoryModel> territories = new Vector<RiskTerritoryModel>();
	public Vector<RiskContinentModel> continents = new Vector<RiskContinentModel>();
        static public Vector<RiskPlayerModel> players = new Vector<RiskPlayerModel>();
        public Vector<RiskCardModel> deck = new Vector<RiskCardModel>();
        public RiskPlayerModel curPlayer;
        public RiskPlayerModel defender;
        public RiskPlayerModel active;

        static private int gameState;
        public RiskTerritoryModel aTerritory;
        public RiskTerritoryModel dTerritory;
        public int defenseNum = 0;
        public int attackNum = 0;
        public int iter = 0;
        public boolean drawn;


      public RiskGameModel(){

                //Setup Board
                gameState = NEW_GAME;
                initalPlayer();
		        loadMap();
                initializeDeck();
                distubuteArmies();

             
	}

      static public boolean addPlayer(String nm){
            int size = players.size();
            if (size > 6)
                return false;
            RiskPlayerModel p = new RiskPlayerModel(nm, size);
            players.add(p);
                return true;
        }

      public void initalPlayer(){
           curPlayer = players.elementAt(0);
       }

      public void nextPlayer(){
           if (curPlayer == players.lastElement()){
               curPlayer = players.elementAt(0);
                iter = 0;
           }
           else
               curPlayer = players.elementAt(++iter);
       }

      public void removePlayer(RiskPlayerModel p){
          players.remove(p);
          players.trimToSize();
          iter--;
          
      }

      public void distubuteArmies(){
            int numOfPlayers = players.size();
            int armies = 0;

            if(numOfPlayers == 3)
                armies = 35;// 35;
            if(numOfPlayers == 4)
                armies = 30; //temp 30
            if(numOfPlayers == 5)
                armies = 25;
            if(numOfPlayers == 6)
                armies = 20;

            for(int i = 0; i < numOfPlayers; i++)
                players.elementAt(i).addArmies(armies);
        }

      public void initializeDeck(){
         for(int i = 0; i < territories.size(); i++)
            deck.add(new RiskCardModel(i%3,i));
      }

      public void drawCard(RiskPlayerModel p){
            Random draw = new Random();
            System.out.println(deck.size());
            int card = draw.nextInt(deck.size());

            RiskCardModel c = deck.elementAt(card);
            deck.remove(deck.elementAt(card));
            deck.trimToSize();
            p.setCard(c);
      }

      public int turnBonus(){
          int bonus = 0;
          bonus += collectReinforcements();
          System.out.println("Bonus " + bonus);
          bonus += collectReinforcementsFromContinent();

          
          return bonus;
      }

      public int collectReinforcements(){
         // count how many territories owned by curPlayer
         int territoryAmount = curPlayer.numOfTerritories();
         double bonus;
         if(territoryAmount < 9)
            bonus = 3;
         else
           bonus = Math.floor(territoryAmount / 3);
         return (int) bonus;
      }

      public int collectReinforcementsFromContinent(){
        int continentBonus = 0;
	    int numOfCont = continents.size();
        // # of continents = 6
        for (int i = 0; i < numOfCont; i++){
	    boolean captured = continents.elementAt(i).isContinentCaptured(curPlayer); 
        if (captured)
            continentBonus += continents.elementAt(i).getValue();
            System.out.println("Bonus "+ continentBonus + " for " + continents.elementAt(i).getName());

        }
	return continentBonus;
        }

      public void loadMap() {
		
		boolean done = false;
		String next;
		String name;
		int value;
		int id;
		int continent;
                int x;
                int y;
		Vector<Integer> adjacents;
		Vector<Integer> contains;
                InputStream file = RiskGameModel.class.getResourceAsStream("resources/mapfile-new.txt");
		//File file = new File("mapfile.txt");
		
		
		//try{
			Scanner mapfile = new Scanner(file);
			
			while (mapfile.hasNextLine()){
				done = false;
				next = mapfile.nextLine();
				
				if(next.equals("{continents}")){
					next = mapfile.next();
					
					do{	
						value = Integer.parseInt(next);
						name = mapfile.next().replace("_", " ");
						contains = new Vector<Integer>();
						next = mapfile.next();
						while(!next.equals(";")){
							contains.add(Integer.parseInt(next));
							next = mapfile.next();
						}
						
						continents.add(new RiskContinentModel(name,contains,value));
						next = mapfile.next();
						
						if (next.equals(";;")) done = true;
					}while(done == false);
				} //end if continents
				
				if(next.equals("{countries}")){
					next = mapfile.next();
					
					do{	
						id = Integer.parseInt(next);
						name = mapfile.next().replace("_", " ");
						continent = Integer.parseInt(mapfile.next());
                                                x = Integer.parseInt(mapfile.next());
                                                y = Integer.parseInt(mapfile.next());
						
						territories.add(new RiskTerritoryModel(id,name,continent,x,y));
						//System.out.println(id + " " + name + " " + continent);
						next = mapfile.next();
						if (next.equals(";;")) done = true;
							
					}while(done == false);
						
				} //end if countries
					
				if(next.equals("{adjacents}")){
					String c = mapfile.next();
					do{	
						adjacents = new Vector<Integer>();
                                                next = mapfile.next();
						while(!next.equals(";")){
                                                    adjacents.add(Integer.parseInt(next));
                                                    next = mapfile.next();
						}
                                                territories.elementAt(Integer.parseInt(c)-1).setAdjacent(adjacents);
						c = mapfile.next();
						if (c.equals(";;")) done = true;
					}while(done == false);
						
				} //end if adjacents
				
			}//end while
		
		
		
		
		//} catch(FileNotFoundException e){e.printStackTrace();} //end try read file

	}//end loadmap

      public void gamePhaseSetup(int x, int y){

          int country = getMapLocation(x,y);
          int i = 0; //num of occupied countries
          

          if(getState() == INITIAL_REINFORCE){
              if(country != -1){
                if(getOwnership(country) == curPlayer.getPlayerIndex()){
                    occupyTerritory(territories.elementAt(country));
                    nextPlayer();
                }
              }

              for(int c = 0; c < players.size(); c++){
                 if (players.elementAt(c).getNumberOfArmies() == 0)
                   i++;
                }

                if (i == players.size()){
                    setState(START_TURN);
                }



          } //end if INITIAL_REINFORCE

          if (getState() == NEW_GAME){
           int num = numOfTerroitories();

           if(country != -1){ //if a country was clicked on
              if(getOwnership(country) == -1) //if country not owned
                    initialOccupyTerritories(country); //current player now owns
                //How many countries are already owned?
                for(int c = 0; c < num; c++){
                     if (getOwnership(c) != -1) //if country owned
                        i++; //count number of owned countries
                } //end for loop
           } //end if country clicked on

           if (i == num){
              setState(INITIAL_REINFORCE);
           }
        } //end if NEW_GAME

      }

      public String gamePhaseActive(int x, int y){

          int country = getMapLocation(x,y);


          if(getState() == FORTIFYING){
              if(country != -1){//not a country
                dTerritory = territories.elementAt(country); //move to territory
                if(getOwnership(country) == curPlayer.getPlayerIndex())
                      if(aTerritory.isAdjacent(dTerritory)){//if its adjacent...
                          setState(FORTIFY_PHASE);
                      }
              }//end if a county
          }//end fortifying

          if(getState() == FORTIFY){
              if(country != -1){
                    if(getOwnership(country) == curPlayer.getPlayerIndex()){
                        setState(FORTIFYING);
                        aTerritory = territories.elementAt(country);
                    }
              }

          }//end forty

          if(getState() == ATTACK_PHASE){
           
           //   engageBattle();

          }

          if(getState() == ATTACKING){
              if(country != -1){//not a country
                RiskTerritoryModel d = territories.elementAt(country); //defending territory
                if(getOwnership(country) == curPlayer.getPlayerIndex())
                        return "You own that territory.";
                      if(aTerritory.isAdjacent(d)){//if its adjacent...
                          setState(ATTACK_PHASE);
                          dTerritory = d;
                          defender = d.getPlayer();
                      }
                      else //if its not adjacent
                          return "That territory is not adjacent, try again.";
              }

          }

          if(getState() == ATTACK){
            if (country != -1)//if not a country
                if(getOwnership(country) == curPlayer.getPlayerIndex()){
                    if(territories.elementAt(country).getArmies() < 2)
                        return "Not enough armies to battle, need at least 2";
                    else{
                        setState(ATTACKING);
                        aTerritory = territories.elementAt(country);
                    }
                }//end is curPlayers country
          } //end attack with

          if(getState() == TRADE_CARDS){





          }

          if(getState() == REINFORCE){
             if(country != -1) //if not a country
               if(getOwnership(country) == curPlayer.getPlayerIndex()) //if owned
                  occupyTerritory(territories.elementAt(country)); //occupy

          }

          if(getState() == START_TURN){

             curPlayer.addArmies(turnBonus()); //recive turn bonus
             if(curPlayer.getCard().size() > 5)
                setState(TRADE_CARDS);
             else
               setState(REINFORCE);
          }



           
           return "";
      }
      
      /**
       * Contains the logic who wins or loses in the battle including the random 
       * dice logic
       *
       * @param dTerritory  defense territory
       * @param aTerritory  attacker territory
       * @param att value of each roll of attacker
       * @param def value of each roll of defender
       */     
      public void engageBattle(){

           
           Integer[] att = new Integer[attackNum];
           Integer[] def = new Integer[defenseNum];
           Random attDice = new Random();


            // get value for each roll
            for (int i = 0; i < attackNum; i++)
                att[i] = attDice.nextInt(6) + 1;
           for (int i = 0; i < defenseNum; i++)
               def[i] = attDice.nextInt(6) + 1;
             Arrays.sort(att, Collections.reverseOrder());
             Arrays.sort(def,Collections.reverseOrder());


            if(attackNum == 1){
               System.out.println(att[0] + " vs " +def[0]);
                if(att[0] > def[0])
                    dTerritory.looseArmy();
                else
                    aTerritory.looseArmy();
            }
             if(attackNum > 1){ //attacking with more than 1
               System.out.println(att[0] + " vs " +def[0]);
                 if(att[0] > def[0])
                     dTerritory.looseArmy();
                 else
                     aTerritory.looseArmy();
                 if(defenseNum == 2){
                    System.out.print(att[1] + " vs " +def[1]);
                     if(att[1] > def[1])
                         dTerritory.looseArmy();
                     else
                         aTerritory.looseArmy();
                 }//if defneding with two
             }

             if(dTerritory.getArmies() == 0){
                 setState(CAPTURE);
                 dTerritory.setPlayer(curPlayer);
             }
             if(aTerritory.getArmies() == 0)
                 setState(ACTIVE_TURN);

             active = curPlayer;




      }

      public void capture(){
          int armies = defenseNum;
          RiskTerritoryModel d = dTerritory;
          RiskTerritoryModel a = aTerritory;
          defender.looseTerritory(d);
          active.occupyTerritory(d);

          if(defender.getOccupiedTerritories().size() == 0){
              System.out.println(defender.getName()+" lost the game.");
              removePlayer(defender);
              if(players.size() == 1){
                  System.out.print(active.getName()+" has won the game");
              }
          }


          a.looseArmies(armies);
          d.addArmies(armies);

          //Draw a card
          if(drawn == false){
            drawCard(curPlayer);
            System.out.println("RiskCard "+getCountryName(curPlayer.getCard().firstElement().territory));
            drawn = true;
          }





          setState(ACTIVE_TURN);
          //Reset battle variables
          defenseNum = 0;
          attackNum = 0;
          dTerritory = null;
          aTerritory = null;


      }

      public int[] drawMap(int i){
            int out[] = new int[2];
            out[0] = territories.elementAt(i).getX();
            out[1] = territories.elementAt(i).getY();
            return out;
        }

      public int[] fillDrawMap(int i, int p){
          int loc[] = new int[2];
          if (territories.elementAt(i).getPlayer().getPlayerIndex() == p)
              loc = drawMap(i);
          return loc;
      }

      public int getOwnership(int i){
                return territories.elementAt(i).getPlayer().getPlayerIndex();
         
      }

      public int numOfTerroitories(){
            int num = territories.size();
            return num;
        }

      public int getMapLocation(int x, int y){
          int x1;
          int y1;
          int size = 30;
          for (int i = 0; i < territories.size(); i++){
                x1 = territories.elementAt(i).getX();
                y1 = territories.elementAt(i).getY();
              if (Math.abs(x1 - x) <= size || Math.abs(x1 - x) <= size ){
                  if (Math.abs(y1 - y) <= size || Math.abs(y1 - y) <= size){
                        return i;
                  } //end if y
              }//end if x
          } //end for
          return -1;

      }

      public boolean occupyTerritory(RiskTerritoryModel t){
        //Make sure there are availble armies
        if(curPlayer.getNumberOfArmies() > 0)
        { //Checks if the territory is occupied by the current player.
              if(t.getPlayer() == curPlayer){
                    t.setPlayer(curPlayer);
                    t.addArmies(1);
                    curPlayer.looseArmy();
                    return true;
                } //end if
        } //end if availble
        else
          System.out.println(curPlayer.getName() + " has no more Armies.");
        return false;
      } //end occupyTerritory

      public void initialOccupyTerritories(int id){


          if(curPlayer.getNumberOfArmies() > 0){
            RiskTerritoryModel t = territories.elementAt(id);
            t.setPlayer(curPlayer);
            curPlayer.occupyTerritory(t);
            t.addArmy();
            curPlayer.looseArmy();
            nextPlayer();
          }
          else
            System.out.println(curPlayer.getName() + " has no more Armies.");


      }

      public String getCountryName(int id){
              if (id == -1)
                  return "Nowhere";
              return territories.elementAt(id).getName();
      }

      public String getPlayerName(int id){
          return territories.elementAt(id).getPlayer().getName();
      }

      public RiskPlayerModel getCurrentPlayer(){
          return curPlayer;
      }

      public Vector<RiskPlayerModel> getPlayers(){
          return players;
      }

      public RiskTerritoryModel getTerritoryAt(int i){
         if(i > 0)
            return territories.elementAt(i);
         return null;
      }

      public int numOfArmiesOnTerritory(int i){
          return territories.elementAt(i).getArmies();
      }

     public int getState(){
         return gameState;
     }

     public void setState(int state){
         gameState = state;
     }

    public void setAttack(int num) {
        attackNum = num;
    }

    public void setDefend(int num){
        defenseNum = num;
    }

}