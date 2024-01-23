
public class MyArrayList<E> implements MyList<E>{ 
    Object[] data;
    int size;
    public Object length;

    //constructors
    public MyArrayList(){
        data= new Object[5];
    }

    public MyArrayList(int initialCapacity){
        if(initialCapacity<0){
            throw new IllegalArgumentException();
        }
        data= new Object[initialCapacity];
    }
    public MyArrayList (E[] arr){
        if(arr==null){
            //throw new IllegalArgumentException();
            data= new Object[5];

        }
        else{
            data= arr;
            size=arr.length;
        }

    }


    //methods


    //need to test and add expection
    public void expandCapacity (int requiredCapacity){
        if(requiredCapacity<data.length){
            throw new IllegalArgumentException();
        }
        Object[] temp_data;
            if(data.length>0){
                if(data.length>requiredCapacity-3){

                temp_data=new Object[data.length+3];
                for(int i=0; i<data.length; i++){
                    temp_data[i]=data[i];
                }
                data=temp_data;
                }

                else{
                    temp_data=new Object[data.length+3];
                for(int i=0; i<data.length; i++){
                    temp_data[i]=data[i];
                }

                data=temp_data;
                }
                }
            

            if((data.length==0)&&(requiredCapacity<5)){
                temp_data=new Object[5];
                for(int i=0; i<data.length; i++){
                    temp_data[i]=data[i];
                }
                data=temp_data;
            }


            if((data.length==0)&&(requiredCapacity>5)){
                temp_data=new Object[requiredCapacity];

                for(int i=0; i<data.length; i++){
                    temp_data[i]=data[i];
                }
                data=temp_data;
            }
        
          }
    

    //need to test
    public int getCapacity(){

        return data.length;
    }

    //need to complete
    public void insert(int index, E element){
        //expands the array if the array size is too small

        if(index>=data.length){
            throw new IndexOutOfBoundsException();

        }



        if(data.length==size){
            expandCapacity(data.length+1);
        }

        //shifts the array by 1
        for(int i=size; i>=index; i--){
            data[i+1]=data[i];
        }

        data[index]=element;
        size++;
    }




    //need to complete
    public void append(E element){
        if(data.length==size){
            expandCapacity(data.length+1);
        }
        data[size]=element;
        size++;

    }




    //need to test
    public void prepend(E element){
        if(data.length==size){
            expandCapacity(data.length+1);
        }
        Object[] temp= new Object[data.length];

        for(int i=0; i<data.length; i++){
            temp[i]=data[i];
        }



        for(int i=0; i<size; i++){
            this.data[i+1]=temp[i];
        }
        size++;
        data[0]=element;


    }




    //need to test
    @SuppressWarnings("unchecked")
    public E get(int index){
        if((index>data.length)||(index<0)){
            throw new IndexOutOfBoundsException();
        }
       E item= (E) data[index];
        return item;
    }

    //need to test
    @SuppressWarnings("unchecked")
    public E set(int index, E element){
        if((index>data.length)||(index<0)){
            throw new IndexOutOfBoundsException();
        }

        E temp= (E) data[index];
        data[index]=element;
        return temp;

    }
    //need to complete -double check logic later on 
    @SuppressWarnings("unchecked")
   public E remove(int index){
    if((index>data.length)||(index<0)){
        throw new IndexOutOfBoundsException();
    }

        E temp= (E) data[index];
        for(int i=index; i<data.length-1; i++){
            data[i]=data[i+1];
        }
        data[data.length-1]=null;
        size--;
        return temp;

   }
    //need to complete


    public int size(){
       return size;
    }









}