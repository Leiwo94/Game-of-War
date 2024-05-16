import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;


class GUICard extends JButton implements CardInterface
{
   private Card card = new Card(); 

   // Static Members
   private static ImageIcon[][] iconCards = new ImageIcon[14][4]; // 14 = 2 through 9 and TJQKAX (X is the joker)
   private static ImageIcon iconBack;
   private static boolean iconsLoaded = false;

   private static final int invalidNumber = -1;
   private Hand currHand;
   private Card currCard;
   private int weight;
                   

   // Helper Static Array
   private static String cardlValsConvertAssist = "23456789TJQKAX";
   private static String suitValsConvertAssist = "CDHS";
   private static Card.Suit suitConvertAssist[] =
   { Card.Suit.clubs, Card.Suit.diamonds, Card.Suit.hearts, Card.Suit.spades };

   // Constructors 
   public GUICard()
   {  
   }
   
   public GUICard(Card card, Hand currHand)
   {
      set(card.getValue(), card.getSuit());
      setCurrCard(card);
      setCurrHand(currHand);
      
   }
   
   
   public Card getCurrCard() {
      return this.currCard;
   }
   
   public void setCurrCard(Card currCard) {
      this.currCard = currCard;
   }
   public Hand getCurrHand() {
      return currHand;
   }
   
   public void setCurrHand(Hand currHand) {
      this.currHand = currHand;
   }
   
   public GUICard(char value, Suit suit)
   {
      set(value,suit);
   }
   
   public int getHandPos() {
      return this.currHand.getHandPos(this.currCard);
   }
   
   public void setWeight(int weight) {
      this.weight = weight;
   }
   
   public int getWeight() {
      return weight;
   }
   
   public Icon getIcon()
   {
      if (this.card == null || !iconsLoaded )
      {
         return super.getIcon();
      }
      else 
         return getIcon(this.card);
   }
   
   // Static Methods
   static void loadCardIcons()
   {
      String imageFileName;
      for (int intSuit = 0; intSuit < 4; intSuit++)
         for (int intVal = 0; intVal < 14; intVal++)
         {
            imageFileName = "images/" + turnIntIntoCardValueChar(intVal) + turnIntIntoCardSuitChar(intSuit) + ".gif";
            ImageIcon imageIcon = new ImageIcon(imageFileName);
            imageIcon.setDescription("icon" + (intSuit * 14 + intVal));
            iconCards[intVal][intSuit] = imageIcon;
         }
      String imageBackFileName = "images/BK.gif";
      iconBack = new ImageIcon(imageBackFileName);
      iconsLoaded = true;
   }

   // turns 0 - 13 into 'A', '2', '3', ... 'Q', 'K', 'X'
   static char turnIntIntoCardValueChar(int k)
   {
      if (k < 0 || k > 13)
         return '?';
      return cardlValsConvertAssist.charAt(k);
   }

   // turns 0 - 3 into 'C', 'D', 'H', 'S'
   static char turnIntIntoCardSuitChar(int k)
   {
      if (k < 0 || k > 3)
         return '?';
      return suitValsConvertAssist.charAt(k);
   }
   // turns ints 0 - 3 into suits
   static Suit turnIntIntoSuit(int k)
   {
      return suitConvertAssist[k];
   }
   // pulls icons for cards
   static public Icon getIcon(Card card)
   {
      loadCardIcons(); 
      return iconCards[valueAsInt(card)][suitAsInt(card)];
   }
   // adds back image for cards
   static public Icon getBackCardIcon()
   {   
      return iconBack;
   }
   // turns card values into int
   static int valueAsInt(Card card)
   {
      return cardlValsConvertAssist.indexOf(card.getValue());
   }
   // turns card suits into int
   static int suitAsInt(Card card)
   {
      for (int i = 0; i < suitValsConvertAssist.length(); i++)
      {
         if (suitConvertAssist[i] == card.getSuit())
         {
            return i;
         }
      }
      return invalidNumber;
   }
   
   // Adding card methods to satisfy CardInterface
   public boolean set(char value, Suit suit)
   {
       boolean successful = this.card.set(value, suit);
       if (successful)
       {
          if (value == 'A') {
             this.weight = 14;
          } else if (value == 'K') {
             this.weight = 13;
          } else if (value == 'Q') {
             this.weight = 12;
          } else if (value == 'J') {
             this.weight = 11;
          } else if (value == 'T') {
             this.weight = 10;
          } else {
             this.weight = Character.getNumericValue(value);
          }
          
           this.setDisabledIcon(getIcon(this.card));
       }
       return successful;
   }
   
   // Compares card seen with card recieved 
   public boolean equals(Card card)
   {
      return this.card.equals(card);
   }
   
   public String toString()
   {
      return this.card.toString();
   }
   
   public char getValue()
   {
      return this.card.getValue();
   }
   
   public Suit getSuit()
   {
      return this.card.getSuit();
   }
   
   public boolean isErrorFlag()
   {
      return this.card.isErrorFlag();
   }
   

}