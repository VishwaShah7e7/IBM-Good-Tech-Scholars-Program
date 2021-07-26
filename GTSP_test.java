import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
public class GTSP_test {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Timer timer = new Timer();
		timer.schedule(new SayHello(), 0, 5000);
	}
}
class SayHello extends TimerTask {
    @SuppressWarnings("resource")
	public void run() {
    	try 
	      {
    		File file = new File("C:\\Users\\amayk\\OneDrive\\Desktop\\TEST_1.txt");
    		BufferedReader br = new BufferedReader(new FileReader(file));
    		String st;
    		int Inside_temp  = 0;
    		 while ((st = br.readLine()) != null)
    		 {
    		   Inside_temp = Integer.valueOf(st);
    			 System.out.println(Inside_temp);
    		 }
		     
	       }
    	catch (IOException e) 
        {
           System.err.println("FileCopy: " + e);
        }
    }
}