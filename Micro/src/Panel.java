import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;







public class Panel extends JPanel 
	implements Runnable, KeyListener{
	JButton but;
	// dimensions
	public static final int WIDTH = 950;
	public static final int HEIGHT = 630;
	public static final int SCALE = 1;
	
	// game thread
	private Thread thread;
	private boolean running=true;
	private boolean running1=false;
	
	private int FPS = 60;
	private long targetTime = 1000 / FPS;
	
	// image
	
	JTextArea editTextArea;
	String s;
	Mpro m;
	
	String []s1=new String[1000];
	Display d;
	
	public Panel() {
		super();
		d=new Display();
		but=new JButton();
		but.setBounds(300,220,200,25);
		editTextArea = new JTextArea("Type Here!");
	     editTextArea.setBounds(10, 10, 880, 200);
//		
//	//	init();
		this.add(but);
		this.add( editTextArea);
		this.add( d);
		setPreferredSize(
			new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		  setBackground(new Color(130,168,180));
		setFocusable(true);
		  setLayout(null);
		requestFocus();
	}
	
	public void addNotify() {
		super.addNotify();
		if(thread == null) {
			thread = new Thread(this);
			addKeyListener(this);
			thread.start();
		}
	}
	
	private void init() {
		//running=true;
		m=new Mpro();
		but.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				m.sp=0;
				
				s=editTextArea.getText();
//				ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(1);
//				exec.scheduleAtFixedRate(new Runnable() {
//				           public void run() {
//				               if(m.Reg[m.aMap.get("bl")].equals("0h")){
//				            	   d.but.setEnabled(false);
//				               }
//				           }
//				       }, 0, 1, TimeUnit.SECONDS);
				s=s.replace(',', ' ');
				s=s.replace(':', ' ');
				s=s.replace('\n', ' ' );
				s=s.replace('[', ' ' );
				s=s.replace(']', ' ' );
				s=s.replace("+", " + " );
				
				
				
				s=s.trim().replaceAll("\\s+", " ");
				s=s.toLowerCase();
				s1=s.split(" ");
				
				System.out.println(s);
				//m.sp=Arrays.asList(s1).indexOf("start")+1;
				System.out.println(m.sp);
//				for(int i=Arrays.asList(s1).indexOf("start");i<s1.length;i++){
//					System.out.println(s1[i]);
//				}
				
				
				//rohim();
				
						
						
				running1=true;
			}
		});
		
	}
	
	public void run() {
		
		init();
		//System.out.println("jjjjjjjjjjjjjjj");
		long start;
		long elapsed;
		long wait;
		
		// game loop
		while(running) {
			
			start = System.nanoTime();
			
			update();
			draw();
			drawToScreen();
			
			elapsed = System.nanoTime() - start;
			
			wait = targetTime - elapsed / 1000000;
			if(wait < 0) wait = 16;
			
			try {
				Thread.sleep(wait);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	private void update() {
	//	&& m.sp<s1.length
		          if(running1 ){  
		        	 
                        rohim();

		 
		          }
	}
	private void draw() {
		
	}
	private void drawToScreen() {
		
		
	}
	
	public void keyTyped(KeyEvent key) {}
	public void keyPressed(KeyEvent key) {
		
	}
	public void keyReleased(KeyEvent key) {
		
	}
	public void rohim(){
		while(m.sp<s1.length){
			String h=m.Reg[m.aMap.get("19h")];
			//char []c=h.toCharArray();
			//System.out.println("hhhhhhhhhhhhhhhhh"+h.charAt(0));
			if(h.charAt(7)=='0'){
				d.a.setIcon(d.icon3);
			}
			else{
				d.a.setIcon(d.icon1);
			}
			if(h.charAt(1)=='0'){
				d.g.setIcon(d.icon3);
			}
			else{
				d.g.setIcon(d.icon1);
			}
			if(h.charAt(4)=='0'){
				d.d.setIcon(d.icon3);
			}
			else{
				d.d.setIcon(d.icon1);
			}
			
			
			if(h.charAt(6)=='0'){
				d.b.setIcon(d.icon4);
			}
			else{
				d.b.setIcon(d.icon2);
			}
			if(h.charAt(5)=='0'){
				d.c.setIcon(d.icon4);
			}
			else{
				d.c.setIcon(d.icon2);
			}
			
			if(h.charAt(3)=='0'){
				d.e.setIcon(d.icon4);
			}
			else{
				d.e.setIcon(d.icon2);
			}
			if(h.charAt(2)=='0'){
				d.f.setIcon(d.icon4);
			}
			else{
				d.f.setIcon(d.icon2);
			}
		///Led	
			String h1=m.Reg[m.aMap.get("1bh")];
			if(h1.charAt(7)=='1'){
				d.R1.setIcon(d.icon5);
			}
			else{
				d.R1.setIcon(d.icon8);
			}
			
			if(h1.charAt(6)=='1'){
				d.G.setIcon(d.icon6);
			}
			else{
				d.G.setIcon(d.icon8);
			}
			
			if(h1.charAt(5)=='1'){
				d.Y.setIcon(d.icon7);
			}
			else{
				d.Y.setIcon(d.icon8);
			}
			
			if(h1.charAt(4)=='1'){
				d.R2.setIcon(d.icon5);
			}
			else{
				d.R2.setIcon(d.icon8);
			}
			String h2=m.Reg[m.aMap.get("18h")];
			String h3=m.Reg[m.aMap.get("1ah")];
			String h4=m.Reg[m.aMap.get("1ch")];
			
		//	System.out.println("188888888888h"+h2+"1aaaaaaaaaaaaaah"+h3+"1cccccccccccccccccch"+h4);
			for(int row=0;row<8;row++){
				for(int col=0;col<8;col++){
					if(h4.charAt(col)=='0'){
						d.MAT[row][col].setIcon(d.icon8);
					}
					else{
						if(h2.charAt(row)=='0' && h3.charAt(row)=='0'){
							d.MAT[row][col].setIcon(d.icon7);
						}
						else if(h2.charAt(row)=='0' && h3.charAt(row)=='1'){
							d.MAT[row][col].setIcon(d.icon6);
						}
						else if(h2.charAt(row)=='1' && h3.charAt(row)=='0'){
							d.MAT[row][col].setIcon(d.icon5);
						}
						else{
							d.MAT[row][col].setIcon(d.icon8);
						}
					}
				}
			}
			
			m.m8086(s1[m.sp],s1);
			 
		}
		}
}