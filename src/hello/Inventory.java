package hello;

public class Inventory {
	public int[][] inv;
	
	public int bagSpace = 10;
	
	public Inventory(){
		inv = new int[2][bagSpace];
		for(int i = 0; i < bagSpace; i++){
			inv[0][i] = -1;
			inv[1][i] = 0;
		}
	}
	public boolean checkSpace(int ID){
		boolean results = false;
		boolean full = false;
		for(int i = 0; i < bagSpace; i++){
			if(inv[0][i] == ID){
				if(inv[1][i] <= 98){
					results = true;
				}
				full = true;
			}
		}
		if(inv[0][9] == -1 && full != true){
			results = true;
		}
		return results;
	}
	public void addItem(int ID, int quan){
		for(int i = 0; i < bagSpace; i++){
			if(inv[0][i] == ID){
				inv[1][i] += quan;
				if(inv[1][i] > 99){
					inv[1][i] = 99;
				}
				break;
			}else if(inv[0][i] == -1){
				inv[0][i] = ID;
				inv[1][i] = quan;
				break;
			}
		}
	}
	public void removeItem(int ID, int quan){
		for(int i = 0; i < bagSpace; i++){
			if(inv[0][i] == ID){
				inv[1][i] -= quan;
				if(inv[1][i] <= 0){
					for(int j = i; j < 9; j++){
						inv[0][j] = inv[0][j+1];
						inv[1][j] = inv[1][j+1];
					}
					inv[0][9] = -1;
					inv[1][9] = -1;
				}
			}
		}
	}
	public boolean checkForItem(int ID, int quan){
		boolean results = false;
		for(int i = 0; i < bagSpace; i++){
			if(inv[0][i] == ID){
				if(inv[1][i] >= quan){
					results = true;
				}
			}
		}
		return results;
	}
	public int checkOccupiedSpaces(){
		int results = 9;
		for(int i = 0; i < bagSpace; i++){
			if(inv[0][i] == -1){
				results = i;
				break;
			}
		}
		return results;
	}
	public int getInvName(int slot){
		return inv[0][slot];
	}
	public int getInvQuantity(int slot){
		return inv[1][slot];
	}
}
