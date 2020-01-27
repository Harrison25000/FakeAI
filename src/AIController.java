package src;

import java.util.Locale; 
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Scanner;
import java.util.ArrayList;
//---Speech Imports (Need to add jar files)
import com.sun.speech.freetts.Voice; 
import com.sun.speech.freetts.VoiceManager; 
import javax.speech.Central; 
import javax.speech.EngineModeDesc;
import javax.speech.synthesis.Synthesizer; 
import javax.speech.synthesis.SynthesizerModeDesc; 


public class AIController {

	static String macOpen = "open ";
	static String rasOpen = "xdg-open ";
	static boolean mute = false;

        public static void main(String args[]) throws Exception{


            Runtime runtime = Runtime.getRuntime();
            Process process = runtime.exec("python /home/pi/Projects/FakeAI/MicrophoneSpeech.py");
            Thread.sleep(3000);
            System.out.println("Pi listening...");
            Thread.sleep(10000);




            String fileName = "/home/pi/Projects/FakeAI/SpeechDetection.txt";
            File file = new File(fileName);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
	    boolean percentage = false;

            while((line = br.readLine()) != null) {
                //process the line
                System.out.println(line);
                line = line.toLowerCase();
                System.out.println(line);
		String[] lineArray = line.split(" ");
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		for(String x : lineArray){
			if(x.contains("%")){
					    System.out.println("percentage sign included"); 
					    percentage = true;
					    x=x.replaceAll("%","");
			}
			System.out.println(x);
			if(x.contains("0") || x.contains("1") || x.contains("2") || x.contains("3") || x.contains("4") || x.contains("5") || x.contains("6") || x.contains("7") || x.contains("8") || x.contains("9")){
					Integer forArray = Integer.parseInt(x);
					numbers.add(forArray);
					System.out.println(numbers);
			}
		}

                if (line.contains("hey pi") || line.contains("hey pie") || line.contains("hey pai") || line.contains("hey bye") || line.contains("hey bi") || line.contains("hey die") || line.contains("hey dye") ) {

                    if (line.contains("youtube")) {
                        spokenPhrase("Opening You Tube");
                        runtime.exec(rasOpen + "https://youtube.com");
                    }
                    if (line.contains("bbc")) {
                        spokenPhrase("Opening the BBC");
                        runtime.exec(rasOpen + "https://bbc.com");
                    }
                    if (line.contains("facebook")) {
                        spokenPhrase("Opening FaceBook");
                        runtime.exec(rasOpen + "https://facebook.com");
                    }
                    if (line.contains("gmail")) {
                        spokenPhrase("Opening G Mail");
                        runtime.exec(rasOpen + "https://gmail.com");
                    }
                    if (line.contains("internet") || line.contains("google")) {
			spokenPhrase("Opening Google");
                        runtime.exec(rasOpen + "https://google.com");
                    }
                    if (line.contains("amazon")) {
			spokenPhrase("Opening Amazon");
                        runtime.exec(rasOpen + "https://amazon.co.uk");
                    }
                    if (line.contains("netflix")) {
			spokenPhrase("Opening Netflix");
                        runtime.exec(rasOpen + "https://netflix.co.uk");
                    }
		    if (line.contains("calculate")) {
                        if (line.contains(" x ") || line.contains("multiply")){
				System.out.println("multiplication");
				System.out.println(numbers.get(0)*numbers.get(1));			
			}
			if (line.contains(" + ") || line.contains("plus") || line.contains("add")){
				System.out.println("addition");
				System.out.println(numbers.get(0) + numbers.get(1));			
			}
			if (line.contains(" - ") || line.contains("minus") || line.contains("subtract") || line.contains("subtracted")){
				System.out.println("subtraction");
				System.out.println(numbers.get(0) - numbers.get(1));			
			}
			if (line.contains(" / ") || line.contains("divide") || line.contains("divided")){
				System.out.println("division");
				System.out.println(numbers.get(0)/numbers.get(1));			
			}
			if (percentage==true || line.contains("percent")){
				System.out.println("percentage");
				float f = numbers.get(0);
				double getPercentage = f/100;
				double totalPercentage = numbers.get(1)*getPercentage;
				double roundedTotalPercentage = Math.round(totalPercentage*100.0)/100.0;
				System.out.println(roundedTotalPercentage);
						
			}
                    }

                }
                else{System.out.println("You talking to Pi?!");}
            }

        }
        
        
         public static void spokenPhrase(String speechOut){
             


  
	        try { 
	            // Set property as Kevin Dictionary 
	            System.setProperty( 
	                "freetts.voices", 
	                "com.sun.speech.freetts.en.us"
	                    + ".cmu_us_kal.KevinVoiceDirectory"); 
	  
	            // Register Engine 
	            Central.registerEngineCentral( 
	                "com.sun.speech.freetts"
	                + ".jsapi.FreeTTSEngineCentral"); 
	  
	            // Create a Synthesizer 
	            Synthesizer synthesizer 
	                = Central.createSynthesizer( 
	                    new SynthesizerModeDesc(Locale.US)); 
	  
	            // Allocate synthesizer 
	            synthesizer.allocate(); 
	  
	            // Resume Synthesizer 
	            synthesizer.resume(); 
	  
	            // Speaks the given text 
	            // until the queue is empty. 
	            
	             synthesizer.speakPlainText(speechOut, null);
	             synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
	            

	  
	            // Deallocate the Synthesizer. 
	            synthesizer.deallocate(); 
	        } 
	  
	        catch (Exception e) { 
	            e.printStackTrace(); 
	        } 
    } 

}
