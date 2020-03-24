package assignment4;
//Linh Nguyen
import java.util.Random;

import algs4.Queue;
import algs4.StdIn;
import algs4.StdOut;

import java.util.ArrayList;

public class ShuffleDeck {
	
	public static void main(String[] args) {
		Queue<String> newDeck = new Queue<String>();
		StdIn.fromFile("data/deckofcards.txt");
		String cardstr = StdIn.readAll();
		String[] cardDeck = cardstr.split("\n");
		for(int i = 0;i<cardDeck.length;i++) {
			newDeck.enqueue(cardDeck[i]);
		}
		shuffle(newDeck);
		//StdOut.println(newDeck);////sanity check
	}//end main
	
	public static void shuffle(Queue<String> deck) {
		Random r = new Random();
		int shufflecount = (int)(Math.log10(deck.size())/Math.log10(2));
		for(int i=0;i<shufflecount;i++) {
			Queue<String> lq = new Queue<>();
			Queue<String> rq = new Queue<>();
			while(deck.isEmpty() == false) {
				String fcard = deck.dequeue();
				//flip coin
				int cflip = r.nextInt(2);
				if(cflip==1) {
					//if heads, enqueue on left, 
					lq.enqueue(fcard);
				}
				else {//////////////////////////////////////////////////
				rq.enqueue(fcard);
				}////////////////////////////////////////////////////////feedback
			}//end of while loop
			while(lq.isEmpty()==false) {
				String lpop = lq.dequeue();
				deck.enqueue(lpop);
			}
			while(rq.isEmpty()==false) {
				String rpop = rq.dequeue();
				deck.enqueue(rpop);
			}
		}//outside for loop
		while(deck.isEmpty()==false) {
			String pc = deck.dequeue();
			StdOut.println(pc);
		}
		return;
	}//end of shuffle
	
}
