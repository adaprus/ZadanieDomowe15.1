import java.io.*;
import java.util.*;

public class CountryTest {
    public static void main(String[] args) {
        String path = "countries.csv";
        Scanner scanner = new Scanner(System.in);

        try {
            Map<String, Country> countryMap = createCountryMap(path);
            System.out.println(showCountryInfo(scanner, countryMap));
        } catch (IOException e) {
            System.out.println("Błąd pliku");
        }
        scanner.close();
    }

    private static String showCountryInfo(Scanner scanner, Map<String, Country> map) {
        System.out.println("Podaj kod kraju, o którym chcesz zobaczyć informacje:");
        String code = scanner.nextLine();
        Set<String> set = map.keySet();
        System.out.println(map.keySet());

        if (set.contains(code)) {
            return map.get(code).toString();
        }
        return "W bazie nie ma takiego kodu";
    }

    private static Map<String, Country> createCountryMap(String path) throws IOException {
        File file = new File(path);
        Map<String, Country> map = new HashMap<>();
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        String[] data;
        String code;
        Country country;

        while ((line = br.readLine()) != null) {
            data = line.split(";");
            code = data[0];
            country = new Country(code, data[1], Integer.valueOf(data[2]));
            map.put(code, country);
        }
        return map;
    }
}
