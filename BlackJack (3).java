
import javax.swing.*;
import java.awt.*;
class MyJFrame extends JFrame
{
	private JLabel[] lblPCard=new JLabel[7];
	private JLabel[] lblDCard=new JLabel[7];
	private JButton btn1=new JButton("Player");
	private JButton btn2=new JButton("Dealer");
	private JButton btn3=new JButton("Deal");
	private JButton btn4=new JButton("New");
	private JPanel pnlPLabel=new JPanel(new GridLayout(1,7));
	private JPanel pnlDLabel=new JPanel(new GridLayout(1,7));
	private JPanel pnlButtons=new JPanel(new FlowLayout());
	public MyJFrame()
	{
		for(int i=0;i<7;i++)
			lblPCard[i]=new JLabel("Player");
		for(int i=0;i<7;i++)
			lblDCard[i]=new JLabel("Dealer");
		setLayout(new GridLayout(3,1));
		add(pnlPLabel);
		add(pnlDLabel);
		add(pnlButtons);
		pnlPButtonsAddControls();
		pnlDButtonsAddControls();
		pnlButtonsAddControls();
	}
	private void pnlPButtonsAddControls()
	{
		for(int i=0;i<7;i++)
			pnlPLabel.add(lblPCard[i]);
	}
	private void pnlDButtonsAddControls()
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
}
public class BlackJack
{
	public static void main(String[] args)
	{
		MyJFrame f=new MyJFrame();
		f.setTitle("BlackJack");
		f.setSize(500,300);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}
	