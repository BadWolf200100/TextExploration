package hello;

public class Dialogue {
	public String[][] G;
	public String[] D;
	public String[] N;
	public String[] P;
	public String[] I;
	public void construct(String n){
		G = new String[100][20];
		//Tristan
		G[0][0] = "Hello, " + n + ".";
		G[0][1] = "Go talk to Boz Man.";
		G[0][2] = "I SAID GO!.";
		//Boz Man
		G[1][0] = "Tristan didn't ask you to talk to me.";
		G[1][1] = "Tristan did ask you to talk to me. Go talk to him again.";
		
		
		D = new String[200];
		//Ryan's House
		D[0] = "It's a place.";
		D[1] = "Pretty cool in here.";
		D[2] = "Lot's of fun.";
		D[3] = "Wowie.";
		D[4] = "Dang.";
		D[5] = "Smells yum.";
		
		
		N = new String[100];
		N[0] = "Welcome to the game.";
		N[1] = "The world has gone mad.";
		N[2] = "The whole place caught on fire.";
		
		
		
		P = new String[30];
		//Ryan's House
		P[0] = "Tristan";
		P[1] = "Boz Man";
		

		I = new String[20];
		I[0] = "dollar";
		I[1] = "key";
		I[2] = "thing";
		
	}
	public String getSpeech(int person, int phase){
		if(phase != -1){
			return G[person][phase];
		}else{return "There is no one here.";}
	}
}