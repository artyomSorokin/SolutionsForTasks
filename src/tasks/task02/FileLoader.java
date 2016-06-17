package tasks.task02;




import java.io.File;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileLoader {

    private File fileNameIn;
    private File fileNameOut;
    Data data = new Data();

    public File getFileNameIn() {
        return fileNameIn;
    }

    public void setFileNameOut(File fileNameOut) {
        this.fileNameOut = fileNameOut;
    }

    public void setFileNameIn(File fileNameIn) {
        this.fileNameIn = fileNameIn;
    }

    public File getFileNameOut() {
        return fileNameOut;
    }

    public void fileLoad() throws Exception {
        Scanner in = null;
        int s = 0;
        try {
            in = new Scanner(getFileNameIn());
        } catch (FileNotFoundException ex) {
            throw new Exception("The format of file is wrong");
        }
        int line = 0;

        if (in.hasNextInt()) {
            s = in.nextInt();
            line++;
        } else {

        }
        for (int i = 0; i < s; i++) {
            LoadFile record = new LoadFile(in, line);
            data.graphs.add(record.read());
            in = record.inRecord;
            line = record.line;

            if (in.hasNextLine()) {
                in.nextLine();
                line++;
            }
        }
        in.close();
    }

    class LoadFile {

        int line;                       // line in file
        int s = 0;                      // the number of tests
        Scanner inRecord;               // pointer on record of test


        public LoadFile(Scanner in, int line) {
            this.inRecord = in;
            this.line = line;
        }

        public Data read() throws Exception {
            readCity();
            readRutes();
            return data;
        }

        private void readCity() throws Exception {
            int numberOfCity = 0; // the number of cities
            if (inRecord.hasNextInt()) {
                numberOfCity = inRecord.nextInt();
                line++;
            } else {
                throw new Exception("The format of file is wrong");
            }
            // initialization of the cities inRecord objects of Node, the first city corresponds to object of Node (0)
            for (int city = 0; city < numberOfCity; city++) {
                data.cities.add(new City(city));
            }

            for (int city = 0; city < numberOfCity; city++) {
                if (inRecord.hasNextLine()) {
                    data.cities.get(city).setName(inRecord.next());
                    line++;
                } else {
                    throw new Exception("The format of file is wrong");
                }
                readEdges(city);
            }
        }

        private void readEdges(int fromCity) throws Exception {
            int p = 0;                          // the number of neighbours of city
            if (inRecord.hasNextInt()) {
                p = inRecord.nextInt();
                line++;
            } else {
                throw new Exception("The format of file is wrong");
            }

            for (int i = 0; i < p; i++) {
                int toCity = 0;
                if (inRecord.hasNextInt()) {
                    toCity = inRecord.nextInt();
                } else {
                    throw new Exception("The format of file is wrong");
                }
                int cost = 0;
                if (inRecord.hasNextInt()) {
                    cost = inRecord.nextInt();
                    line++;
                } else {
                    throw new Exception("The format of file is wrong");
                }

                Neighbors neighbors = new Neighbors(data.cities.get(fromCity), data.cities.
                        get(toCity - 1), cost);
                data.neighborses.add(neighbors);
            }
        }

        private void readRutes() throws Exception {
            int r = 0; //  // the number of rute to find
            if (inRecord.hasNextInt()) {
                r = inRecord.nextInt();
                line++;
            } else {
                throw new Exception("The format of file is wrong");
            }
            for (int i = 0; i < r; i++) {

                String strFrom = null;
                if (inRecord.hasNext()) {
                    strFrom = inRecord.next();
                } else {
                    throw new Exception("The format of file is wrong");
                }

                String strTo = null;
                if (inRecord.hasNext()) {
                    strTo = inRecord.next();
                    line++;
                } else {
                    throw new Exception("The format of file is wrong");
                }

                City cityFrom = null;
                City cityTo = null;

                for (int j = 0; j < data.cities.size(); j++) {
                    if ((cityFrom == null) && (data.cities.get(j).getName().
                            equals(strFrom))) {
                        cityFrom = data.cities.get(j);
                    }
                    if ((cityTo == null) && (data.cities.get(j).getName().
                            equals(strTo))) {
                        cityTo = data.cities.get(j);
                    }
                }
                Neighbors neighbors = new Neighbors(cityFrom, cityTo, -1);
                data.rutes.put(i, neighbors);
            }
        }
    }

    public void writeToFile() {
        PrintWriter out = null;
        try {
            out = new PrintWriter(getFileNameOut());
            for (Data outData : data.graphs) {
                for (int i = 0; i < outData.rutes.size(); i++) {
                    out.println(outData.rutes.get(i).getWeight());
                }
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            out.close();
        }

    }

    public void solution() throws Exception {

        for (Data fact : data.graphs) {  // for each graph to calculate the rute

            Algorithm algorithml = new Algorithm(fact.cities, fact.neighborses);

            for (int i = 0; i < fact.rutes.size(); i++) {
                algorithml.getShortestDistance(fact.rutes.get(i));
                fact.listPath.add(algorithml.getShortestPath(fact.rutes.get(i)));
            }
        }
    }
}
