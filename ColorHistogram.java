import java.io.File;
import java.lang.Math;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;


public class ColorHistogram {
    private ColorImage image;
    private int[] histogram;
    private int d;
    private boolean histoComplete=false;
    private int pixelCount=0;

    public ColorHistogram (int d){
        histogram=new int[(int)Math.pow(2, d*3)];
        this.d=d;
    }

    //construct histogram from text file
    public ColorHistogram (String filename) throws FileNotFoundException{
        //first number in file is the number of entries in the histogram
        File f = new File(filename);
        Scanner s = new Scanner(f);

        int size= Integer.parseInt(s.next());
        histogram=new int[size];
        int i=0;
        while(s.hasNext()){
            int temp=Integer.parseInt(s.next());
            pixelCount=pixelCount+temp;
            histogram[i]=temp;
            i++;
        }
        histoComplete=true;

        s.close();
    }

    public int getSize(){
        return (int)Math.pow(2, d*3);
    }

    public void setImage(ColorImage image){
        this.image=image;
        this.image.reduceColor(d);
    }

    public double[] getHistogram(){
        if (!histoComplete){
            for (int i=0;i<image.getHeight();i++){
                for (int j=0;j<image.getWidth();j++){
                    int p[]=image.getPixel(i, j);
                    histogram[(p[0]<<(2*d))+(p[1]<<d)+p[2]]++;
                }
            }
            histoComplete=true;
            pixelCount=image.getHeight()*image.getWidth();
        }
        

        double[] normalised= new double[histogram.length];

        //NORMALISE THE HISTOGRAM
        for (int i=0;i<histogram.length;i++){
            normalised[i]=(double)histogram[i]/(double)pixelCount;
        }
        return normalised;
    }

    //write histogram to a file
    public void ColorHistogramWrite (String filename) throws FileNotFoundException{
        PrintWriter print= new PrintWriter(filename);
        for (int i=0;i<(int)Math.pow(2, d*3);i++){
            print.write(histogram[i]);
            print.write(" ");
        }

        print.close();
    }

    //compare 2 histograms and return their intersection
    public double compare(ColorHistogram hist){
        double intersection=0;
        double[] normalised=getHistogram();
        double[] dataset = hist.getHistogram();

        for(int i=0;i<getSize();i++){ 
            intersection=intersection+Math.min(dataset[i],normalised[i]);
        }
        
        return intersection;
    }

}
