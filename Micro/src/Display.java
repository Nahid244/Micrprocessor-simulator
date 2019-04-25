import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;



public class Display extends JPanel{
	 JButton a,b,c,d,e,f,g;
	 
	 JButton R2,Y,G,R1;
	 JButton [][]MAT=new JButton[8][8];
	 ImageIcon icon1,icon2, icon3,icon4,icon5,icon6,icon7,icon8;
    public Display(){
    	init();
    }
    public void init(){
    	
    	 icon1=new ImageIcon(getClass().getResource("aa.png"));
    	 icon2=new ImageIcon(getClass().getResource("ab.png"));
    	 icon3=new ImageIcon(getClass().getResource("ca.png"));
    	 icon4=new ImageIcon(getClass().getResource("cb.png"));
    	 
    	 icon5=new ImageIcon(getClass().getResource("red.png"));
    	 icon6=new ImageIcon(getClass().getResource("green.png"));
    	 icon7=new ImageIcon(getClass().getResource("yellow.png"));
    	 icon8=new ImageIcon(getClass().getResource("off.png"));
    	 
    	 a= new JButton(icon1);
	     a.setBounds(40,20,70,10);
	    a.setBackground(new Color(255,255,255));
	     a.setBorder(null);
	      a.setVisible(true);
	      
	      g= new JButton(icon1);
		     g.setBounds(40,120,70,10);
		    g.setBackground(new Color(255,255,255));
		     g.setBorder(null);
		      g.setVisible(true);
		      
		      d= new JButton(icon1);
			     d.setBounds(40,220,70,10);
			    d.setBackground(new Color(255,255,255));
			     d.setBorder(null);
			      d.setVisible(true);
			      
			      b= new JButton(icon2);
				     b.setBounds(110,20,10,100);
				    b.setBackground(new Color(255,255,255));
				     b.setBorder(null);
				      b.setVisible(true);
				      
				      c= new JButton(icon2);
					     c.setBounds(110,120,10,100);
					    c.setBackground(new Color(255,255,255));
					     c.setBorder(null);
					      c.setVisible(true); 
					      f= new JButton(icon2);
						     f.setBounds(30,20,10,100);
						    f.setBackground(new Color(255,255,255));
						    f.setBorder(null);
						      f.setVisible(true);
						      
						      e= new JButton(icon2);
							     e.setBounds(30,120,10,100);
							    e.setBackground(new Color(255,255,255));
							     e.setBorder(null);
							      e.setVisible(true); 	
		
			R2=new JButton(icon8);
			R2.setBounds(800, 20, 25, 25);
			R2.setBackground(new Color(255,255,255));
			R2.setBorder(null);
			R2.setVisible(true); 	
			
			G=new JButton(icon8);
			G.setBounds(850, 20, 25, 25);
		     G.setBackground(new Color(255,255,255));
			G.setBorder(null);
			G.setVisible(true); 
			
			Y=new JButton(icon8);
			Y.setBounds(800, 70, 25, 25);
		     Y.setBackground(new Color(255,255,255));
			Y.setBorder(null);
			Y.setVisible(true); 
			
			
			R1=new JButton(icon8);
			R1.setBounds(850, 70, 25, 25);
		     R1.setBackground(new Color(255,255,255));
			R1.setBorder(null);
			R1.setVisible(true); 
			
			for(int i=7;i>=0;i--){
				MAT[7][i]=new JButton(icon8);
				MAT[7][i].setBounds(300+(7-i)*30, 220,10, 10);
				MAT[7][i].setBackground(new Color(255,255,255));
				MAT[7][i].setBorder(null);
				MAT[7][i].setVisible(true); 
			
			}
			for(int i=7;i>=0;i--){
				MAT[6][i]=new JButton(icon8);
				MAT[6][i].setBounds(300+(7-i)*30, 190,10, 10);
				MAT[6][i].setBackground(new Color(255,255,255));
				MAT[6][i].setBorder(null);
				MAT[6][i].setVisible(true); 
			
			}
			for(int i=7;i>=0;i--){
				MAT[5][i]=new JButton(icon8);
				MAT[5][i].setBounds(300+(7-i)*30, 160,10, 10);
				MAT[5][i].setBackground(new Color(255,255,255));
				MAT[5][i].setBorder(null);
				MAT[5][i].setVisible(true); 
			
			}
			for(int i=7;i>=0;i--){
				MAT[4][i]=new JButton(icon8);
				MAT[4][i].setBounds(300+(7-i)*30, 130,10, 10);
				MAT[4][i].setBackground(new Color(255,255,255));
				MAT[4][i].setBorder(null);
				MAT[4][i].setVisible(true); 
			
			}
			for(int i=7;i>=0;i--){
				MAT[3][i]=new JButton(icon8);
				MAT[3][i].setBounds(300+(7-i)*30,100,10, 10);
				MAT[3][i].setBackground(new Color(255,255,255));
				MAT[3][i].setBorder(null);
				MAT[3][i].setVisible(true); 
			
			}
			for(int i=7;i>=0;i--){
				MAT[2][i]=new JButton(icon8);
				MAT[2][i].setBounds(300+(7-i)*30, 70,10, 10);
				MAT[2][i].setBackground(new Color(255,255,255));
				MAT[2][i].setBorder(null);
				MAT[2][i].setVisible(true); 
			
			}
			for(int i=7;i>=0;i--){
				MAT[1][i]=new JButton(icon8);
				MAT[1][i].setBounds(300+(7-i)*30, 40,10, 10);
				MAT[1][i].setBackground(new Color(255,255,255));
				MAT[1][i].setBorder(null);
				MAT[1][i].setVisible(true); 
			
			}
			for(int i=7;i>=0;i--){
				MAT[0][i]=new JButton(icon8);
				MAT[0][i].setBounds(300+(7-i)*30, 10,10, 10);
				MAT[0][i].setBackground(new Color(255,255,255));
				MAT[0][i].setBorder(null);
				MAT[0][i].setVisible(true); 
			
			}
//				   a= new JButton(icon2);
//			   a.setBounds(110,20,70,10);
//			   a.setBackground(new Color(255,255,255));
//			    a.setBorder(null);
//			 a.setVisible(true);
	      
	    // but.setBackground(Color.GREEN.brighter().brighter());
	     this.add(a);
	     this.add(g);
	     this.add(d);
	     this.add(b);
	     this.add(c);
	     
	     this.add(f);
	     this.add(e);
	     
	     this.add(R2);
	     this.add(G);
	     this.add(Y);
	     this.add(R1);
	     
	     for(int i=0;i<8;i++){
	    	 this.add(MAT[0][i]);
	     }
	     for(int i=0;i<8;i++){
	    	 this.add(MAT[1][i]);
	     }
	     for(int i=0;i<8;i++){
	    	 this.add(MAT[2][i]);
	     }
	     for(int i=0;i<8;i++){
	    	 this.add(MAT[3][i]);
	     }
	     for(int i=0;i<8;i++){
	    	 this.add(MAT[4][i]);
	     }
	     for(int i=0;i<8;i++){
	    	 this.add(MAT[5][i]);
	     }
	     for(int i=0;i<8;i++){
	    	 this.add(MAT[6][i]);
	     }
	     for(int i=0;i<8;i++){
	    	 this.add(MAT[7][i]);
	     }
	     
	     this.setBounds(10, 300, 920, 250);
         this.setBackground(new Color(255,255,255));
       this.setVisible(true);
         this.setLayout(null);
    }
}

