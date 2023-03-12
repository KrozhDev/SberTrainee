import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class CityUtils {

    public static void howManyCitiesInEachRegion(List <City> cities) {
        Collections.sort(cities, new SortByRegion());
        City currentCity = cities.get(0);
        String currentRegion = currentCity.getRegion();
        int howManyCitiesInRegion = 0;
        for (int i = 0; i < cities.size(); i++ ) {
            currentCity = cities.get(i);
            if (currentRegion.equals(currentCity.getRegion())) {
                howManyCitiesInRegion++;
            } else {
                System.out.println(currentRegion + " " + howManyCitiesInRegion);
                howManyCitiesInRegion = 1;
                currentRegion = currentCity.getRegion();
            }
        }
        System.out.println(currentRegion + " " + howManyCitiesInRegion);
    }


    public static int[] convertToArray(List<City> cities) {
        int[] populationArray = new int[cities.size()];
        for (int i = 0; i < populationArray.length; i++) {
            populationArray[i] = cities.get(i).getPopulation();
        }
        return populationArray;
    }

    public static void maxPopulation(int[] populationArray) {
        int max = 0;
        int index = 0;
        for (int i = 0; i < populationArray.length; i++) {
            if (populationArray[i] > max) {
                max = populationArray[i];
                index = i;
            }

        }
        System.out.println("[" + index +"] " + max);
    }


    public static List<City> parse() {
        List<City> cities = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File("resources/city_ru.csv"));
            while (scanner.hasNextLine()) {
                cities.add(parse(scanner.nextLine()));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return cities;
    }

    public static void print(List<City> cities) {

        System.out.println();
//        cities.forEach(System.out::println);
//        maxPopulation(convertToArray(cities));
        howManyCitiesInEachRegion(cities);

    }

    public static void printSortedByName(List<City> cities) {

        System.out.println();
        System.out.println("Пример полученного результата для сортировки по наименованию:\n");
        Collections.sort(cities, new SortByName());
        cities.forEach(System.out::println);
    }

    public static void printSortedByRegion(List<City> cities) {

        System.out.println();
        System.out.println("Пример полученного результата для сортировки по региону:\n");
        Collections.sort(cities, new SortByRegion());
        cities.forEach(System.out::println);
    }



    private static City parse(String line) {
        Scanner scanner = new Scanner(line);
        scanner.useDelimiter(";");
        scanner.skip("\\d*");
        String name = scanner.next();
        String region = scanner.next();
        String district = scanner.next();
        int population = scanner.nextInt();
        String foundation = null;
        if (scanner.hasNext()) {
            foundation = scanner.next();
        }
        scanner.close();

        return new City(name, region, district, population, foundation);
    }
}
