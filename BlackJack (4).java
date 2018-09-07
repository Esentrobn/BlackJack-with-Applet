
import java.util.*;
public class BlackJack
{
   private DeckOfCards deck;
   private Vector<String> player;
   private Vector<String> dealer;
   //Post: The BlackJack game, the dealer hand, and the player hand are all properly
   //      initialized. The DeckOfCards are shuffled. Both the player and the dealer are
   //      delt 2 cards.
   public BlackJack()
   {
      deck=new DeckOfCards();
      player=new Vector<String>();
      dealer=new Vector<String>();
      deck.shuffle();
      for(int i=0;i<2;++i)
      {   
         player.add(deck.deal());
      }
      for(int i=0;i<2;++i)
      {  
         dealer.add(deck.deal());
      }
   }
   //Desc:   Play 1 game of BlackJack
   //Input:  When prompted, via the keyboard hit 'h' to hit or 's' to stay.
   //Output: Various messages indicating the progress of the game.
   public void play()
   {
      displayPlayer();
      displayDealer();
      playersTurnForBlackjack();
      displayDealerTwo();
      dealerTurn();
   }
   //Output: the cards currently in the player's hand.
   private void displayPlayer()
   {
      System.out.println("Your hand: ");
      for(int i=0;i<player.size();++i)
         System.out.println("      "+player.get(i));
   }
   //Output: the dealer's initial hand. The second card is hidden by a row of stars. 
   private void displayDealer()
   {
      System.out.println("Dealer's hand: ");
      for(int i=0;i<1;++i)
         System.out.println("      "+dealer.get(i));
      System.out.println("      *******");
   }
   //Output: the cards currently in the dealer's hand.
   private void displayDealerTwo()
   {
     System.out.println("Dealer's hand:");
      for(int i=0;i<dealer.size();++i)
         System.out.println("      "+dealer.get(i));
   } 
   //Desc: Draws another card if player chooses to hit. Exits the program if player busts.
   //Input: 'h' to hit or 's' to stay when prompted.
   //Output: the total of the players current hand. A message indicating that the player
   //        has busted if player's hand total is over 21.
   public void playersTurnForBlackjack()
   {
      while(true)
      {
         Scanner f=new Scanner(System.in);
         System.out.print("You have "+total(player));
         System.out.print("   Hit or stay - H/S: ");
         String pick=f.nextLine();
         if(pick.charAt(0)=='h')
            {
               player.add(deck.deal());
               displayPlayer();
               if(total(player)>21)
               {
                  System.out.println("You busted. Peace out!");
                  System.exit(0);
               }
            }
         else break;
      }   
   }
   //Desc: If dealer's hand is less than 17 dealer must hit until it is over 17.
   //      If dealer's hand is more than 17 but less than or equal to 21, dealer must stay.  
   //Output: the total of the dealer's hand. Various messages indicating who has won the game.
   public void dealerTurn()
   {
      while(true)
      {   
         if(total(dealer)<17)     
         {
            dealer.add(deck.deal());
            displayDealerTwo();
         }
         else if((total(dealer)>=17) && (total(dealer)<=21))
         {  
            if(total(dealer)>total(player))
            {
               System.out.println("Dealer has "+total(dealer)+". You lose. Bye Bye");
               break;
            }
            else
            {
               System.out.println("Dealer has "+total(dealer)+". You Won! Bye Bye");
               break;
            }
         }
         else
         {
            System.out.println("Dealer lost "+total(dealer)+". You Won! Bye!Bye");
            break;
         }
      }
   }   
   //Pre: v contains playing cards
   //Return: the numeric total of all cards in v
   private int total(Vector<String> v)
   {
       int result=0;
       for( int i=0;i<v.size();++i)
       {
           if(v.get(i).startsWith("Ace")) result+=11;
           else if(v.get(i).startsWith("Two")) result+=2;
           else if(v.get(i).startsWith("Three")) result+=3;
           else if(v.get(i).startsWith("Four")) result+=4;
           else if(v.get(i).startsWith("Five")) result+=5;
           else if(v.get(i).startsWith("Six")) result=+6;
           else if(v.get(i).startsWith("Seven")) result+=7;
           else if(v.get(i).startsWith("Eight")) result+=8;
           else if(v.get(i).startsWith("Nine")) result+=9;
           else result+=10;
       }
	return result;
   }
}