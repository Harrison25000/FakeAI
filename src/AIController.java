import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Scanner;

public class AIController {

        public static void main(String args[]) throws Exception{

            Calendar c = Calendar.getInstance();
            int timeOfDay = c.get(Calendar.HOUR_OF_DAY);
            if (timeOfDay<12){System.out.println("morning");}
            else{System.out.println("evening");}


            Runtime runtime = Runtime.getRuntime();
            Process process = runtime.exec("python /Users/oliverharrison/Projects/side_projects/FakeAI/MicrophoneSpeech.py");
            Thread.sleep(3000);
            System.out.println("Pi listening...");
            Thread.sleep(10000);




            String fileName = "/Users/oliverharrison/Projects/side_projects/FakeAI/SpeechDetection.txt";
            File file = new File(fileName);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;

            while((line = br.readLine()) != null) {
                //process the line
                System.out.println(line);
                line = line.toLowerCase();
                System.out.println(line);

                if (line.contains("hey pi") || line.contains("hey pie") || line.contains("hey pai") || line.contains("hey bye") || line.contains("hey bi") || line.contains("hey die") || line.contains("hey dye") ) {

                    if (line.contains("youtube")) {
                        runtime.exec("open https://youtube.com");
                    }
                    if (line.contains("bbc")) {
                        runtime.exec("open https://bbc.com");
                    }
                    if (line.contains("facebook")) {
                        runtime.exec("open https://facebook.com");
                    }
                    if (line.contains("gmail")) {
                        runtime.exec("open https://gmail.com");
                    }
                    if (line.contains("internet") || line.contains("google")) {
                        runtime.exec("open https://google.com");
                    }
                    if (line.contains("amazon")) {
                        runtime.exec("open https://amazon.co.uk");
                    }
                    if (line.contains("netflix")) {
                        runtime.exec("open https://netflix.co.uk");
                    }

                }
                else{System.out.println("You talking to Pi?!");}
            }

        }

}
