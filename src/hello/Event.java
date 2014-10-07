package hello;

public class Event {
	public int t = -1;
	public boolean a;
	public boolean ad;
	
	public int cp;
	public int cph;
	
	public int r;
	
	public int iu;
	public int qu;
	
	//
	public int[] t2 = new int[5];
	public int noo = -1;
	
	public int[] r2;
	
	public int[] p1;
	public int[] pe1;
	public int[] p2;
	public int[] pe2;
	
	public int[] c1;
	public int[] c2;
	public boolean[] nr;
	
	public int[] n;
	
	public int[] p;
	public int[] ph;
	public int[] phe;
	
	public int[] i;
	public int[] q;
	
	public int[] e;
	public boolean[] ea;
	public boolean[] ead;
	
	public int[] ri;
	public int[] rq;
	public boolean[] g;
	
	public int[] l;
	
	public Event(){//Special
		t = 0;
	}
	public Event(boolean active, boolean autoDeactivate, int person, int phase){//Dialogue
		t = 1;
		a = active;
		ad = autoDeactivate;
		cp = person;
		cph = phase;
	}
	public Event(boolean active, boolean autoDeactivate, int room){//Entering a room
		t = 2;
		a = active;
		ad = autoDeactivate;
		r = room;
	}
	public Event(boolean active, boolean autoDeactivate, int room, int item, int quantity){//Using an item
		t = 3;
		a = active;
		ad = autoDeactivate;
		r = room;
		iu = item;
		qu = quantity;
	}
	//Outcome
	public void setQ(int phase1, int phaseEnd1, int phase2, int phaseEnd2){//Dialogue Question
		if(noo == -1){first();}
		noo++;
		t2[noo] = 0;
		p1[noo] = phase1;
		pe1[noo] = phaseEnd1;
		p2[noo] = phase2;
		pe2[noo] = phaseEnd2;
	}
	public void setRC(int connection1, int connection2, boolean noRemove){//Room Connection
		if(noo == -1){first();}
		noo++;
		t2[noo] = 1;
		c1[noo] = connection1;
		c2[noo] = connection2;
		nr[noo] = noRemove;
	}
	public void setN(int narration){//Narrate
		if(noo == -1){first();}
		noo++;
		t2[noo] = 2;
		n[noo] = narration;
	}
	public void setRP(int room, int person, int phase, int phaseEnd){//Edit Rooms (person)
		if(noo == -1){first();}
		noo++;
		t2[noo] = 3;
		r2[noo] = room;
		p[noo] = person;
		ph[noo] = phase;
		phe[noo] = phaseEnd;
	}
	public void setRI(int room, int item, int quan){//Edit Rooms (item)
		if(noo == -1){first();}
		noo++;
		t2[noo] = 4;
		r2[noo]= room;
		i[noo] = item;
		q[noo] = quan;
	}
	public void setE(int ID, boolean active, boolean autoDeactivate){//Edit Events (active)
		if(noo == -1){first();}
		noo++;
		t2[noo] = 5;
		e[noo] = ID;
		ea[noo] = active;
		ead[noo] = autoDeactivate;
	}
	public void setI(int removedItem, int removedQuantity, boolean give){//Edit Inventory
		if(noo == -1){first();}
		noo++;
		t2[noo] = 6;
		ri[noo] = removedItem;
		rq[noo] = removedQuantity;
		g[noo] = give;
	}
	public void setRL(int room, int locked){
		if(noo == -1){first();}
		noo++;
		t2[noo] = 7;
		r2[noo] = room;
		l[noo] = locked;
	}
	public void first(){
		r2 = new int[5];
		p1 = new int[5];
		pe1 = new int[5];
		p2 = new int[5];
		pe2 = new int[5];
		c1 = new int[5];
		c2 = new int[5];
		nr = new boolean[5];
		n = new int[5];
		p = new int[5];
		ph = new int[5];
		phe = new int[5];
		i = new int[5];
		q = new int[5];
		e = new int[5];
		ea = new boolean[5];
		ead = new boolean[5];
		ri = new int[5];
		rq = new int[5];
		g = new boolean[5];
		l = new int[5];
	}
}
