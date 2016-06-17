package tasks.task02;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Data {
    //         the list cities
     List<City> cities = new ArrayList<>();
    //         the list of neighbours of cities
     List<Neighbors> neighborses = new ArrayList<Neighbors>();
    //         the number of rute to find
     HashMap<Integer, Neighbors> rutes = new HashMap<Integer, Neighbors>();
    //         the list of rute
     ArrayList<List<City>> listPath = new ArrayList<>();
    //         result List
     List<Data> graphs = new ArrayList<>();
}
