import java.util.*;  import java.util.*;  
class CovidTransmission   
{  
public static void main(String[] args)  
{  

Scanner input = new Scanner(System.in);
int D1= input.nextInt();
int H1= input.nextInt();
int M1= input.nextInt();
int D2= input.nextInt();
int H2= input.nextInt();
int M2= input.nextInt();
int min = (60*(24*(D2-D1)+(H2-H1)))+(M2-M1);
if ((D1<=31 && D1>=1 && D2<=31 && D2>=1 && 23>=H1 && H1>=0 && 23>=H2 && H2>=0 && M1<=59 && M1>=0 && M2>=0 && M2<=59)&&(D1<D2 || (D1==D2 && H2>H1) || (D1==D2 && H2==H1 && M2>=M1))){
    if ( min>=0 && min<=60){
        System.out.print(min+" "+"low");
 }
  if ( min>60 && min<=180){
        System.out.print(min+" "+"medium");
 }
  if ( min>180 && min<=360){
        System.out.print(min+" "+"high");
 }
  if ( min>360){
        System.out.print(min+" "+"HIGH");
 }
}
else{
    System.out.print("-1"+" "+"-1");
}   


}  
}