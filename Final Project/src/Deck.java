import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

class Deck
{
   private ArrayList<Card> cards = new ArrayList<Card>();
   private int topCard;
   private static CardInterface.Suit[] SUITS = { CardInterface.Suit.clubs, CardInterface.Suit.diamonds, CardInterface.Suit.hearts, CardInterface.Suit.spades };
   
   // constructor 
   public Deck()
   {
      for(int i=0; i < SUITS.length; i++) {
         CardInterface.Suit currSuit = SUITS[i];
         for(int j=1; j <= 13; j++) {
            if(j==1) {
               
               Card card = new Card('A', currSuit);
               cards.add(card);
            } else if(j == 10){
               Card card = new Card('T', currSuit);
               cards.add(card);
            } else if(j == 11) {
               Card card = new Card('J', currSuit);
               cards.add(card);
            } else if(j== 12) {
               Card card = new Card('Q', currSuit);
               cards.add(card);
            } else if(j == 13) {
               Card card = new Card('K', currSuit);
               cards.add(card);
            } else {
               Card card = new Card(Character.forDigit(j, 10), currSuit);
               cards.add(card);
            }
               
         }
      }
      
      this.shuffle();
   }
   
   // method to shuffle deck
   public void shuffle()
   {
      Collections.shuffle(cards);
   }
   
   // method that deals a card 
   public void dealCardToHand(Hand currHand)
   {
      currHand.takeCard(cards.get(0));
      cards.remove(0);
   }
   
   public Card dealCard() {
      Card card = cards.get(0);
      cards.remove(0);
      
      return card;
   }
   
   public int getNumCards()
   {
      return topCard;
   }
   
   // looks at card k and see if its an error or not. 
   public Card inspectCard(int k)
   {
      if(k < 0 || k >= topCard)
      {
         Card Error = new Card('N', Card.Suit.clubs);
         return Error;
      }
      return cards.get(k);
   }
   
   //stringizer for debugging
   public String toString()
   {
      String str = "";
      for (int i =0; i < topCard; i ++)
      {
         str += cards.get(i).toString();
         if ( i != topCard-1)
         {
            str += ", ";
         }
      }
      return str;
   }
}
