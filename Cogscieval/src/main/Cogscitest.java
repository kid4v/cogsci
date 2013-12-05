package main;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class Cogscitest {
	
public static void main(String[] args) {
	try{
		run();
	}
	catch(IOException e){
		System.out.print(e);
	}
}
public static void run() throws IOException{
	System.out.println("Please enter your full name: ");
	//read in 
	BufferedReader conIn = new BufferedReader(new InputStreamReader(System.in));
	PrintWriter log = new PrintWriter(new FileWriter("log.txt",true)); 
	BufferedReader in = new BufferedReader(new FileReader("sentences.txt")); 

	String name = conIn.readLine();
	System.out.println("Please complete the sentence with the with the appropiate word.");
	log.println(name);
	String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmm").format(Calendar.getInstance().getTime());
	log.println(timeStamp);
	System.out.println("For instance if the sentance was: Since i was out of gas I went to the gas s_____");
	System.out.print("type word here:");
	String response = conIn.readLine();
	if (response.contains("station")){
		System.out.println("Yes that is correct!");
	}
	else{
		System.out.println("Incorrect the anwser was station");
	}
	Random chooser = new Random();
	System.out.println("hit \"y\" when ready");
	while(true){
		if(conIn.readLine().contains("y")){
			break;
		}
	}
	String words;
	int i;
	String sentence;
	while (in.ready()) { 
		  Boolean correct = false;
		  words = in.readLine(); 
		  String[] acceptable = words.split(" ");
		  //if i=0 its the verb sentence if i=1 then its the noun sentence
		  i = (chooser.nextInt(1));
		  sentence = in.readLine();
		  if (i==1){
			  sentence = in.readLine();
		  }
		  long start,stop; 
		  System.out.print(sentence+"\n");
		  start = System.currentTimeMillis();
		  response = conIn.readLine();
		  stop = System.currentTimeMillis();
		  for(String cor: acceptable){
			  if (response.contains(cor)) correct = true;
		  }		  
		  if (correct){
			  //math to print out nonn or verb
			System.out.println("Yes that is correct!");
			i = 118-i*8;
			long time = start - stop;
			log.print(acceptable[0]+"\t"+ Character.toString((char)i)+"\t"+ time + "\n" );
		  }
		  else{
			System.out.println("We were looking for"+words);
			log.print(""+ acceptable[0] + "\t" + "word typed"+ "\t" + response + "\n");
		  }  
		  //safegard for EOF
		  if (i == 118||i==0){
			  in.readLine();
		  }
		  try{in.readLine();}
		  catch(Exception e){
			  break;
		  }
		  
		}

	System.out.println("Thank you for participatig in the first part of the study. The porgam will now exit. ");
	log.println("         \n");
	in.close();
	log.close();
	System.exit(0);
	
	
}
}
