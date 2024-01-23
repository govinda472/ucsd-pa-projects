import java.util.*;
public class CovidMutation {
    public static void main(String [] args) {
    Scanner input = new Scanner(System.in);
    String str= input.nextLine();
    int K= input.nextInt();
    int Len;
	Len = str.length();
	int remainder;
	int run;
	int p;
	String out = "";
    
    if (!( str instanceof java.lang.String) || !(K>=1)){
        System.out.println(str);
    }
    
    
    if (( str instanceof java.lang.String)&&(K>=1)){
    remainder = (Len%K);
	run = (Len-remainder)/K;
    
    
    if ((Len>K) || (Len==K)){
    
    if (remainder>=0 ){
       for ( int r = 0; r<run; r++){
         for ( int i = (K-1); i>=0; i--){
             
            p = ((K*(r))+i);
           out= out+str.charAt(p);
        }
        
       }
     if(remainder>0 ){  
       for ( int i = (remainder-1); i>=0; i--){
           p = (K*(run)+i);
           out= out+str.charAt(p);
       }
       
    }
    }
    
    }
    if (!((Len>K) || (Len==K))){
        for ( int i = (Len-1); i>=0; i--){
            out= out+str.charAt(i);
        }
    }
    System.out.println(out);
    }
    
}
}