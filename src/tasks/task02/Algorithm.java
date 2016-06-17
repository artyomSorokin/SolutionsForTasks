package tasks.task02;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Algorithm {

    //marix for compute shortest path
    private int[][] shorts;
    //matrix for compute shortest path from city to city
    private City[][] cities;

    //init our matrix
    public Algorithm(City[] cities, Neighbors [] neighborses) throws Exception{

        if(cities == null){
            throw new Exception("Cities is null");
        }

        if(neighborses == null){
            throw new Exception("Neighborses is null");
        }

        shorts = initializeWeight(cities, neighborses);
        this.cities = new City[cities.length][cities.length];

        for (int k = 0; k < cities.length; k++) {
            for (int i = 0; i < cities.length; i++) {
                for (int j = 0; j < cities.length; j++) {
                    if (shorts[i][k] != Integer.MAX_VALUE
                            && shorts[k][j] != Integer.MAX_VALUE
                            && shorts[i][k] + shorts[k][j] < shorts[i][j]) {
                        shorts[i][j] = shorts[i][k] + shorts[k][j];
                        this.cities[i][j] = cities[k];
                    }
                }
            }
        }
    }


    public Algorithm(List<City> cities, List<Neighbors> neighborses) throws Exception {

        this(cities.toArray(new City[0]),neighborses.toArray(new Neighbors[0]));
    }

    //Determines the length of the shortest path from A (source) to B (target)
    public int getShortestDistance(City source, City target) {
        return shorts[source.id][target.id];
    }

    public int getShortestDistance(Neighbors rute) {

        int distance = getShortestDistance(rute.getFrom(), rute.getTo());
        rute.setWeight(distance);

        return distance;
    }

    /**
     * Describes the shortest path from vertex A (source) to vertex B (target)
     * by returning a collection of the vertices traversed,
     * in the order traversed
    */

    public List<City> getShortestPath(City source, City target) {

        if (shorts[source.id][target.id] == Integer.MAX_VALUE) {
            return new ArrayList<City>();
        }
        List<City> path = getIntermediatePath(source, target);
        path.add(0, source);
        path.add(target);
        return path;
    }

    public List<City> getShortestPath(Neighbors rute) {
        return getShortestPath(rute.getFrom(), rute.getTo());
    }

    //This method constructs path from cities
    private List<City> getIntermediatePath(City source, City target) {
        if (cities[source.id][target.id] == null) {
            return new ArrayList<>();
        }
        List<City> path = new ArrayList<>();
        path.addAll(getIntermediatePath(source, cities[source.id][target.id]));
        path.add(cities[source.id][target.id]);
        path.addAll(getIntermediatePath(cities[source.id][target.id], target));
        return path;
    }

    /**
     * This method constructs adjacency matrix. Infinity is equal to value of
     * Integer.MAX_VALUE. Size of occupied space is Node.length**2.
    */

    private int[][] initializeWeight(City[] cities, Neighbors[] neighborses) {
        int[][] mas = new int[cities.length][cities.length];

        for (int i = 0; i < cities.length; i++) {
            Arrays.fill(mas[i], Integer.MAX_VALUE);
        }
        for (Neighbors e : neighborses) {
            mas[e.getFrom().id][e.getTo().id] = e.getWeight();
        }
        return mas;
    }
}
