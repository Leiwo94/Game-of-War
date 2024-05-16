import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * 
 */
public class CardGame
{
   // static variables
   static int NUM_CARDS_PER_HAND = 7;
   static int NUM_PLAYERS = 2;
   static int playerScore = 0;
   static int CPUScore = 0;
   static JLabel[] computerLabels = new JLabel[NUM_CARDS_PER_HAND];
   // static GUICard[] humanGUICards = new GUICard[NUM_CARDS_PER_HAND];
   private static ArrayList<GUICard> humanGUICards = new ArrayList<GUICard>();
   private static ArrayList<GUICard> CPUGUICards = new ArrayList<GUICard>();

   static GUICard[] playedCards = new GUICard[NUM_PLAYERS];
   // static GUICard[] CPUGUICards = new GUICard[NUM_CARDS_PER_HAND];
   static JLabel[] playLabelText = new JLabel[NUM_PLAYERS];
   static Deck playerDeck = new Deck();
   private static Hand playerHand = new Hand();
   private static Hand CPUHand = new Hand();
   private static boolean DEBUG = true;

   // main class
   public static void main(String[] args)
   {
      int k;
      dealCards();

      // establish main frame in which program will run
      CardTable myCardTable = new CardTable("CardTable", NUM_CARDS_PER_HAND,
            NUM_PLAYERS);
      myCardTable.setName("CardTable");
      myCardTable.setSize(1024, 768);
      myCardTable.setLocationRelativeTo(null);
      myCardTable.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      // show everything to the user
      myCardTable.setVisible(true);

      // ActionListener inner class for humanButtons
      class HumanGUICardActionListener implements ActionListener
      {
         // event handler for JButton mouse click or space key
         public void actionPerformed(ActionEvent e)
         {
            GUICard button = (GUICard) e.getSource();
            // System.out.println(button.getHandPos());
            myCardTable.pnlHumanHand.remove(button);
            playerHand.playCard(button.getHandPos());
            myCardTable.pnlHumanHand.repaint();
            myCardTable.pnlHumanHand.doLayout();
            button.setEnabled(false);
            myCardTable.pnlHumanArea.removeAll();
            myCardTable.pnlHumanArea.add(button);
            myCardTable.pnlHumanArea.doLayout();
            int randomNum;
            if (CPUHand.size() > 1)
            {
               randomNum = ThreadLocalRandom.current().nextInt(0,
                     playerHand.size());
            } else
            {
               randomNum = 0;
            }
            GUICard cpuDummyButton = CPUGUICards.get(randomNum);
            boolean didPlayerWin = button.getWeight() > cpuDummyButton
                  .getWeight();
            boolean didPlayerTie = button.getWeight() == cpuDummyButton
                  .getWeight();
            myCardTable.computerTop.remove(cpuDummyButton);
            CPUHand.playCard(randomNum);
            CPUGUICards.remove(randomNum);
            cpuDummyButton.setEnabled(false);
            myCardTable.computerTop.repaint();
            myCardTable.computerTop.doLayout();
            myCardTable.pnlComputerArea.removeAll();
            myCardTable.pnlComputerArea.add(cpuDummyButton);
            myCardTable.pnlComputerArea.doLayout();

            if (playerHand.size() == 0)
            {
               String message;

               if (playerScore > CPUScore)
               {
                  message = "You Won the game! This is your score "
                        + playerScore + " This is the CPU's score " + CPUScore
                        + " Thank you for playing!";

               } else if (playerScore == CPUScore)
               {
                  message = "You tied the game! This is your score "
                        + playerScore + " This is the CPU's score " + CPUScore
                        + " Thank you for playing!";
               } else
               {
                  message = "You lost. This is your score " + playerScore
                        + " This is the CPU's score " + CPUScore
                        + " Thank you for playing!";
               }

               JOptionPane.showMessageDialog(null, message);
            } else
            {

               if (didPlayerWin && !didPlayerTie)
               {
                  playerScore++;
                  JOptionPane.showMessageDialog(null,
                        "You Won! This is your score: " + playerScore
                              + " This is the CPU's score: " + CPUScore);
                  System.out.println("You won this round!");
               } else if (didPlayerTie)
               {
                  JOptionPane.showMessageDialog(null,"You tied, try again! This is your score: "
                        + playerScore + " This is the CPU's score: "
                        + CPUScore);
               } else
               {
                  CPUScore++;
                  JOptionPane.showMessageDialog(null,"You lost, try again. This is your score: "
                        + playerScore + " This is the CPU's score: "
                        + CPUScore);
               }
            }
         }
      }

      // prepare the human player's array of GUICards (bottom region)
      for (k = 0; k < playerHand.size(); ++k)
      {
         // humanGUICards[k] = new GUICard(playerHand.inspectCard(k),
         // playerHand);
         humanGUICards.add(new GUICard(playerHand.inspectCard(k), playerHand));
         System.out.println(humanGUICards.get(k));
         humanGUICards.get(k)
               .addActionListener(new HumanGUICardActionListener());
      }

      // prepare the computer label array (all card backs, top region)
      if (DEBUG == true)
      {
         for (k = 0; k < CPUHand.size(); ++k)
         {
            CPUGUICards.add(new GUICard(CPUHand.inspectCard(k), CPUHand));
            System.out.println(CPUGUICards.get(k));
            CPUGUICards.get(k)
                  .addActionListener(new HumanGUICardActionListener());
         }
      } else
      {
         for (k = 0; k < NUM_CARDS_PER_HAND; ++k)
         {
            computerLabels[k] = new JLabel(GUICard.getBackCardIcon());
         }
      }

      // prepare the played-card GUICard array with jokers
      for (k = 0; k < NUM_PLAYERS; ++k)
      {
         playedCards[k] = new GUICard('X', Card.Suit.clubs);
         playedCards[k].setEnabled(false);
      }

      // ADD LABELS TO PANELS -----------------------------------------

      for (k = 0; k < NUM_CARDS_PER_HAND; ++k)
      {
         if (DEBUG == true)
         {
            myCardTable.computerTop.add(CPUGUICards.get(k));
         } else
         {
            myCardTable.computerTop.add(computerLabels[k]);
         }
      }
      for (k = 0; k < NUM_CARDS_PER_HAND; ++k)
      {
         myCardTable.pnlHumanHand.add(humanGUICards.get(k));
      }

      // and two random cards in the play region (simulating a computer/hum ply)
      for (k = 0; k < NUM_PLAYERS; ++k)
      {
         myCardTable.pnlComputerArea.add(playedCards[k]);
         ++k;
         myCardTable.pnlHumanArea.add(playedCards[k]);
      }

      // show everything to the user
      myCardTable.setVisible(true);
   }

   // gets a random int for suit and value and uses that to return a random card
   // static Card generateRandomCard()
   // {
   // Card.Suit randomSuit = GUICard.turnIntIntoSuit((int) (Math.random() * 4));
   // char randomValue = GUICard.turnIntIntoCardValueChar((int) (Math.random() *
   // 14));
   // return new Card(randomValue, randomSuit);
   //
   //
   // }

   public static void dealCards()
   {
      playerHand.resetHand();
      CPUHand.resetHand();

      for (int i = 0; i < NUM_CARDS_PER_HAND; i++)
      {
         playerHand.takeCard(playerDeck.dealCard());
         CPUHand.takeCard(playerDeck.dealCard());
      }
      playerScore = 0;
      CPUScore = 0;

   }

}
