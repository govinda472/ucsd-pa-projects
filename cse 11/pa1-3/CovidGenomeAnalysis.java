import java.util.*;
public class CovidGenomeAnalysis {
    public static void main(String [] args) {
        Scanner input = new Scanner(System.in);
        String str= input.nextLine();
        int T_count = 0;
		int count=0;
		int len;
		len = str.length();
		String out = "";
		for ( int i=0; i<len;){
		   if (i<len){ if (str.charAt(i) == 'A'){
       out= out+"T";
        i++;
            } }
            
            if (i<len){ if (str.charAt(i) == 'T'){
        out= out+"A";
        i++;
            } }
            
          if (i<len){  if (str.charAt(i) == 'C'){
        out= out+"G";
        i++;
            } }
           if (i<len){ 
               if (str.charAt(i) == 'G'){
        out= out+"C";
        i++;
            }}
    	}
		
		for ( int i=0; i<len; i++){ 
		    if (out.charAt(i) == 'T'){
		        
        T_count++;}
        
		    
		}

		System.out.print(T_count+" "+out);
    }
}