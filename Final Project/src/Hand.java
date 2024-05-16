import java.util.ArrayList;

public class Hand
   {
      // hand is an array list that holds the value of our cards 
      private ArrayList<Card> hand = new ArrayList<Card>();
      
      // constructor 
      public Hand()
      {
         this.resetHand();
      }
      
      public int getHandPos(Card card) {
         return this.hand.indexOf(card);
      }
      
      // resetHand() replaces the array with a new array 
      public void resetHand()
      {
         
         hand = new ArrayList<Card>();
      }
      
      // plays card i from your hand.
      public Card playCard(int i)
      {
         Card card = hand.get(i);
         hand.remove(i);
         
         return card;
      }
      // plays the last card in your hand/array
      public boolean takeCard(Card card)
      {
         hand.add(card);
         
         return true;
      }
      
      //takes the values in your array and converts then in a string 
      public String toString()
      {
         String Wake = "";
         for (int i =0; i < hand.size(); i ++)
         {
            Wake += hand.get(i).toString();
            if (i != hand.size() -1)
            {
               Wake += ", ";
            }
         }
         return Wake;
      }
      
      //accessor  for NumCards()
      public int getNumCards()
      {
         return hand.size();
      }
      
      // Method that looks into myCards at index k 
      public Card inspectCard(int k)
      {
         if(k < 0 || k >= hand.size())
         {
            Card Error = new Card('M', Card.Suit.clubs);
            return Error;
         }
         return hand.get(k);
      }
      
      public int size() {
         return hand.size();
      }
   }
