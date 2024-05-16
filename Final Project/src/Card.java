
class Card implements CardInterface
{
   // private data
   private char value;
   private Suit suit;
   private boolean errorFlag;
   private static final Suit DefaultSuit = Suit.spades;
   private static final char DefaultCard = 'A';

   // Constructors

   // Card() constructs when given value and suit
   public Card(char value, Suit suit)
   {
      set(value, suit);
   }

   // When given only value
   public Card(char value)
   {
      this(value, DefaultSuit);
   }

   // default constructor
   public Card()
   {
      this(DefaultCard, DefaultSuit);
   }

   // copy constructor
   public Card(Card card)
   {
      set(card.value, card.suit);
   }

   // Private method to see if returns are true or false
   private static boolean isValid(char value, Suit suit)
   {
      char upVal = Character.toUpperCase(value);
      return (upVal == 'A' || upVal == 'K' || upVal == 'Q' || upVal == 'J' || upVal == 'T'
            || (upVal >= '2' && upVal <= '9'));
   }

   // mutator
   public boolean set(char value, Suit suit)
   {
      this.errorFlag = !this.isValid(value, suit);
      char upVal = Character.toUpperCase(value);
      this.suit = suit;
      this.value = upVal;
      return !this.errorFlag;
   }

   // accessors

   public Suit getSuit()
   {
      return suit;
   }

   public boolean isErrorFlag()
   {
      return errorFlag;
   }

   public char getValue()
   {
      return value;
   }

   // checks to see if this card is equal to their card
   public boolean equals(Card card)
   {
      if (this.errorFlag != card.isErrorFlag())
      {
         return false;
      }
      if (this.suit != card.getSuit())
      {
         return false;
      }
      if (this.value != card.getValue())
      {
         return false;
      }
      return true;
   }

   // stringizer
   public String toString()
   {
      if (this.errorFlag == true)
      {
         return "** illegal **";
      } else
      {
         String retVal;
         retVal = String.valueOf(value) + " of " + suit;
         return retVal;
      }
   }
}
