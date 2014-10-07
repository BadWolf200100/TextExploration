package hello;
/*
 * INFO
 * Project started: July 19 2014 2:00pm
 * 90ish lines in "world". Basic stuff.
 * 
 * July 22 2014 10:00pm
 * Added  "rooms". 90ish lines in "rooms". A 50ish more in "world". Moved "world" to "stuff".
 * 
 * * = new class
 * July 25 2014 1:00am
 * World:7(Without Comments) Stuff:209 Room:122 *Dialogue:55 *Inventory:74
 * 467 total lines.
 * 
 * July 25 2014 11:41pm
 * World:Unchanged Stuff:256 Room:Unchanged Dialogue:71 Inventory:105
 * Exported PreVersion 1. Added lots of rooms and things for testing. Some things go unused.
 * +74 total lines. 541 total.
 * 
 * July 29 2014 3:06am
 * World: Unchanged Stuff:382 Room:Unchanged Dialogue:88 Inventory:108 *Event:93
 * +259 total lines. 800 total.
 * 
 * July 30 2014 12:00am
 * World: Unchanged Stuff: 447 Room:89(-33) Dialogue: 92 Inventory: Unchanged Event: 104 
 * Exported Pre Version 1.1. Made a quest line to follow.
 * +47 total lines. 847 total.
 * 
 * August 1 2014 12:00am
 * World: 7Unch Stuff: 411 Room:88 Event: 161 Inventory: 88Unch Dialogue: 102 *Data: 89
 * Exported Pre Version 1.2. Mostly cleaned up. Added a couple of things as well as removing a lot of junk. Ground work done.
 * +99 total lines. 946 total.
 * 
 * August 5 2014 7:00pm
 * World: 7unch Stuff: 561 Room: 95 Event: 171 Inventory: 88unch Dialogue: 191 Data: 255
 * +422 total lines. 1368 total.
 * 
 * 
 * TO DO LIST:
 * Maps
 * Level Editor
 */
public class World {
	public static void main(String args[]){
		Stuff stuffy = new Stuff();
		stuffy.gameLoop();
	}
}