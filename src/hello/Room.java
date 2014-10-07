package hello;

public class Room {
	public String n;
	public int c = -1;
	public int c2 = -1;
	public int c3 = -1;
	public int c4 = -1;
	public int c5 = -1;
	public int p;
	public int ph;
	public int phe;
	public int i;
	public int q;
	public int l = 0;
	public Room(String roomName, int person, int phase, int phaseEnd, int item, int quantity){
		n = roomName;
		p = person;
		ph = phase;
		phe = phaseEnd;
		i = item;
		q = quantity;
	}
	public Room(String roomName){
		n = roomName;
		p = -1;
		ph = -1;
		phe = -1;
		i = -1;
		q = -1;
	}
	public Room(String roomName, int person, int phase, int phaseEnd){
		n = roomName;
		p = person;
		ph = phase;
		phe = phaseEnd;
		i = -1;
		q = -1;
	}
	public Room(String roomName, int item, int quantity){
		n = roomName;
		p = -1;
		ph = -1;
		phe = -1;
		i = item;
		q = quantity;
	}
	//Set
	public void addConnected(int connected){
		if (c == -1){
			c = connected;
		}else if(c2 == -1){
			c2 = connected;
		}else if(c3 == -1){
			c3 = connected;
		}else if(c4 == -1){
			c4 = connected;
		}else if(c5 == -1){
			c5 = connected;
		}
	}
	public void removeConnected(int connected){
		if (c == connected){
			c = c2;
			c2 = c3;
			c3 = c4;
			c4 = c5;
			c5 = -1;
		}else if (c2 == connected){
			c2 = c3;
			c3 = c4;
			c4 = c5;
			c5 = -1;
		}else if (c3 == connected){
			c3 = c4;
			c4 = c5;
			c5 = -1;
		}else if (c4 == connected){
			c4 = c5;
			c5 = -1;
		}else if (c5 == connected){
			c5 = -1;
		}
	}
	//Get
	public int[] roomC(){
		int[] i = new int[5];
		i[0] = c;
		i[1] = c2;
		i[2] = c3;
		i[3] = c4;
		i[4] = c5;
		return i;
	}
}
