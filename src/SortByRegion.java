import java.util.Comparator;

public class SortByRegion implements Comparator<City> {
    @Override
    public int compare(City a, City b) {
        if (a.getRegion() == b.getRegion()) {
            return a.getName().compareTo(b.getName());
        } else return a.getRegion().compareTo(b.getRegion());
    }
}
