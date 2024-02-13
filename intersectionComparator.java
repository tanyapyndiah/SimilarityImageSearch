import java.util.Comparator;

public class intersectionComparator implements Comparator<ImageIntersection>{
    public int compare(ImageIntersection a, ImageIntersection b){
        return (a.getIntersection().compareTo(b.getIntersection()));
    }
}
