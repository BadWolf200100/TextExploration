package hello;

public class Data {
	Room[] rooms = new Room[200];
	Event[] event = new Event[100];
	int numberOfRooms = 6;
	int numberOfEvents = 3;
	public Room[] r(){
		
		//House
		rooms[0] = new Room("Basement");
		rooms[1] = new Room("Kitchen", 1, 1);
		rooms[2] = new Room("Bedroom", 1, 0, 0);
		rooms[3] = new Room("Hallway", 0, 0, 1);
		rooms[4] = new Room("Bathroom", 2, 1);
		rooms[5] = new Room("Outside");
		rooms[5].l = 1;
		
		
		//Connections
		//House
		
		addC(0, 1, true);
		addC(1, 3, true);
		addC(3, 2, true);
		addC(3, 4, true);
		addC(2, 5, true);
		
		
		return rooms;
	}
	public Event[] e(){
		//Ryan's House
		event[0] = new Event(true, true, 0);
		event[0].setN(0);
		event[1] = new Event(true, false, 0, 1);
		event[1].setRP(2, 1, 1, 1);
		event[1].setRP(3, 0, 2, 2);
		event[2] = new Event(true, false, 1, 1);
		event[2].setRP(2, 1, 0, 0);
		event[2].setRP(3, 0, 0, 1);
		/*event[3] = new Event(true, true, );
		event[3].set();
		event[4] = new Event(true, true, );
		event[4].set();
		event[5] = new Event(true, true, );
		event[5].set();*/
		
		
		return event;
	}
	public void addC(int c1, int c2, boolean noRemove){
		if(noRemove){
			rooms[c1].addConnected(c2);
			rooms[c2].addConnected(c1);
		}else{
			rooms[c1].removeConnected(c2);
			rooms[c2].removeConnected(c1);
		}
	}
}