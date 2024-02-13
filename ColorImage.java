import java.io.*;
import java.util.Arrays;
import java.util.Scanner;


public class ColorImage {
    private int width,height,depth;
    private int[][][] pixelArray;

    public int getWidth(){return width;}

    public int getHeight(){return height;}

    public int getDepth(){return depth;}

    public ColorImage(String filename) throws IOException{

        File f = new File(filename);
        Scanner s = new Scanner(f);

        s.nextLine();
        s.nextLine();  

        width=Integer.parseInt(s.next());
        height=Integer.parseInt(s.next());
        int rgb=Integer.parseInt(s.next());

        while(rgb>0){
            rgb=rgb/2;
            depth++;
        }


        pixelArray=new int[height][width][3];

        //loop through all the pixels inside the ppm file
        for (int i=0;i<height;i++){
            for (int j=0;j<width;j++){
                pixelArray[i][j][0]=Integer.parseInt(s.next());
                pixelArray[i][j][1]=Integer.parseInt(s.next());
                pixelArray[i][j][2]=Integer.parseInt(s.next());
            }
        }
          
        s.close();
    }

    public int[] getPixel(int i, int j){
        // int p[]=new int[3];

        // for (int x=0;x<3;x++){
        //     p[x]=pixelArray[i][j][x];
        // }
        // return p;
        return pixelArray[i][j];
    }

    public void reduceColor(int d){
        //for each pixel, we perform a bitwise right shift of 8-d places using the >> bitwise right shift operator
        for (int i=0;i<height;i++){
            for (int j=0;j<width;j++){
                pixelArray[i][j][0]=(pixelArray[i][j][0]>>(depth-d));
                pixelArray[i][j][1]=(pixelArray[i][j][1]>>(depth-d));
                pixelArray[i][j][2]=(pixelArray[i][j][2]>>(depth-d));
            }
        }
    } 
 
    
}