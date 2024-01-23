// Online Java Compiler
// Use this editor to write, compile and run your Java code online

import java.util.ArrayList;
class Minecraft {
    
    public static int[][] rotateFloorPlan(int[][] originalFloorPlan){
        
        if (!(originalFloorPlan==null)){
        int h = originalFloorPlan.length;
        int w = originalFloorPlan[0].length;
       int [][] newFloorPlan = new int[w][h];
       int r;
       
            for (int i=0; i<newFloorPlan.length; i++){  
            for (int j=(newFloorPlan[i].length-1); j>=0; --j){ 
                r= (h-j)-1;
                newFloorPlan[i][r] = originalFloorPlan [j][i];
                
                
            }  
            
        }
        
        return newFloorPlan;
        
       }
      else{
           return null;
      }
    }
    
    
    
 
    
    
    public static ArrayList<String> getMobsToTest(String[][] groups, String infected){
        String temp;
        ArrayList<String> list = new ArrayList<String>();
        list.add(infected);
        
        
        if ((!(groups==null))&&(!(infected==null))){
        for (int i=0; i<=(groups.length-1); i++){
            if (!(groups[i]==null)){
            for (int j=0; j<=(groups[i].length-1); j++){
        
                
                if ((!(groups[i][j]==null))&&groups[i][j].equals(infected)){
               
                    
                    for (int z=0; z<=(groups[i].length-1); z++){
                        
                        
                        for (int x=0; x<=(list.size()-1); x++){
                            
                               String repeat= "false";
                               temp= groups[i][z];
                               for (int e=0; e<=(list.size()-1); e++ ) {
                                   if (temp.equals(list.get(e))){
                                       repeat= "true";
                                  }
                               }
                               if (repeat=="false"){
                                
                                
                                list.add(temp);
                               }
                               
                        }
                        
                    }
                    
                }
            }
            
            
        } }
        list.remove(0);
        return list;
        }
        else{
           return null;
        }
    }
}