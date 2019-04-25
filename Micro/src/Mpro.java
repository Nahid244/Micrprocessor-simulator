import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.TimeUnit;

import javax.swing.Timer;

public class Mpro {
	int sp;
	
	int si=-1;
	int di=-1;
	char cf='0';
	char zf='1';
	boolean cmpabove=false;
	boolean cmpabelow=false;
	boolean cmpaequal=false;
	boolean cmpanequal=false;
	boolean cmpalequal=false;
	boolean cmpagequal=false;
	
	Timer t = new Timer(0, null);
	
	String [] Reg=new String[100];
	//String [] Out=new String[100];
	
	Stack<String> stack = new Stack<String>(); 
	Map<String, Integer> aMap = new HashMap<String, Integer>();
   public Mpro(){
	   aMap.put("ax" , Integer.valueOf(0));
	   aMap.put("bx" , Integer.valueOf(1));
	   aMap.put("cx" , Integer.valueOf(2));
	   aMap.put("dx" , Integer.valueOf(3));
	   aMap.put("ah" , Integer.valueOf(4));
	   aMap.put("bh" , Integer.valueOf(5));
	   aMap.put("ch" , Integer.valueOf(6));
	   aMap.put("dh" , Integer.valueOf(7));
	   aMap.put("al" , Integer.valueOf(8));
	   aMap.put("bl" , Integer.valueOf(9));
	   aMap.put("cl" , Integer.valueOf(10));
	   aMap.put("dl" , Integer.valueOf(11));
	   aMap.put("si" , Integer.valueOf(12));
	   aMap.put("di" , Integer.valueOf(13));
	   aMap.put("19h" , Integer.valueOf(14));
	   aMap.put("1fh" , Integer.valueOf(15));
	   aMap.put("1bh" , Integer.valueOf(16));
	   aMap.put("1eh" , Integer.valueOf(17));
	   aMap.put("18h" , Integer.valueOf(18));
	   aMap.put("1ah" , Integer.valueOf(19));
	   aMap.put("1ch" , Integer.valueOf(20));
	   
	   
	  // aMap.put("19h" , Integer.valueOf(14));
	  for(int i=0 ;i<21;i++){
		  Reg[i]="00000000";
	  }
	  Reg[14]="11111111";
	  Reg[16]="00000000";
	  Reg[17]="00000000";
	  Reg[18]="11111111";
	  Reg[19]="11111111";
	  Reg[20]="00000000";
	  
	  
   }
   public void m8086(String s,String []s1){
	
	   if(s.equals("mov")){
		   System.out.println(s1[sp]+" "+s1[sp+1]+"  "+s1[sp+2]);
		  // System.out.println(" "+s+" "+s1[sp]);
		 //  s1[sp]=s1[sp].replace('o', 'i');
		   if(s1[sp+2].equals("offset")){
			 
			   if(s1[sp+1].equals("si")){
			   si=Arrays.asList(s1).lastIndexOf(s1[sp+3])+1;
			   Reg[aMap.get("si")]=Integer.toHexString(si)+"h";
			   }else{
				   di=Arrays.asList(s1).lastIndexOf(s1[sp+3])+1;
				   Reg[aMap.get("di")]=Integer.toHexString(di)+"h";
			   }
			 
			  // System.out.println("mov done"+" "+Reg[aMap.get("al")]+" "+Reg[aMap.get("cx")]+" "+Reg[aMap.get("bl")]);
			   sp+=4;
		   }
		   else if(s1[sp+2].equals("byte")){
			  // System.out.println(s1[Integer.parseInt( Reg[aMap.get("si")])]);
			   if(s1[sp+5].equals("si")){
				   if(s1[sp+6].equals("+")){
					  // System.out.println("llllllllllllllllllllllllllllllllllllllllllllllllll");
					   mov(s1[sp+1],s1[Integer.parseInt(rep( Reg[aMap.get("si")]),16)+1+Integer.parseInt(rep(s1[sp+7]),16)+Integer.parseInt(rep(s1[sp+7]),16)]);
				   }
				   else{
					   mov(s1[sp+1],s1[Integer.parseInt(rep( Reg[aMap.get("si")]),16)+1]);
					   //System.out.println(s1[Integer.parseInt(rep( Reg[aMap.get("si")]),16)+1]);
				   }
				   
				   
				   
				   
			  
			   }
			   else{
				   if(s1[sp+6].equals("+")){
						  // System.out.println("llllllllllllllllllllllllllllllllllllllllllllllllll");
						   mov(s1[sp+1],s1[Integer.parseInt(rep( Reg[aMap.get("di")]),16)+1+Integer.parseInt(rep(s1[sp+7]),16)+Integer.parseInt(rep(s1[sp+7]),16)]);
					   }
					   else{
						   mov(s1[sp+1],s1[Integer.parseInt(rep( Reg[aMap.get("di")]),16)+1]);
						   //System.out.println(s1[Integer.parseInt(rep( Reg[aMap.get("si")]),16)+1]);
					   }
			   }
			   //System.out.println("mov done"+" "+Reg[aMap.get("al")]+" "+Reg[aMap.get("cx")]+" "+Reg[aMap.get("bl")]);
			   sp+=6;
		   }
		   else{
		   mov(s1[sp+1],s1[sp+2]);
		  // System.out.println("mov done"+" "+Reg[aMap.get("al")]+" "+Reg[aMap.get("cx")]+" "+Reg[aMap.get("bl")]);
		   sp+=3;
		   }
	   }
	   else  if(s.equals("out")){
		   System.out.println(s1[sp]+" "+s1[sp+1]+"  "+s1[sp+2]);
		  // System.out.println(" "+s+" "+s1[sp]);
		  // System.out.println("out done");
		   out(s1[sp+1],s1[sp+2]);
		   System.out.println(Reg[aMap.get("18h")]+"    "+Reg[aMap.get("1ah")]+"    "+Reg[aMap.get("1ch")]+"    "+Reg[aMap.get("si")]+"    "+Reg[aMap.get("cx")]);
		   sp+=3;
	   }
	   else if(s.equals("push")){
		   System.out.println(s1[sp]+" "+s1[sp+1]+"  ");
		  // System.out.println(" "+s+" "+s1[sp]);
		  // System.out.println("push done");
		   pushh(s1[sp+1]);
		   sp+=2;
	   }
	   else if(s.equals("pop")){
		   System.out.println(s1[sp]+" "+s1[sp+1]+"  ");
		  // System.out.println(" "+s+" "+s1[sp]);
		  // System.out.println("pop done");
		   pop(s1[sp+1]);
		   sp+=2;
	   }
	   else if(s.equals("xchg")){
		  // System.out.println(" "+s+" "+s1[sp]);
		   //System.out.println("xchg done");
		   sp+=3;
	   }
	   else if(s.equals("add")){
		   System.out.println(s1[sp]+" "+s1[sp+1]+"  "+s1[sp+2]);
		  // System.out.println(" "+s+" "+s1[sp]);
		 //  System.out.println(s1[Integer.parseInt(rep(Reg[aMap.get("si")]),16)+1]);
		 // System.out.println("add done");
		   if(s1[sp+1].equals("si")){
			   add(s1[sp+1],s1[sp+2]);
			   add(s1[sp+1],s1[sp+2]);
			  // System.out.println(s1[Integer.parseInt(rep(Reg[aMap.get("si")]),16)+1]);
		   }
		   else if(s1[sp+1].equals("di")){
			   add(s1[sp+1],s1[sp+2]);
			   add(s1[sp+1],s1[sp+2]);
		   }
		   else{
			   add(s1[sp+1],s1[sp+2]);
		   }
		   sp+=3;
	   }
	   else if(s.equals("sub")){
		   System.out.println(s1[sp]+" "+s1[sp+1]+"  "+s1[sp+2]);
		  // System.out.println(" "+s+" "+s1[sp]);
		  // System.out.println("sub done");
		   if(s1[sp+1].equals("si")){
			   sub(s1[sp+1],s1[sp+2]);
			   sub(s1[sp+1],s1[sp+2]);
		   }
		   else if(s1[sp+1].equals("di")){
			   sub(s1[sp+1],s1[sp+2]);
			   sub(s1[sp+1],s1[sp+2]);
		   }
		   else{
			   sub(s1[sp+1],s1[sp+2]);
		   }
		   sp+=3;
	   }
	   else if(s.equals("inc")){
		   System.out.println(s1[sp]+" "+s1[sp+1]+"  ");
		   //System.out.println(" "+s+" "+s1[sp]);
		  // System.out.println("inc done");
		   if(s1[sp+1].equals("si")){
			   inc(s1[sp+1]);
			   inc(s1[sp+1]);
		   }
		   else if(s1[sp+1].equals("di")){
			   inc(s1[sp+1]);
			   inc(s1[sp+1]);
		   }
		   else{
			   inc(s1[sp+1]);
		   }
		   sp+=2;
	   }
	   else if(s.equals("dec")){
		   System.out.println(s1[sp]+" "+s1[sp+1]+"  ");
		   //System.out.println(" "+s+" "+s1[sp]);
		   if(s1[sp+1].equals("si")){
			   dec(s1[sp+1]);
			   dec(s1[sp+1]);
		   }
		   else if(s1[sp+1].equals("di")){
			   dec(s1[sp+1]);
			   dec(s1[sp+1]);
		   }
		   else{
			   dec(s1[sp+1]);
		   }
		  // System.out.println("dec done");
		  // dec(s1[sp+1]);
		  // System.out.println("dec done"+" "+Reg[aMap.get("bl")]);
		   sp+=2;
	   }
	   else if(s.equals("cmp")){
		   System.out.println(s1[sp]+" "+s1[sp+1]+"  "+s1[sp+2]);
		//   System.out.println(" "+s+" "+s1[sp]);
		  // System.out.println("cmp done");
		   cmp(s1[sp+1],s1[sp+2]);
		   sp+=3;
	   }
	   else if(s.equals("mul")){
		  // System.out.println("mul done");
		   sp+=2;
	   }
	   else if(s.equals("div")){
		  // System.out.println("div done");
		   sp+=2;
	   }
	   else if(s.equals("neg")){
		 //  System.out.println("neg done");
		   sp+=2;
	   }
	   else if(s.equals("not")){
		  // System.out.println("not done");
		   sp+=2;
	   }
	   else if(s.equals("and")){
		 //  System.out.println("and done");
		   sp+=3;
	   }
	   else if(s.equals("or")){
		 //  System.out.println("or done");
		   sp+=3;
	   }
	   else if(s.equals("xor")){
		   System.out.println(s1[sp]+" "+s1[sp+1]+"  "+s1[sp+2]);
		 //  System.out.println("xor done");
		   xor(s1[sp+1],s1[sp+2]);
		   sp+=3;
	   }
	   else if(s.equals("shl")){
		   System.out.println(s1[sp]+" "+s1[sp+1]+"  "+s1[sp+2]);
		  // System.out.println("shl done");
		   shl(s1[sp+1],s1[sp+2]);
		   sp+=3;
	   }
	   else if(s.equals("shr")){
		   System.out.println(s1[sp]+" "+s1[sp+1]+"  "+s1[sp+2]);
		  // System.out.println("shr done");
		  
		   
		   shr(s1[sp+1],s1[sp+2]);
		   sp+=3;
	   }
	  
	   else if(s.equals("jmp")){
		   System.out.println(s1[sp]+" "+s1[sp+1]+"  "+s1[sp+2]);
//		   Reg[aMap.get("al")]=rep( Reg[aMap.get("al")]);
//		   Reg[aMap.get("bl")]=rep( Reg[aMap.get("bl")]);
//		   int a=Integer.parseInt(Reg[aMap.get("al")],16);
//		   int b=Integer.parseInt(Reg[aMap.get("bl")],16);
//		   a=a+b;
//		   
//		   Reg[aMap.get("al")]=Integer.toHexString(a)+"h";
//		shr("al");
//		shr("al");
		  // cmp("bl","01h");
		   //System.out.println("jmp done"+" "+Reg[aMap.get("al")]);
//		   System.out.println(cmpabove);
//		   System.out.println(cmpabelow);
//		   try {
//			TimeUnit.SECONDS.sleep(1);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
			
		 //  System.out.println("jumpppppppppppppppppppppppppppppppppppppppppppp");
//		   if(Arrays.asList(s1).indexOf(s1[sp+1])==(sp+1)){
//				 sp= Arrays.asList(s1).lastIndexOf(s1[sp+1]);
//			 }
//			 else{
//				  sp= Arrays.asList(s1).indexOf(s1[sp+1]);
//			 }
			   String[] str=s1;
			   int index=sp+1;
			   String str1=s1[sp+1];
			   int sp1=0;
				 int i=-1; 
			   while(true){
					  i++;
					  //System.out.println(i);
					   sp=Arrays.asList(str).indexOf(str1);
					   sp1+=sp;
//					   System.out.println(sp);
//					   System.out.println(sp1);
					  // System.out.println(s[sp]+s[sp+1]+s[sp-1]);
					   if(sp==0){
						   break;
					   }
					   else if(str[sp].equals(str1) && (!str[sp-1].equals("jnz")) && (!str[sp-1].equals("loop")) && (!str[sp-1].equals("jmp")) && (!str[sp-1].equals("je")) && (!str[sp-1].equals("ja")) && (!str[sp-1].equals("jne")) && (!str[sp-1].equals("jle")) ){
						  // System.out.println("lllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllll");
						  // System.out.println(s1[sp]+s1[sp+1]+s1[sp-1]);
//						   System.out.println(s[sp]+s[sp+1]+s[sp-1]);
						   
						   break;
					   }else{
//						   List<String> list = new ArrayList<String>(Arrays.asList(str));
//						   list.subList(sp+1, str.length-1);
//						   str = list.toArray(new String[0]);
						   
						   str=Arrays.copyOfRange(str, sp+1, str.length-1);
						   //System.out.println(str[0]);
					   }
				   }
				   sp=sp1+i;
				  
				  
		        
				   
		  
		   
		  // sp+=2;
		  
	   }
	   else if(s.equals("ja")){
		   System.out.println(s1[sp]+" "+s1[sp+1]+"  "+s1[sp+2]);
		  // System.out.println(" "+s+" "+s1[sp+1]);
//		   try {
//				TimeUnit.SECONDS.sleep(1);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		   if(cmpabove){
			   String[] str=s1;
			   int index=sp+1;
			   String str1=s1[sp+1];
			   int sp1=0;
				 int i=-1; 
			   while(true){
					  i++;
					  //System.out.println(i);
					   sp=Arrays.asList(str).indexOf(str1);
					   sp1+=sp;
//					   System.out.println(sp);
//					   System.out.println(sp1);
					  // System.out.println(s[sp]+s[sp+1]+s[sp-1]);
					   if(sp==0){
						   break;
					   }
					   else if(str[sp].equals(str1) && (!str[sp-1].equals("jnz")) && (!str[sp-1].equals("loop")) && (!str[sp-1].equals("jmp")) && (!str[sp-1].equals("je")) && (!str[sp-1].equals("ja")) && (!str[sp-1].equals("jne")) && (!str[sp-1].equals("jle")) ){
						  // System.out.println("lllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllll");
//						   System.out.println(s[sp]+s[sp+1]+s[sp-1]);
//						   System.out.println(s[sp]+s[sp+1]+s[sp-1]);
						   
						   break;
					   }else{
//						   List<String> list = new ArrayList<String>(Arrays.asList(str));
//						   list.subList(sp+1, str.length-1);
//						   str = list.toArray(new String[0]);
						   
						   str=Arrays.copyOfRange(str, sp+1, str.length-1);
						  // System.out.println(str[0]);
					   }
				   }
				   sp=sp1+i;
				  
			   cmpfalse();
		   }
		   else{
			   sp+=2; 
			   
			  // System.out.println("ja finish()");
			  
		   }
		   
	   }
	   else if(s.equals("je")){
		   System.out.println(s1[sp]+" "+s1[sp+1]+"  "+s1[sp+2]);
		 //  System.out.println("je done");
//		   try {
//				TimeUnit.SECONDS.sleep(1);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		  // System.out.println("je done");
		   if(cmpaequal){
			   String[] str=s1;
			   int index=sp+1;
			   String str1=s1[sp+1];
			   int sp1=0;
				 int i=-1; 
			   while(true){
					  i++;
					 // System.out.println(i);
					   sp=Arrays.asList(str).indexOf(str1);
					   sp1+=sp;
//					   System.out.println(sp);
//					   System.out.println(sp1);
					  // System.out.println(s[sp]+s[sp+1]+s[sp-1]);
					   if(sp==0){
						   break;
					   }
					   else if(str[sp].equals(str1) && (!str[sp-1].equals("jnz")) && (!str[sp-1].equals("loop")) && (!str[sp-1].equals("jmp")) && (!str[sp-1].equals("je")) && (!str[sp-1].equals("ja")) && (!str[sp-1].equals("jne")) && (!str[sp-1].equals("jle")) ){
						 //  System.out.println("lllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllll");
//						   System.out.println(s[sp]+s[sp+1]+s[sp-1]);
//						   System.out.println(s[sp]+s[sp+1]+s[sp-1]);
						   
						   break;
					   }else{
//						   List<String> list = new ArrayList<String>(Arrays.asList(str));
//						   list.subList(sp+1, str.length-1);
//						   str = list.toArray(new String[0]);
						   
						   str=Arrays.copyOfRange(str, sp+1, str.length-1);
						  // System.out.println(str[0]);
					   }
				   }
				   sp=sp1+i;
			   cmpfalse();
		   }
		   else{
			   sp+=2; 
			   
			//   System.out.println("ja finish()");
			  
		   }
		  
	   }
	   else if(s.equals("jne")){
		   System.out.println(s1[sp]+" "+s1[sp+1]+"  "+s1[sp+2]);
		   
//		   try {
//				TimeUnit.SECONDS.sleep(1);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		  // System.out.println("jne done");
		   if(cmpanequal){
			  
			   
			   String[] str=s1;
			   int index=sp+1;
			   String str1=s1[sp+1];
			   int sp1=0;
				 int i=-1; 
			   while(true){
					  i++;
					 // System.out.println(i);
					   sp=Arrays.asList(str).indexOf(str1);
					   sp1+=sp;
//					   System.out.println(sp);
//					   System.out.println(sp1);
					  // System.out.println(s[sp]+s[sp+1]+s[sp-1]);
					   if(sp==0){
						   break;
					   }
					   else if(str[sp].equals(str1) && (!str[sp-1].equals("jnz")) && (!str[sp-1].equals("loop")) && (!str[sp-1].equals("jmp")) && (!str[sp-1].equals("je")) && (!str[sp-1].equals("ja")) && (!str[sp-1].equals("jne")) && (!str[sp-1].equals("jle")) ){
						  // System.out.println("lllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllll");
//						   System.out.println(s[sp]+s[sp+1]+s[sp-1]);
//						   System.out.println(s[sp]+s[sp+1]+s[sp-1]);
						   
						   break;
					   }else{
//						   List<String> list = new ArrayList<String>(Arrays.asList(str));
//						   list.subList(sp+1, str.length-1);
//						   str = list.toArray(new String[0]);
						   
						   str=Arrays.copyOfRange(str, sp+1, str.length-1);
						 //  System.out.println(str[0]);
					   }
				   }
				   sp=sp1+i;
				  
			   cmpfalse();
		   }
		   else{
			   sp+=2; 
			   
			//   System.out.println("ja finish()");
			  
		   }
//		   if(cmpaequal){
//			   sp= Arrays.asList(s1).indexOf(s1[sp+1]);
//			     // System.out.println("ja done "+s1[sp]);
//			      cmpfalse();
//		   }
//		   else{
			   
		  // }
		   
	   }
	   else if(s.equals("jle")){
		   System.out.println(s1[sp]+" "+s1[sp+1]+"  "+s1[sp+2]);
		 //  System.out.println("jle done");
//		   try {
//				TimeUnit.SECONDS.sleep(1);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		  
//		   if(cmpalequal){
//			   if(Arrays.asList(s1).indexOf(s1[sp+1])==(sp+1)){
//					 sp= Arrays.asList(s1).lastIndexOf(s1[sp+1]);
//				 }
//				 else{
//					  sp= Arrays.asList(s1).indexOf(s1[sp+1]);
//				 }
//			   System.out.println("jlee done");
//			   cmpfalse();
//		      
//		   }
		   if(cmpalequal){
			   String[] str=s1;
			   int index=sp+1;
			   String str1=s1[sp+1];
			   int sp1=0;
				 int i=-1; 
			   while(true){
					  i++;
					//  System.out.println(i);
					   sp=Arrays.asList(str).indexOf(str1);
					   sp1+=sp;
//					   System.out.println(sp);
//					   System.out.println(sp1);
					  // System.out.println(s[sp]+s[sp+1]+s[sp-1]);
					   if(sp==0){
						   break;
					   }
					   else if(str[sp].equals(str1) && (!str[sp-1].equals("jnz")) && (!str[sp-1].equals("loop")) && (!str[sp-1].equals("jmp")) && (!str[sp-1].equals("je")) && (!str[sp-1].equals("ja")) && (!str[sp-1].equals("jne")) && (!str[sp-1].equals("jle")) ){
						 //  System.out.println("lllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllll");
//						   System.out.println(s[sp]+s[sp+1]+s[sp-1]);
//						   System.out.println(s[sp]+s[sp+1]+s[sp-1]);
						   
						   break;
					   }else{
//						   List<String> list = new ArrayList<String>(Arrays.asList(str));
//						   list.subList(sp+1, str.length-1);
//						   str = list.toArray(new String[0]);
						   
						   str=Arrays.copyOfRange(str, sp+1, str.length-1);
						   //System.out.println(str[0]);
					   }
				   }
				   sp=sp1+i;
				  
			   cmpfalse();
		   }
		   else{
			   sp+=2; 
			   
			  // System.out.println("jle finish()");
			  
		   }
	   }
	   else if(s.equals("jnz")){
		   System.out.println(s1[sp]+" "+s1[sp+1]+"  "+zf);
//		   try {
//				TimeUnit.SECONDS.sleep(1);
//				
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		  // System.out.println("jnz done");
		  if(zf=='0'){
			  String[] str=s1;
			   int index=sp+1;
			   String str1=s1[sp+1];
			   int sp1=0;
				 int i=-1; 
			   while(true){
					  i++;
					  //System.out.println(i);
					   sp=Arrays.asList(str).indexOf(str1);
					   sp1+=sp;
//					   System.out.println(sp);
//					   System.out.println(sp1);
					  // System.out.println(s[sp]+s[sp+1]+s[sp-1]);
					   if(sp==0){
						   break;
					   }
					   else if(str[sp].equals(str1) && (!str[sp-1].equals("jmp")) && (!str[sp-1].equals("je")) && (!str[sp-1].equals("ja")) && (!str[sp-1].equals("jne")) && (!str[sp-1].equals("jle")) && (!str[sp-1].equals("loop")) && (!str[sp-1].equals("jnz"))){
						   //System.out.println("lllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllll");
//						   System.out.println(s[sp]+s[sp+1]+s[sp-1]);
//						   System.out.println(s[sp]+s[sp+1]+s[sp-1]);
						   
						   break;
					   }else{
//						   List<String> list = new ArrayList<String>(Arrays.asList(str));
//						   list.subList(sp+1, str.length-1);
//						   str = list.toArray(new String[0]);
						   
						   str=Arrays.copyOfRange(str, sp+1, str.length-1);
						   //System.out.println(str[0]);
					   }
				   }
				   sp=sp1+i;
				   zf='1';
				  
		  }
		 
		  else{
			  sp+=2;
		  }
	   }
	   else if(s.equals("loop")){
		   System.out.println(s1[sp]+" "+s1[sp+1]+"  "+s1[sp+2]);
		  // System.out.println("loooppppppppppp");
//		   try {
//				TimeUnit.SECONDS.sleep(1);
//				
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		   Reg[aMap.get("cx")]=rep( Reg[aMap.get("cx")]);
		   int b=Integer.parseInt(Reg[aMap.get("cx")],16);
		   Reg[aMap.get("cx")]=Reg[aMap.get("cx")]+"h";
		   if(b>1){
			   String[] str=s1;
			   int index=sp+1;
			   String str1=s1[sp+1];
			   int sp1=0;
				 int i=-1; 
			   while(true){
					  i++;
					  //System.out.println(i);
					   sp=Arrays.asList(str).indexOf(str1);
					   sp1+=sp;
//					   System.out.println(sp);
//					   System.out.println(sp1);
					  // System.out.println(s[sp]+s[sp+1]+s[sp-1]);
					   if(sp==0){
						   break;
					   }
					   else if(str[sp].equals(str1) && (!str[sp-1].equals("jmp")) && (!str[sp-1].equals("je")) &&(!str[sp-1].equals("loop")) && (!str[sp-1].equals("ja")) && (!str[sp-1].equals("jne")) && (!str[sp-1].equals("jle")) ){
						 //  System.out.println("0000000000000000000lllllllllllllllllllllllllllllllllllllllllllllllllll");
						//   System.out.println(s1[sp]+s1[sp+1]+s1[sp-1]);
//						   System.out.println(s[sp]+s[sp+1]+s[sp-1]);
						   
						   break;
					   }else{
//						   List<String> list = new ArrayList<String>(Arrays.asList(str));
//						   list.subList(sp+1, str.length-1);
//						   str = list.toArray(new String[0]);
						   
						   str=Arrays.copyOfRange(str, sp+1, str.length-1);
						   //System.out.println(str[0]);
					   }
				   }
				   sp=sp1+i;
				  
		   }
		   else{
			   sp+=2;
		   }
		   dec("cx");
		  
		   
	   }
	   
	   
	   else {
		  // System.out.println("done");
		   sp++;
	   }
   }
   
   public void mov(String s1,String s2){
	   if(aMap.get(s2)==null){
		   Reg[aMap.get(s1)]=s2;
	   }
	   else{
		   Reg[aMap.get(s1)]=Reg[aMap.get(s2)];
	   }
   }
   public void out(String s1,String s2){
	   if(aMap.get(s2)==null){
		   s2=rep(s2);
		   int b=Integer.parseInt(s2,16);
		   Reg[aMap.get(s1)]=Integer.toBinaryString(b);
		   for(int i=Reg[aMap.get(s1)].length();i<8;i++){
			   Reg[aMap.get(s1)]="0"+Reg[aMap.get(s1)];
			  }
	   }
	   else{
		   Reg[aMap.get(s2)]=rep( Reg[aMap.get(s2)]);
		   
		   int b=Integer.parseInt(Reg[aMap.get(s2)],16);
		  
		   Reg[aMap.get(s1)]=Integer.toBinaryString(b);
		   //System.out.println("amra jaaaaaaaaaaaaaaaaaaaaaa chaiiiiiiiiiiiiiiiii"+Reg[aMap.get(s1)]);
		   for(int i=Reg[aMap.get(s1)].length();i<8;i++){
			   Reg[aMap.get(s1)]="0"+Reg[aMap.get(s1)];
			  }
		  // System.out.println("amra jaaaaaaaaaaaaaaaaaaaaaa chaiiiiiiiiiiiiiiiii"+Reg[aMap.get(s1)]+s1);
		   Reg[aMap.get(s2)]= Reg[aMap.get(s2)]+"h";
	   }
   }
   public void add(String s1,String s2){
	 //  System.out.println(Reg[aMap.get("si")]);
	   if(aMap.get(s2)==null){
		   Reg[aMap.get(s1)]=rep( Reg[aMap.get(s1)]);
		   s2=rep(s2);
		   int a;
		   int b;
		   try{
		    a=Integer.parseInt(Reg[aMap.get(s1)],16);
		   }catch(Exception e){
			   a=0;
		   }
		   try{
			    b=Integer.parseInt(s2,16);
			   }catch(Exception e){
				   b=0;
			   }
		   
		   a=a+b;
		   if(a==0){
			   zf='1';
		   }
		   else{
			   zf='0';
		   }
		   Reg[aMap.get(s1)]=Integer.toHexString(a)+"h";
	   }
	   else{
		   Reg[aMap.get(s1)]=rep( Reg[aMap.get(s1)]);
		   Reg[aMap.get(s2)]=rep( Reg[aMap.get(s2)]);
		   int a;
		   int b;
		   try{
		    a=Integer.parseInt(Reg[aMap.get(s1)],16);
		   }catch(Exception e){
			   a=0;
		   }
		   try{
			    b=Integer.parseInt(Reg[aMap.get(s2)],16);
			   }catch(Exception e){
				   b=0;
			   }
		   a=a+b;
		   if(a==0){
			   zf='1';
		   }
		   else{
			   zf='0';
		   }
		   Reg[aMap.get(s1)]=Integer.toHexString(a)+"h";
		   Reg[aMap.get(s2)]= Reg[aMap.get(s2)]+"h";
	   }
	   //System.out.println(Reg[aMap.get("si")]);
   }
//   public int add1(String s1,String s2){
//	   if(aMap.get(s2)==null){
//		   Reg[aMap.get(s1)]=rep( Reg[aMap.get(s1)]);
//		   s2=rep(s2);
//		   int a=Integer.parseInt(Reg[aMap.get(s1)],16);
//		   int b=Integer.parseInt(s2,16);
//		   a=a+b;
//		   //Reg[aMap.get(s1)]=Integer.toHexString(a)+"h";
//	   }
//	   else{
//		   Reg[aMap.get(s1)]=rep( Reg[aMap.get(s1)]);
//		   Reg[aMap.get(s2)]=rep( Reg[aMap.get(s2)]);
//		   int a=Integer.parseInt(Reg[aMap.get(s1)],16);
//		   int b=Integer.parseInt(Reg[aMap.get(s2)],16);
//		   return a=a+b;
//		   
//		   //Reg[aMap.get(s1)]=Integer.toHexString(a)+"h";
//	   }
//   }
   public void sub(String s1,String s2){
	   if(aMap.get(s2)==null){
		   Reg[aMap.get(s1)]=rep( Reg[aMap.get(s1)]);
		   s2=rep(s2);
		   int a;
		   int b;
		   try{
		    a=Integer.parseInt(Reg[aMap.get(s1)],16);
		   }catch(Exception e){
			   a=0;
		   }
		   try{
			    b=Integer.parseInt(s2,16);
			   }catch(Exception e){
				   b=0;
			   }
		   
		   a=a-b;
		   if(a==0){
			   zf='1';
		   }
		   else{
			   zf='0';
		   }
		   Reg[aMap.get(s1)]=Integer.toHexString(a)+"h";
	   }
	   else{
		   Reg[aMap.get(s1)]=rep( Reg[aMap.get(s1)]);
		   Reg[aMap.get(s2)]=rep( Reg[aMap.get(s2)]);
		   int a;
		   int b;
		   try{
		    a=Integer.parseInt(Reg[aMap.get(s1)],16);
		   }catch(Exception e){
			   a=0;
		   }
		   try{
			    b=Integer.parseInt(Reg[aMap.get(s2)],16);
			   }catch(Exception e){
				   b=0;
			   }
		   a=a-b;
		   if(a==0){
			   zf='1';
		   }
		   else{
			   zf='0';
		   }
		   Reg[aMap.get(s1)]=Integer.toHexString(a)+"h";
		   Reg[aMap.get(s2)]= Reg[aMap.get(s2)]+"h";
	   }
   }
   public void inc(String s1){
	   Reg[aMap.get(s1)]=rep(Reg[aMap.get(s1)]);
	   int a=Integer.parseInt(Reg[aMap.get(s1)],16);
	   a=a+1;
	   if(a==0){
		   zf='1';
	   }
	   else{
		   zf='0';
	   }
	   Reg[aMap.get(s1)]=Integer.toHexString(a)+"h";
   }
   public void dec(String s1){
	  // System.out.println("it issssshhhhhhhhhhhssssssssss a::::"+ Reg[aMap.get(s1)]);
	   Reg[aMap.get(s1)]=rep(Reg[aMap.get(s1)]);
	   //System.out.println("it issssshhhhhhhhhhhssssssssss a::::"+ Reg[aMap.get(s1)]);
	  // System.out.println(Reg[aMap.get(s1)]);
	   int a=Integer.parseInt(Reg[aMap.get(s1)],16);
	   a=a-1;
	  // System.out.println("it issssshhhhhhhhhhhssssssssss a"+);
	   //if(a>0){
	   if(a==0){
		   zf='1';
	   }
	   else{
		   zf='0';
	   }   
	   Reg[aMap.get(s1)]=Integer.toHexString(a)+"h";
	  // System.out.println("it issssshhhhhhhhhhhssssssssss c::::"+ Reg[aMap.get(s1)]);
	  // }
	   //else{
		   //Reg[aMap.get(s1)]="0"+"h";
	  // }
   }
   public void xor(String s1 ,String s2){
	   if(aMap.get(s2)==null){
		   Reg[aMap.get(s1)]=rep( Reg[aMap.get(s1)]);
		   s2=rep(s2);
		   int a;
		   int b;
		   try{
		    a=Integer.parseInt(Reg[aMap.get(s1)],16);
		   }catch(Exception e){
			   a=0;
		   }
		   try{
			    b=Integer.parseInt(s2,16);
			   }catch(Exception e){
				   b=0;
			   }
		   
		   a=a^b;
		   if(a==0){
			   zf='1';
		   }
		   else{
			   zf='0';
		   }
		   Reg[aMap.get(s1)]=Integer.toHexString(a)+"h";
	   }
	   else{
		///  System.out.println( "kkkkkk"+Reg[aMap.get(s1)]);
		   Reg[aMap.get(s1)]=rep( Reg[aMap.get(s1)]);
		   Reg[aMap.get(s2)]=rep( Reg[aMap.get(s2)]);
		  // System.out.println( "hhhhhhh"+Integer.parseInt(Reg[aMap.get(s1)],16));
		   int a;
		   int b;
		   try{
		    a=Integer.parseInt(Reg[aMap.get(s1)],16);
		   }catch(Exception e){
			   a=0;
		   }
		   try{
			    b=Integer.parseInt(Reg[aMap.get(s2)],16);
			   }catch(Exception e){
				   b=0;
			   }
		   
		   a=a^b;
		   if(a==0){
			   zf='1';
		   }
		   else{
			   zf='0';
		   }
		   Reg[aMap.get(s1)]=Integer.toHexString(a)+"h";
		   Reg[aMap.get(s2)]= Reg[aMap.get(s2)]+"h";
	   }
   }
   public void or(String s1 ,String s2){
	   if(aMap.get(s2)==null){
		   Reg[aMap.get(s1)]=rep( Reg[aMap.get(s1)]);
		   s2=rep(s2);
		   int a;
		   int b;
		   try{
		    a=Integer.parseInt(Reg[aMap.get(s1)],16);
		   }catch(Exception e){
			   a=0;
		   }
		   try{
			    b=Integer.parseInt(s2,16);
			   }catch(Exception e){
				   b=0;
			   }
		   
		   a=a|b;
		   if(a==0){
			   zf='1';
		   }
		   else{
			   zf='0';
		   }
		   Reg[aMap.get(s1)]=Integer.toHexString(a)+"h";
	   }
	   else{
		   Reg[aMap.get(s1)]=rep( Reg[aMap.get(s1)]);
		   Reg[aMap.get(s2)]=rep( Reg[aMap.get(s2)]);
		   int a;
		   int b;
		   try{
		    a=Integer.parseInt(Reg[aMap.get(s1)],16);
		   }catch(Exception e){
			   a=0;
		   }
		   try{
			    b=Integer.parseInt(Reg[aMap.get(s2)],16);
			   }catch(Exception e){
				   b=0;
			   }
		   a=a|b;
		   if(a==0){
			   zf='1';
		   }
		   else{
			   zf='0';
		   }
		   Reg[aMap.get(s1)]=Integer.toHexString(a)+"h";
		   Reg[aMap.get(s2)]= Reg[aMap.get(s2)]+"h";
	   }
   }
   public void and(String s1 ,String s2){
	   if(aMap.get(s2)==null){
		   Reg[aMap.get(s1)]=rep( Reg[aMap.get(s1)]);
		   s2=rep(s2);
		   int a;
		   int b;
		   try{
		    a=Integer.parseInt(Reg[aMap.get(s1)],16);
		   }catch(Exception e){
			   a=0;
		   }
		   try{
			    b=Integer.parseInt(s2,16);
			   }catch(Exception e){
				   b=0;
			   }
		   
		   a=a&b;
		   if(a==0){
			   zf='1';
		   }
		   else{
			   zf='0';
		   }
		   Reg[aMap.get(s1)]=Integer.toHexString(a)+"h";
	   }
	   else{
		   Reg[aMap.get(s1)]=rep( Reg[aMap.get(s1)]);
		   Reg[aMap.get(s2)]=rep( Reg[aMap.get(s2)]);
		   int a;
		   int b;
		   try{
		    a=Integer.parseInt(Reg[aMap.get(s1)],16);
		   }catch(Exception e){
			   a=0;
		   }
		   try{
			    b=Integer.parseInt(Reg[aMap.get(s2)],16);
			   }catch(Exception e){
				   b=0;
			   }
		   a=a&b;
		   if(a==0){
			   zf='1';
		   }
		   else{
			   zf='0';
		   }
		   Reg[aMap.get(s1)]=Integer.toHexString(a)+"h";
		   Reg[aMap.get(s2)]= Reg[aMap.get(s2)]+"h";
	   }
   }
   public void neg(String s1){
	  boolean firstone=false;
	  Reg[aMap.get(s1)]=rep( Reg[aMap.get(s1)]);
	  int a=Integer.parseInt(Reg[aMap.get(s1)],16);
	 String s=Integer.toBinaryString(a);
	 //System.out.println(s);
	 for(int i=s.length();i<8;i++){
		  s="0"+s;
	  }
	  char [] s2=s.toCharArray();
	 // System.out.println(s2);
	  for(int i=s2.length-1;i>=0;i--){
		  if(firstone){
			  if(s2[i]=='1'){
				  s2[i]='0';
			  }else {
				  s2[i]='1';
			  }
			// System.out.print("vhitore ");
		  }
		  else if(s2[i]=='1'){
			  firstone=true;
			  //System.out.print(s2[i]+"baire ");
		  }
	  }
	  s=String.valueOf(s2);
	 // System.out.println(s);
	  int b=Integer.parseInt(s,2);
	  if(b==0){
		   zf='1';
	   }
	   else{
		   zf='0';
	   }
	  Reg[aMap.get(s1)]=Integer.toHexString(b)+"h";
   }
   public void not(String s1){
	   Reg[aMap.get(s1)]=rep( Reg[aMap.get(s1)]);
		  int a=Integer.parseInt(Reg[aMap.get(s1)],16);
		  String s=Integer.toBinaryString(a);
		  for(int i=s.length();i<8;i++){
			  s="0"+s;
		  }
		 // System.out.println(s);
		  char [] s2=s.toCharArray();
		//  System.out.println(s2);
		  for(int i=s2.length-1;i>=0;i--){
			 
				  if(s2[i]=='1'){
					  s2[i]='0';
				  }else {
					  s2[i]='1';
				  }
				
			 
			 
		  }
		  s=String.valueOf(s2);
		//  System.out.println(s);
		  int b=Integer.parseInt(s,2);
		  if(b==0){
			   zf='1';
		   }
		   else{
			   zf='0';
		   }
		  Reg[aMap.get(s1)]=Integer.toHexString(b)+"h";
   }
   public void shl(String s1,String s4){
	   int k;
	   if(aMap.get(s4)==null){
		   
		   s4=rep(s4);
		   k=Integer.parseInt(s4,16);
		   
		   //System.out.println(k);
	   Reg[aMap.get(s1)]=rep( Reg[aMap.get(s1)]);
	  // System.out.println(Reg[aMap.get(s1)]);
	   int a=Integer.parseInt(Reg[aMap.get(s1)],16);
		  String s=Integer.toBinaryString(a);
		  for(int i=s.length();i<8;i++){
			  s="0"+s;
		  }
		//  System.out.println(s);
		  char [] s2=s.toCharArray();
	  //System.out.println(s2);
		  for(int q=1;q<=k;q++){
		  cf=s2[0];
			  
		  for(int i=0;i<s2.length-1;i++){
			  
			 
			 s2[i]=s2[i+1];
				  
		  }
		 s2[s2.length-1]='0';
		 // s2[s2.length-1]='1';
		  }
		 s=String.copyValueOf(s2);
		  //s=String.valueOf(s2);
		 // System.out.println(s2);
		 // System.out.println(cf);
		  int b=Integer.parseInt(s,2);
		 // System.out.println(b);
		  if(b==0){
			   zf='1';
			  
		   }
		   else{
			   zf='0';
		   }
		  
		  Reg[aMap.get(s1)]=Integer.toHexString(b)+"h";
		  //System.out.println(Reg[aMap.get(s1)]);
	   }
	   else{
		   Reg[aMap.get(s4)]=rep( Reg[aMap.get(s4)]);
		   k=Integer.parseInt(Reg[aMap.get(s4)],16);
		   Reg[aMap.get(s1)]=rep( Reg[aMap.get(s1)]);
		   int a=Integer.parseInt(Reg[aMap.get(s1)],16);
			  String s=Integer.toBinaryString(a);
			  for(int i=s.length();i<8;i++){
				  s="0"+s;
			  }
			  //System.out.println(s);
			  char [] s2=s.toCharArray();
		 // System.out.println(s2);
			  for(int q=1;q<=k;q++){
			  cf=s2[0];
			  for(int i=0;i<s2.length-1;i++){
				  
				 
				 s2[i]=s2[i+1];
					  
			  }
			  s2[s2.length-1]='0';
			  }
			  //s=String.valueOf(s2);
			 // System.out.println(s2);
			  s=String.copyValueOf(s2);
			 // System.out.println(cf);
			  int b=Integer.parseInt(s,2);
			  if(b==0){
				   zf='1';
			   }
			   else{
				   zf='0';
			   }
			  Reg[aMap.get(s1)]=Integer.toHexString(b)+"h";
			  Reg[aMap.get(s4)]=Reg[aMap.get(s4)]+"h";
	   }
	  // System.out.println(zf);
   }
   public void shr(String s1,String s4){
	   int k;
	   if(aMap.get(s4)==null){
		   
		   s4=rep(s4);
		   k=Integer.parseInt(s4,16);
		   Reg[aMap.get(s1)]=rep( Reg[aMap.get(s1)]);
		   int a=Integer.parseInt(Reg[aMap.get(s1)],16);
		  
			  String s=Integer.toBinaryString(a);
			  for(int i=s.length();i<8;i++){
				  s="0"+s;
			  }
			 // System.out.println(s);
			  char [] s2=s.toCharArray();
		 // System.out.println(s2);
			 for(int q=1;q<=k;q++){ 
			  cf=s2[s2.length-1];
			  for(int i=s2.length-1;i>0;i--){
				  
				 
				 s2[i]=s2[i-1];
					  
			  }
			  s2[0]='0';
			 }
			  
			 // System.out.println(s2);
			//  System.out.println(cf);
			  s=String.copyValueOf(s2);
			  int b=Integer.parseInt(s,2);
			  if(b==0){
				   zf='1';
			   }
			   else{
				   zf='0';
			   }
			  Reg[aMap.get(s1)]=Integer.toHexString(b)+"h";
	   }else{
		   Reg[aMap.get(s4)]=rep( Reg[aMap.get(s4)]);
		   k=Integer.parseInt(Reg[aMap.get(s4)],16);
		   Reg[aMap.get(s1)]=rep( Reg[aMap.get(s1)]);
		   int a=Integer.parseInt(Reg[aMap.get(s1)],16);
		  
			  String s=Integer.toBinaryString(a);
			  for(int i=s.length();i<8;i++){
				  s="0"+s;
			  }
			 // System.out.println(s);
			  char [] s2=s.toCharArray();
		 // System.out.println(s2);
			 for(int q=1;q<=k;q++){ 
			  cf=s2[s2.length-1];
			  for(int i=s2.length-1;i>0;i--){
				  
				 
				 s2[i]=s2[i-1];
					  
			  }
			  s2[0]='0';
			 }
			  
			//  System.out.println(s2);
			  s=String.copyValueOf(s2);
			//  System.out.println(cf);
			  int b=Integer.parseInt(s,2);
			  if(b==0){
				   zf='1';
			   }
			   else{
				   zf='0';
			   }
			  Reg[aMap.get(s1)]=Integer.toHexString(b)+"h";
			  Reg[aMap.get(s4)]=Reg[aMap.get(s4)]+"h";
	   }
	  
   }
   public void pushh(String s1){
	  // Reg[aMap.get(s1)]=rep( Reg[aMap.get(s1)]);
	   stack.push(Reg[aMap.get(s1)]);
   }
   public void pop(String s1){
	   Reg[aMap.get(s1)]=stack.pop();
   }
   public String rep(String s){
	   System.out.println(s.length());
	   return s.substring(0,s.length() - 1);
   }
   public void jmp(String s){
	  
   }
   public void cmp(String s1,String s2){
	   if(aMap.get(s2)==null){
		   Reg[aMap.get(s1)]=rep( Reg[aMap.get(s1)]);
		   s2=rep(s2);
		   int a;
		   int b;
		   try{
		    a=Integer.parseInt(Reg[aMap.get(s1)],16);
		   }catch(Exception e){
			   a=0;
		   }
		   try{
			    b=Integer.parseInt(s2,16);
			   }catch(Exception e){
				   b=0;
			   }
		   
		   if(a>b){
				  cmpabove=true;
					 cmpabelow=false;
					 cmpaequal=false;
					 cmpanequal=true;
					 cmpalequal=false;
					 cmpagequal=true;
			  }
			  else if(a<b){
				  cmpabove=false;
					 cmpabelow=true;
					 cmpaequal=false;
					 cmpanequal=true;
					 cmpalequal=true;
					 cmpagequal=false;
			  }else if(a==b){
				  cmpabove=false;
					 cmpabelow=false;
					 cmpaequal=true;
					 cmpanequal=false;
					 cmpalequal=true;
					 cmpagequal=true;
			  }
		  Reg[aMap.get(s1)]=Reg[aMap.get(s1)]+"h";
		   
	   }
	   else{
		   Reg[aMap.get(s1)]=rep( Reg[aMap.get(s1)]);
		   Reg[aMap.get(s2)]=rep( Reg[aMap.get(s2)]);
		   int a;
		   int b;
		   try{
		    a=Integer.parseInt(Reg[aMap.get(s1)],16);
		   }catch(Exception e){
			   a=0;
		   }
		   try{
			    b=Integer.parseInt(Reg[aMap.get(s2)],16);
			   }catch(Exception e){
				   b=0;
			   }
		  
		   if(a>b){
				  cmpabove=true;
					 cmpabelow=false;
					 cmpaequal=false;
					 cmpanequal=true;
					 cmpalequal=false;
					 cmpagequal=true;
			  }
			  else if(a<b){
				  cmpabove=false;
					 cmpabelow=true;
					 cmpaequal=false;
					 cmpanequal=true;
					 cmpalequal=true;
					 cmpagequal=false;
			  }else if(a==b){
				  cmpabove=false;
					 cmpabelow=false;
					 cmpaequal=true;
					 cmpanequal=false;
					 cmpalequal=true;
					 cmpagequal=true;
			  }
			 
		   Reg[aMap.get(s1)]=Reg[aMap.get(s1)]+"h";
		   Reg[aMap.get(s2)]=Reg[aMap.get(s2)]+"h";
	   }
   }
   public void cmpfalse(){
	     cmpabove=false;
		 cmpabelow=false;
		 cmpaequal=false;
		 cmpanequal=false;
		 cmpalequal=false;
		 cmpagequal=false;
   }
   
}

