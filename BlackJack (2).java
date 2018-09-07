
//BlackJack.java

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
class MyJFrame extends JFrame implements ActionListener
{
   DeckOfCards deck=new DeckOfCards();
   Vector<String> player=new Vector<String>();
   Vector<String> dealer=new Vector<String>();
   private JLabel[] lblPCard=new JLabel[7];
   private JLabel[] lblDCard=new JLabel[7];
   private JButton btn1=new JButton("Deal");
   private JButton btn2=new JButton("Player");
   private JButton btn3=new JButton("Dealer");
   private JButton btn4=new JButton("New");
   private JPanel pnlPLabel=new JPanel(new GridLayout(1,7));
   private JPanel pnlDLabel=new JPanel(new GridLayout(1,7));
   private JPanel pnlLabel=new JPanel(new GridLayout(2,1));
   private JPanel pnlButtons=new JPanel(new FlowLayout());

   public MyJFrame()
   {
      for(int i=0;i<7;i++)
         lblPCard[i]=new JLabel("Player");
      for(int i=0;i<7;i++)
         lblDCard[i]=new JLabel("Dealer");
      setLayout(new BorderLayout());
      addControls();
      registerListeners();
      btn2.setEnabled(false);
      btn3.setEnabled(false);
      btn4.setEnabled(false);
   }
   public void newGame()
   {
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
   public void reset()
   {
      for(int i=0;i<7;i++)
      {
         lblPCard[i].setText("Player");
         lblPCard[i].setIcon(new ImageIcon(""));
      }
      for(int i=0;i<7;i++)
      {
         lblDCard[i].setText("Dealer");
         lblDCard[i].setIcon(new ImageIcon(""));
      }
      while(!player.isEmpty())
      { 
         player.remove(player.firstElement());
      }
      while(!dealer.isEmpty())
      { 
         dealer.remove(dealer.firstElement());
      }
      btn1.setEnabled(true);
      btn4.setEnabled(false);
   }
   public void addControls()
   {
      
      add(pnlLabel,BorderLayout.CENTER);
      add(pnlButtons,BorderLayout.SOUTH);
      pnlLabelAddControls();
      pnlDLabelAddControls();
      pnlPLabelAddControls();
      pnlButtonsAddControls();
   }
   public void registerListeners()
   {
      btn1.addActionListener(this);
      btn2.addActionListener(this);
      btn3.addActionListener(this);
      btn4.addActionListener(this);
   }
   public void actionPerformed(ActionEvent e)
   {
      if(e.getSource()==btn1)
      {
         newGame();
	 displayPlayer();
	 displayDealer();
         btn1.setEnabled(false);
         btn2.setEnabled(true);
         btn3.setEnabled(false);
         btn4.setEnabled(false);
      }
      else if(e.getSource()==btn2)
      {
         playersTurn();
	 displayPlayer();
      }
      else if(e.getSource()==btn3)
      {
	 dealerTurn();
	 displayDealerTwo();
      }
      
      else if(e.getSource()==btn4)
      {  
         reset();
         btn1.setEnabled(true);
         btn4.setEnabled(false);
      }
   }
   private void pnlLabelAddControls()
   {
      pnlLabel.add(pnlPLabel);
      pnlLabel.add(pnlDLabel);
   }
   private void pnlPLabelAddControls()
   {
      for(int i=0;i<7;i++)
         pnlPLabel.add(lblPCard[i]);
   }
   private void pnlDLabelAddControls()
   {
      for(int i=0;i<7;i++)
         pnlDLabel.add(lblDCard[i]);
   }
   private void pnlButtonsAddControls()
   {
      pnlButtons.add(btn1);
      pnlButtons.add(btn2);
      pnlButtons.add(btn3);
      pnlButtons.add(btn4);	
   }
   private void displayPlayer()
   {
      for(int i=0;i<player.size();++i)
      {
         lblPCard[i].setIcon(new ImageIcon("cardImages/"+player.get(i)+".gif"));
         lblPCard[i].setText("");
      }
   } 
   private void displayDealer()
   {
      for(int i=0;i<1;++i)
      {
         lblDCard[i].setIcon(new ImageIcon("cardImages/"+dealer.get(i)+".gif"));
         lblDCard[i].setText("");
      }
      lblDCard[1].setIcon(new ImageIcon("cardImages/"+"card.gif"));
      lblDCard[1].setText("");
   }
   private void displayDealerTwo()
   {
      for(int i=0;i<dealer.size();++i)
      {
         lblDCard[i].setIcon(new ImageIcon("cardImages/"+dealer.get(i)+".gif"));
         lblDCard[i].setText("");
      }
   }
   public void playersTurn()
   {
      String s=JOptionPane.showInputDialog(null, "You have 18. Hit or Stay H/S:");
      if(s.equals("h")||s.equals("H"))
      {
         player.add(deck.deal());
         if(total(player)>21)
         {
            JOptionPane.showMessageDialog(null, "You Busted");
             btn1.setEnabled(false);
             btn2.setEnabled(false);
             btn3.setEnabled(false);
             btn4.setEnabled(true);
	     displayDealerTwo();
         }   
      }
      else if(s.equals("s")||s.equals("S"))
      {
         btn1.setEnabled(false);
         btn2.setEnabled(false);
         btn3.setEnabled(true);
         btn4.setEnabled(false);
      }
   }   
   public void dealerTurn()
   {
      while(true)
      {
         if(total(dealer)<17)     
         {
            dealer.add(deck.deal());
            displayPlayer();
            displayDealerTwo();
         }
         else if((total(dealer)>=17) && (total(dealer)<=21))
         {  
            if(total(dealer)>total(player))
            {
               JOptionPane.showMessageDialog(null, "You Lost");
               displayDealerTwo();
	       btn1.setEnabled(false);
               btn2.setEnabled(false);
               btn3.setEnabled(false);
               btn4.setEnabled(true);
               break;
            }
            else
            {
               JOptionPane.showMessageDialog(null, "You Won");
               displayDealerTwo();
               btn1.setEnabled(false);
               btn2.setEnabled(false);
               btn3.setEnabled(false);
               btn4.setEnabled(true);
               break;
            }
         }
         else
         {
            JOptionPane.showMessageDialog(null, "You Won");
            displayDealerTwo();
            btn1.setEnabled(false);
            btn2.setEnabled(false);
            btn3.setEnabled(false);
            btn4.setEnabled(true);
            break;
         }
      }
   }
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
           else if(v.get(i).startsWith("Six")) result+=6;
           else if(v.get(i).startsWith("Seven")) result+=7;
           else if(v.get(i).startsWith("Eight")) result+=8;
           else if(v.get(i).startsWith("Nine")) result+=9;
           else result+=10;
       }
	return result;
   }
}
public class BlackJack
{
	public static void main(String[] args)
	{
		MyJFrame f=new MyJFrame();
		f.setTitle("BlackJack");
		f.setSize(700,450);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}
	