import java.awt.*;
import javax.swing.*;

class CardTable extends JFrame
{
   //class variables
   public JPanel pnlHumanHand;
   public JPanel pnlComputerArea;
   public JPanel pnlHumanArea;
   public JPanel computerTop;
   public JPanel pnlPlayArea;
   
   static int MAX_CARDS_PER_HAND = 57;
   static int MAX_PLAYERS = 2; 

   private int numCardsPerHand;
   private int numPlayers;

   // constructor
   public CardTable(String title, int numCardsPerHand, int numPlayers)
   {
      super(title);
      this.numCardsPerHand = numCardsPerHand;
      this.numPlayers = numPlayers;

      // setting up new JPanels
      this.setLayout(new GridLayout(3,1));
      pnlHumanHand = new JPanel();
      pnlHumanHand.setBorder(BorderFactory.createTitledBorder("Your Hand"));
      pnlPlayArea = new JPanel();
      pnlPlayArea.setBorder(BorderFactory.createTitledBorder("Playing Area"));
      computerTop = new JPanel();
      computerTop.setBorder(BorderFactory.createTitledBorder("Computer Cards"));  

      // the middle area
      pnlComputerArea = new JPanel();
      pnlComputerArea.setBorder(BorderFactory.createTitledBorder("Computer"));
      pnlHumanArea = new JPanel();
      pnlHumanArea.setBorder(BorderFactory.createTitledBorder("You"));
      pnlPlayArea.setLayout(new GridLayout(1,2));
      pnlPlayArea.add(pnlComputerArea);
      pnlPlayArea.add(pnlHumanArea);

      // adding the JPanels to the JFrame
      add(computerTop);
      add(pnlPlayArea);
      add(pnlHumanHand);
   }

   // looks at k to see if its valid 
   private boolean isNumCardsPerHandValid(int k)
   {
      return (k <= MAX_CARDS_PER_HAND && k > 0);
   }

   // looks to see if the amount of players is valid
   private boolean isNumPlayersValid(int i)
   {
      return (i > 0 && i < 2);
   }

   // accessors
   public int getNumCardsPerHand()
   {
      return numCardsPerHand;
   }

   public int getNumPlayers()
   {
      return numPlayers;
   }
}



