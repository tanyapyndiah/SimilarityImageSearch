import java.io.File;
import java.io.IOException;
import java.util.PriorityQueue;


public class SimilaritySearch {
    public static void main(String[] args) throws IOException {
        PriorityQueue<ImageIntersection> intersection=new PriorityQueue<ImageIntersection>(5,new intersectionComparator());
        String queryImage= args[0];
        String genericPath=".jpg.txt";
        String imgPath;
        int d=3;
        String datasetDirectory=args[1]+"//";
        queryImage=queryImage.substring(0,3);

        //generate histogram of query image
        ColorHistogram queryHistogram=new ColorHistogram(d);
        queryHistogram.setImage(new ColorImage("queryImages//"+queryImage+".ppm"));

        //loop through all the dataset images
        for (int i=25;i<5000;i++){
            imgPath= i + genericPath;

            try{
                ColorHistogram dataset=new ColorHistogram(datasetDirectory+imgPath);
                intersection.offer(new ImageIntersection(imgPath,queryHistogram.compare(dataset) ));
                if (intersection.size()>5){
                    intersection.poll();
                }
            } catch(Exception e){
            }

        }
        String[] mostSimilar=new String[5];
        //when we poll we obtain the images in order of least similar to most similar
        for(int i=0;i<5;i++){
            mostSimilar[i]=intersection.poll().getName();
        }

        //print array in reverse order to get the most similar image first
        System.out.println("The 5 most similar images to the given query image are (from most similar to least similar): ");
        for (int i=4;i>=0;i--){
            System.out.println((5-i)+". "+mostSimilar[i]);
        }

        
    }
}
