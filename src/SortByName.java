import java.util.Comparator;

public class SortByName implements Comparator<City> {
    @Override
    public int compare(City a, City b) {
        return (a.getName().compareTo(b.getName()));
    }
}
