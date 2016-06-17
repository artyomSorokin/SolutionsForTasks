package tasks.task02;


public class Neighbors {

    private City from;
    private City to;

    //weight of the line
    private int weight;

    public Neighbors(City from, City to, int weight) throws Exception {

        if(from == null){
            throw new Exception("city from is empty");
        }

        if(to == null){
            throw new Exception("city to is empty");
        }

        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public City getFrom() {
        return from;
    }

    public City getTo() {
        return to;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
