
package quicksort;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Quicksort3 { 
    /* Algorithm of Quick Sort for "median of three values"
    strategy of pivot choosing for set without repeats of number*/

    public static int[] ReadArray(String file) // reading of file
    {
        int [] numArray;
        try (BufferedReader reader = new BufferedReader(
            new InputStreamReader(new FileInputStream(file))))
        {
            String[] arr= reader.lines().toArray(size->new String[size]); //creating array of String from numbers in file
            numArray= new int [arr.length];
            for (int i=0;i<arr.length;i++)   //converting array of String to array of Integer
            {
                numArray[i]=Integer.valueOf(arr[i]);
            }
        } 
        catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return numArray;
    }
    
    public static void QuickSort (int [] Array, int left, int right) // recursive realisation of Quick Sort algorithm
    // left - is a left position of Array, right - is a right position        
    {
        int pivot_loc; 
        if (left<right)
        {
            pivot_loc=Partition(Array,left,right); // partition of Array with defenition a new pivot
            QuickSort(Array,left,pivot_loc); // recursively sort left part of array 
            QuickSort(Array,pivot_loc+1,right);     // recursively ort left part of array 
        }
    }
    
    public static void Swap (int [] Array, int i, int j) //swapping of elements in array
    // i,j - array's positions which should be swapped
    {
        int temp1,temp2;
        temp1=Array[j];
        temp2=Array[i];
        Array[j]=temp2;
        Array[i]=temp1;
    }

    public static int Partition (int [] Array, int left, int right) // partition of Array
    {
        // pivot is defined accorging "median of three values" strategy  
        int pivot, p_place=0,p_p=0;
        int [] p=new int [3]; // array of hypothetical pivots 
        int i,j;      
        p[0]=Array[left];
        p[2]=Array[right-1];
        // finding the right pivot and it place in array
        if ((right-left)%2==0) 
        {
            p_place=(right-left)/2+left-1;
            p[1]=Array[p_place];
        }
        else if((right-left)%2==1)
        {
            p_place=(right-left)/2+left;
            p[1]=Array[p_place];
        }
        
        for (int l=0;l<=2;l++)
        {
            for (int k=0;k<=2;k++)
            {
                if (p[l]>p[k])
                {
                    Swap(p,l,k);
                }
            }
        }
        pivot=p[1];
        if (p[1]==Array[left]) 
        {
            p_p=left;
           
        }
        if (p[1]==Array[p_place]) 
        {
            p_p=p_place;
            
        }
        if (p[1]==Array[right-1]) 
        {
            p_p=right-1;
           
        }
        // now pivot is equal p[1] and has a place p_place in initial Array
        Swap(Array,p_p,left); //swapping pivot and first element in initial Array
        i=left+1;
        for (j=left+1;j<right;j++) //sorting Array with algorithm where pivot is a first element of array
        {
            if (Array[j]<pivot)
            {
                Swap(Array,i,j);
                i=i+1;
            }
            
        }
        Swap(Array,left,i-1);  
        return i-1;
    }

    public static void main(String[] args) {
        int [] Array;
        Array = ReadArray("C:\\Quicksort.txt"); // add a path to your file here
        if (Array!=null)
        {
            QuickSort(Array,0,Array.length); //sort of Array
            for (int i=0;i<Array.length;i++)
            {
                System.out.println(Integer.toString(Array[i])); // Results
            }
        }
    }
}
