package hello;

import java.util.Scanner;
import java.io.*;

public class Stuff{
	private static boolean gameInProgress = true;
	private static Scanner input = new Scanner(System.in);
	private static String name = "default";
	private static int currentRoom = 0;
	
	Room[] rooms = new Room[200];
	Event[] event = new Event[100];
	Dialogue speech = new Dialogue();
	Inventory inv = new Inventory();
	
	public static int numberOfRooms = 0;
	public static int numberOfEvents = 0;
	
	public void printer(String input){
		System.out.println(input);
	}
	public void prin(String input){
		System.out.print(input);
	}
	//DEBUG
	public boolean debugOn = false;
	public void debug(String input){
		if(debugOn){
			printer(input);
		}
	}
	//Game Loop
	public void gameLoop(){
		initialSetup();
		examine();
		do{
			eventCheck(2, -1);
			nextAction();
		}while(gameInProgress);
		input.close();
	}
	//Game Stuff
	public void drawRoom(){
		String top = "-------------------------";
		String mid = "|                       |";
		String s5 = "                        ";
		String s12 = "         ";
		String s34 = "       ";
		String si = "         ";
		String sp = "         ";
		if(rooms[currentRoom].i != -1){
			si = " (Item)  ";
		}
		if(rooms[currentRoom].p != -1){
			sp = "(Person) ";
		}
		
		if(rooms[currentRoom].c5 != -1){
			prin(s5); printer(top);
			prin(s5); printer(tokenPlate(5));
			prin(s5); printer(findPlate(rooms[rooms[currentRoom].c5].n));
			prin(s5); printer(mid);
			prin(s5); printer(top);
		}
		
		if(rooms[currentRoom].c2 != -1){
			prin(top); prin(namePlate()); printer(top);
			prin(tokenPlate(1)); prin(s12); prin("  o  "); prin(s12); printer(tokenPlate(2));
			prin(findPlate(rooms[rooms[currentRoom].c].n)); prin(s12); prin(" /|\\ "); prin(s12); printer(findPlate(rooms[rooms[currentRoom].c2].n));
			prin(mid); prin(sp); prin(" / \\ "); prin(si); printer(mid);
			prin(top); prin(s12); prin("-----"); prin(s12); printer(top);
		}else{
			prin(top); printer(namePlate());
			prin(tokenPlate(1)); prin(s12); printer("  o  ");
			prin(findPlate(rooms[rooms[currentRoom].c].n)); prin(s12); printer(" /|\\ ");
			prin(mid); prin(sp); prin(" / \\ "); printer(si);
			prin(top); prin(s12); printer("-----");
		}
		
		if(rooms[currentRoom].c4 != -1){
			prin(s34); prin(top); prin(s12); printer(top);
			prin(s34); prin(tokenPlate(3)); prin(s12); printer(tokenPlate(4));
			prin(s34); prin(findPlate(rooms[rooms[currentRoom].c3].n)); prin(s12); printer(findPlate(rooms[rooms[currentRoom].c4].n));
			prin(s34); prin(mid); prin(s12); printer(mid);
			prin(s34); prin(top); prin(s12); printer(top);
		}else if(rooms[currentRoom].c3 != -1){
			prin(s34); printer(top);
			prin(s34); printer(tokenPlate(3));
			prin(s34); printer(findPlate(rooms[rooms[currentRoom].c3].n));
			prin(s34); printer(mid);
			prin(s34); printer(top);
		}else{
			for(int i = 0; i < 5; i++){
				printer("");
			}
		}
	}
	public String findPlate(String rName){
		StringBuilder temp = new StringBuilder();
		temp.append("|");
		for(int i = 0; i < (23 - rName.length()) / 2; i++){
			temp.append(" ");
		}
		temp.append(rName);
		for(int i = 0; i < (23 - rName.length()) / 2; i++){
			temp.append(" ");
		}
		if(rName.length() % 2 == 0){
			temp.append(" ");
		}
		temp.append("|");
		return temp.toString();
	}
	public String namePlate(){
		StringBuilder temp = new StringBuilder();
		for(int i = 0; i < (23 - rooms[currentRoom].n.length()) / 2; i++){
			temp.append(" ");
		}
		temp.append(rooms[currentRoom].n);
		for(int i = 0; i < (23 - rooms[currentRoom].n.length()) / 2; i++){
			temp.append(" ");
		}
		if(rooms[currentRoom].n.length() % 2 == 0){
			temp.append(" ");
		}
		return temp.toString();
	}
	public String tokenPlate(int rNumber){
		StringBuilder temp = new StringBuilder();
		temp.append("|");
		int[] blah = rooms[currentRoom].roomC();
		if(rooms[blah[rNumber - 1]].l != 0){
			temp.append("        Locked:        ");
		}else{
			temp.append("          ");
			temp.append("" + rNumber + ":");
			temp.append("           ");
		}
		temp.append("|");
		return temp.toString();
	}
	public void nextAction(){
		boolean invalidResponce;
		do{
			invalidResponce = false;

			printer("");
			drawRoom();
			printer("");
			
			StringBuilder temp = new StringBuilder();
			temp.append("Location: " + rooms[currentRoom].n);
			if(rooms[currentRoom].p != -1){
				temp.append(" - Person: " + speech.P[rooms[currentRoom].p]);
			}
			if(rooms[currentRoom].i != -1){
				temp.append(" - Item: " + speech.I[rooms[currentRoom].i] + " x" + rooms[currentRoom].q);
			}
			printer(temp.toString());
			
			printer("What would you like to do?");
			printer("(1)talk - (2)walk - (3)Item - (4)Take - (5)Examine - (6)Save and Quit");
			String choice = line();
			switch (choice){
			case "1":
				talk();
				break;
			case "2":
				walk();
				break;
			case "3":
				item();
				break;
			case "4":
				take();
				break;
			case "5":
				examine();
				break;
			case "6":
				saveAndQuit();
				break;
			case "warp":
				if(debugOn){
					warp();
					break;
				}
			default:
				printer("That is not a valid input.");
				invalidResponce = true;
				break;
			}
		}while(invalidResponce);
	}
	//Actions
	public void talk(){
		int counter = rooms[currentRoom].ph;
		int counterEnd = rooms[currentRoom].phe;
		do{
			if(rooms[currentRoom].p == -1){
				printer("There is nobody here.");
				line();
			}else{
				printer("" + speech.P[rooms[currentRoom].p] + ": " + speech.getSpeech(rooms[currentRoom].p, counter));
				line();
				eventCheck(1, counter);
			}
			counter++;
		}while(counter <= counterEnd);
	}
	public void walk(){
		boolean invalidResponce;
		int[] connections = rooms[currentRoom].roomC();
		do{
			invalidResponce = true;
			drawRoom();
			int noc = findConnection();
			String choice = line();
			int choiceInt = choiceTranslater(choice);
			if(choiceInt == -1){
				invalidResponce = false;
			}else if(choiceInt < noc){
				for(int i = 0; i < numberOfRooms; i++){
					if(connections[choiceInt] == i){
						if(rooms[i].l == 0){
							currentRoom = i;
							examine();
						}else if(rooms[i].l == 1 || rooms[i].l == 2){
							printer("This place is locked.");
							line();
						}
						invalidResponce = false;
					}
				}
			}
			if(invalidResponce){
				printer("That is not a valid input.");
			}
		}while(invalidResponce);
	}
	public void item(){
		if(inv.checkOccupiedSpaces() == 0){
			printer("Your inventory is empty.");
			line();
		}else{
			for(int i = 0; i < inv.checkOccupiedSpaces(); i++){
				int temp = i+1;
				printer("(" + temp + ")" + speech.I[inv.getInvName(i)] + " X" + inv.getInvQuantity(i));
			}
			printer("(0)Cancel");
			String choice = line();
			int choiceInt = choiceTranslater(choice);
			if(choiceInt != 10){
				if(choiceInt != -1){
					if(inv.getInvName(choiceInt) != -1){
						printer("You used " + speech.I[inv.getInvName(choiceInt)]);
						line();
						if(inv.getInvName(choiceInt) == 1){
							int connections[] = rooms[currentRoom].roomC();
							for(int i = 0; i < 4; i++){
								if(connections[i] != -1){
									if(rooms[connections[i]].l == 1){
										inv.removeItem(1, 1);
										rooms[connections[i]].l = 0;
										printer("You unlocked " + rooms[connections[i]].n + ".");
										line();
									}else if(rooms[connections[i]].l == 2){
										printer("This key doesn't work on " + rooms[connections[i]].n + ".");
										line();
									}
								}
							}
						}else{eventCheck(3, choiceInt);}
					}else{printer("That is not a valid input.");line();}
				}
			}else{printer("That is not a valid input.");line();} 
		}
	}
	public void take(){
		if(rooms[currentRoom].i == -1){
			printer("There is nothing in this room.");
		}else if(inv.checkSpace(rooms[currentRoom].i) == false){
			printer("You don't have room for this.");
		}else{
			inv.addItem(rooms[currentRoom].i, rooms[currentRoom].q);
			if(rooms[currentRoom].q == 1){
				printer("You picked up 1 " + speech.I[rooms[currentRoom].i] + ".");
			}else{printer("You picked up " + rooms[currentRoom].q + " " + speech.I[rooms[currentRoom].i] + "s.");}
			rooms[currentRoom].i = -1;
			rooms[currentRoom].q = -1;
		}
		line();
	}
	public void examine(){
		printer("You are now in " + rooms[currentRoom].n);
		line();
		printer("" + speech.D[currentRoom]);
		line();
		if(rooms[currentRoom].p != -1){
			printer("" + speech.P[rooms[currentRoom].p] + " is in this place.");
			line();
		}
		if(rooms[currentRoom].i != -1){
			if(rooms[currentRoom].q == 1){
				printer("There is 1 " + speech.I[rooms[currentRoom].i] + " in this room.");
			}else{printer("There are " + rooms[currentRoom].q + " " + speech.I[rooms[currentRoom].i] + "s in this place.");}
			line();
		}
	}
	public void saveAndQuit(){
		try{
			BufferedWriter bw = new BufferedWriter(new FileWriter("save.dat"));
			for(int i = 0; i < numberOfRooms; i++){
				bw.write(""+rooms[i].c);
				bw.newLine();
				bw.write(""+rooms[i].c2);
				bw.newLine();
				bw.write(""+rooms[i].c3);
				bw.newLine();
				bw.write(""+rooms[i].c4);
				bw.newLine();
				bw.write(""+rooms[i].p);
				bw.newLine();
				bw.write(""+rooms[i].ph);
				bw.newLine();
				bw.write(""+rooms[i].phe);
				bw.newLine();
				bw.write(""+rooms[i].i);
				bw.newLine();
				bw.write(""+rooms[i].q);
				bw.newLine();
				bw.write(""+rooms[i].l);
				bw.newLine();
			}
			bw.write(name);
			bw.newLine();
			for(int i = 0; i < numberOfEvents; i++){
				bw.write(""+event[i].a);
				bw.newLine();
				bw.write(""+event[i].ad);
				bw.newLine();
			}
			for(int i = 0; i < 10; i++){
				bw.write(""+inv.getInvName(i));
				bw.newLine();
				bw.write(""+inv.getInvQuantity(i));
				bw.newLine();
			}
			bw.write(""+currentRoom);
			bw.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		printer("Game Saved.");
		line();
		printer("Version 1 (Unfinished)");
		printer("A game made by Ryan.");
		printer("Press enter to quit.");
		input.nextLine();
		gameInProgress = false;
	}
	public void warp(){
		debug("DEBUG: Enter a warp location.");
		String choice = line();
		int choiceInt = -1;
		try{
			choiceInt = Integer.parseInt(choice);
		}catch(Exception e){}
		if(choiceInt >= 0 && choiceInt < numberOfRooms){
			currentRoom = choiceInt;
		}else{
			debug("DEBUG: That is not a valid warp.");
		}
	}
	//Events
	public void eventCheck(int type, int extra){
		for(int i = 0; i < numberOfEvents; i++){
			if(event[i].a){
				if(event[i].t == 1 && type == 1){
					if(rooms[currentRoom].p == event[i].cp && extra == event[i].cph){
						debug("DEBUG: Found an event of type (Dialogue): event[" + i + "]");
						eventDoing(i);
					}
				}else if(event[i].t == 2 && type == 2){
					if(currentRoom == event[i].r){
						debug("DEBUG: Found an event of type (Entering a room): event[" + i + "]");
						eventDoing(i);
					}
				}else if(event[i].t == 3 && type == 3){
					if(event[i].r == currentRoom && event[i].iu == inv.getInvName(extra) && event[i].qu <= inv.getInvQuantity(extra)){
						debug("DEBUG: Found an event of type (Using an item in a specific room): event[" + i + "]");
						eventDoing(i);
					}else if(event[i].r == -1 && event[i].iu == inv.getInvName(extra) && event[i].qu <= inv.getInvQuantity(extra)){
						debug("DEBUG: Found an event of type (Using an item in any room): event[" + i + "]");
						eventDoing(i);
					}
				}
			}
		}
	}
	public void eventDoing(int i){
		for(int k = 0; k-1 < event[i].noo; k++){
			int debugK = k+1;
			if(event[i].t2[k] == 0){
				debug("DEBUG: Executing action #" + debugK + " of event[" + i + "] (Question)");
				boolean invalidResponce = false;
				int counter = -1;
				int end = -1;
				do{
					invalidResponce = false;
					printer("(1)Yes - (2)No");
					String choice = line();
					switch(choice){
					case("1"):
						counter = event[i].p1[k];
						end = event[i].pe1[k];
						break;
					case("2"):
						counter = event[i].p2[k];
						end = event[i].pe2[k];
						break;
					default:
						printer("That is not a valid input.");
						invalidResponce = true;
						break;
					}
				}while(invalidResponce);
				do{
					printer("" + speech.P[rooms[currentRoom].p] + ": " + speech.getSpeech(rooms[currentRoom].p, counter));
					line();
					eventCheck(1, counter);
					counter++;
				}while(counter <= end);
			}else if(event[i].t2[k] == 1){
				debug("DEBUG: Executing action #" + debugK + " of event[" + i + "] (Room Connection)");
				if(event[i].nr[k]){
					rooms[event[i].c1[k]].addConnected(event[i].c2[k]);
					rooms[event[i].c2[k]].addConnected(event[i].c1[k]);
					debug("DEBUG: Rooms " + rooms[event[i].c1[k]].n + " and " + rooms[event[i].c2[k]].n + " are now connected.");
				}else{
					rooms[event[i].c1[k]].removeConnected(event[i].c2[k]);
					rooms[event[i].c2[k]].removeConnected(event[i].c1[k]);
					debug("DEBUG: Rooms " + rooms[event[i].c1[k]].n + " and " + rooms[event[i].c2[k]].n + " are no longer connected.");
				}
			}else if(event[i].t2[k] == 2){
				debug("DEBUG: Executing action #" + debugK + " of event[" + i + "] (Narration)");
				printer(speech.N[event[i].n[k]]);
				line();
			}else if(event[i].t2[k] == 3){
				debug("DEBUG: Executing action #" + debugK + " of event[" + i + "] (Edit Person in room)");
				for(int j = 0; j < numberOfRooms; j++){
					if(event[i].r2[k] == j){
						rooms[j].p = event[i].p[k];
						rooms[j].ph = event[i].ph[k];
						rooms[j].phe = event[i].phe[k];
						if(rooms[j].p != -1){
							debug("DEBUG: " + speech.P[event[i].p[k]] + " is now in " + rooms[event[i].r2[k]].n + " with phases " + event[i].ph[k] + " - " + event[i].phe[k] + ".");
						}else{debug("DEBUG: " + rooms[event[i].r2[k]].n + " now has no one in it.");}
					}
				}
			}else if(event[i].t2[k] == 4){
				debug("DEBUG: Executing action #" + debugK + " of event[" + i + "] (Edit item in room)");
				for(int j = 0; j < numberOfRooms; j++){
					if(event[i].r2[k] == j){
						rooms[j].i = event[i].i[k];
						rooms[j].q = event[i].q[k];
						debug("DEBUG: " + rooms[event[i].r2[k]].n + " now holds " + event[i].q[k] + " " + speech.I[event[i].i[k]] + "(s).");
					}
				}
			}else if(event[i].t2[k] == 5){
				debug("DEBUG: Executing action #" + debugK + " of event[" + i + "] (Edit event active / inactive)");
				event[event[i].e[k]].a = event[i].ea[k];
				event[event[i].e[k]].ad = event[i].ead[k];
				debug("DEBUG: event[" + event[i].e[k] + "] now has an active value of " + event[i].ea[k] + ", and an autodeactivate value of " + event[i].ead[k] + ".");
			}else if(event[i].t2[k] == 6){
				debug("DEBUG: Executing action #" + debugK + " of event[" + i + "] (Edit inventory)");
				if(event[i].g[k]){
					if(inv.checkSpace(event[i].ri[k])){
						inv.addItem(event[i].ri[k], event[i].rq[k]);
						debug("DEBUG: Added " + event[i].rq[k] + " " + speech.I[event[i].ri[k]] + "(s) to inventory.");
					}else{
						debug("DEBUG: ITEM NOT ADDED. NO ROOM.");
					}
				}else{
					inv.removeItem(event[i].ri[k], event[i].rq[k]);
					debug("DEBUG: Removed " + event[i].rq[k] + " " + speech.I[event[i].ri[k]] + "(s) from inventory.");
				}
			}else if(event[i].t2[k] == 7){
				debug("DEBUG: Executing action #" + debugK + " of event[" + i + "] (Edit lock in room)");
				rooms[event[i].r2[k]].l = event[i].l[k];
				debug("DEBUG: " + rooms[event[i].r2[k]].n + "'s lock value is now " + event[i].l[k] + ".");
			}
		}
		if(event[i].ad){
			event[i].a = false;
			debug("DEBUG: event[" + i + "] is now inactive");
		}
	}
	//Initial Play
	public void initialSetup(){
		printer("Press enter to play.");
		line();
		Data data = new Data();
		rooms = data.r();
		event = data.e();
		numberOfRooms = data.numberOfRooms;
		numberOfEvents = data.numberOfEvents;
		boolean invalidResponce;
		do{
			printer("(1)New Game - (2)Load Game");
			invalidResponce = false;
			String choice = line();
			switch(choice){
			case("1"):
				newName();  
				break;
			case("2"):
				load();
				break;
			default:
				printer("Type the number next to the action you want and press enter.");
				printer("");
				invalidResponce = true;
				break;
			}
		}while(invalidResponce);
		speech.construct(name);
	}
	public void newName(){
		boolean invalidResponce;
		String newName;
		do{
			invalidResponce = false;
			printer("What is your name?");
			newName = line();
			if(newName.length() > 16){
				invalidResponce = true;
				printer("Name must be 16 characters or less.");
			}else if(newName.length() < 1){
				invalidResponce = true;
				printer("Name must be at least 1 charater.");
			}
		}while(invalidResponce);
		name = newName;
		switch(name){
		case("debug"):
			debugOn = true;
			printer("DEBUG: Debug turned on.");
		break;
		}
		printer("Your name is now " + name + ".");
		line();
	}
	public void load(){
		if(new File("save.dat").exists()){
			try{
				BufferedReader br = new BufferedReader(new FileReader("save.dat"));
				for(int i = 0; i < numberOfRooms; i++){
					rooms[i].c = Integer.parseInt(br.readLine());
					rooms[i].c2 = Integer.parseInt(br.readLine());
					rooms[i].c3 = Integer.parseInt(br.readLine());
					rooms[i].c4 = Integer.parseInt(br.readLine());
					rooms[i].p = Integer.parseInt(br.readLine());
					rooms[i].ph = Integer.parseInt(br.readLine());
					rooms[i].phe = Integer.parseInt(br.readLine());
					rooms[i].i = Integer.parseInt(br.readLine());
					rooms[i].q = Integer.parseInt(br.readLine());
					rooms[i].l = Integer.parseInt(br.readLine());
				}
				name = br.readLine();
				for(int i = 0; i < numberOfEvents; i++){
					switch(br.readLine()){
					case("true"):
						event[i].a = true;
						break;
					case("false"):
						event[i].a = false;
					}
					switch(br.readLine()){
					case("true"):
						event[i].ad = true;
						break;
					case("false"):
						event[i].ad = false;
					}
				}
				for(int i = 0; i < 10; i++){
					inv.inv[0][i] = Integer.parseInt(br.readLine());
					inv.inv[1][i] = Integer.parseInt(br.readLine());
				}
				currentRoom = Integer.parseInt(br.readLine());
				br.close();
				switch(name){
				case("debug"):
					debugOn = true;
					printer("DEBUG: Debug turned on.");
				break;
				}
				printer("Game loaded. Welcome back " + name + ".");
				line();
			}catch(Exception e){
				printer("Save data out of date or corrupt.\nYou can make a save file so you don't need to restart.\nAsk Ryan to do it.\nPress enter to close the game.");
				line();
				System.exit(0);
			}
		}else{
			printer("No save file found. Starting new game.");
			line();
			newName();
		}
	}
	//Extra
	public int choiceTranslater(String thing){
		switch(thing){
		case("1"):
			return 0;
		case("2"):
			return 1;
		case("3"):
			return 2;
		case("4"):
			return 3;
		case("5"):
			return 4;
		case("6"):
			return 5;
		case("7"):
			return 6;
		case("8"):
			return 7;
		case("9"):
			return 8;
		case("0"):
			return -1;
		default:
			return 10;
		}
	}
	public String line(){
		String results = input.nextLine();
		printer("--------------------------");
		for(int i = 0; i < 50; i++){
			printer("");
		}
		return results;
	}
	//Room things
	public int findConnection(){
		if(rooms[currentRoom].c == -1){
			printer("(0)Cancel");
			return 0;
		}else if(rooms[currentRoom].c2 == -1){
			printer("(1)" + rooms[rooms[currentRoom].c].n + "\n(0)Cancel");
			return 1;
		}else if(rooms[currentRoom].c3 == -1){
			printer("(1)" + rooms[rooms[currentRoom].c].n + "\n(2)" + rooms[rooms[currentRoom].c2].n + "\n(0)Cancel");
			return 2;
		}else if(rooms[currentRoom].c4 == -1){
			printer("(1)" + rooms[rooms[currentRoom].c].n + "\n(2)" + rooms[rooms[currentRoom].c2].n + "\n(3)" + rooms[rooms[currentRoom].c3].n + "\n(0)Cancel");
			return 3;
		}else if(rooms[currentRoom].c5 == -1){
			printer("(1)" + rooms[rooms[currentRoom].c].n + "\n(2)" + rooms[rooms[currentRoom].c2].n + "\n(3)" + rooms[rooms[currentRoom].c3].n + "\n(4)" + rooms[rooms[currentRoom].c4].n + "\n(0)Cancel");
			return 4;
		}else{
			printer("(1)" + rooms[rooms[currentRoom].c].n + "\n(2)" + rooms[rooms[currentRoom].c2].n + "\n(3)" + rooms[rooms[currentRoom].c3].n + "\n(4)" + rooms[rooms[currentRoom].c4].n + "\n(5)"+ rooms[rooms[currentRoom].c5].n + "\n(0)Cancel");
			return 5;
		}
	}
}