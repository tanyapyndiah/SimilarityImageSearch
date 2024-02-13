public class ImageIntersection {
    private String name;
    private double intersection;

    public ImageIntersection(String name,double intersection){
        this.name=name;
        this.intersection=intersection;
    }

    public Double getIntersection(){
        return intersection;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name=name;
    }

    public void setIntersection(Double inter){
        intersection=inter;
    }
}
