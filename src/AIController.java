import sun.misc.IOUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.Scanner;

public class AIController {

        public static void main(String args[]) throws Exception{
//            Runtime runtime = Runtime.getRuntime();
//            Process process = runtime.exec("python /Users/oliverharrison/Projects/side_projects/FakeAI/MicrophoneSpeech.py");

            String fileName = "/Users/oliverharrison/Projects/side_projects/FakeAI/SpeechDetection.txt";
            File file = new File(fileName);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while((line = br.readLine()) != null){
                //process the line
                System.out.println(line);
//              Runtime runtime = Runtime.getRuntime();
                if(line.contains("youtube")){runtime.exec("open https://youtube.com");}
            }

        }

}
