package tasks.task02;


public class City {

    //number of city
    int id;
    private String name;

    public City(int id, String name) throws Exception {
        this.id = id;
        this.name = name;
    }

    public City(int id) throws Exception {
        this(id, null);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
