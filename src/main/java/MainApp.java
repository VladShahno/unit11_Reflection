import com.nixsolution.config.Initializer;
import com.nixsolution.data.Person;

public class MainApp {

    public static void main(String[] args) throws Exception {
        Person person = new Person();
        Initializer.initialization(person);
        System.out.println(person);
    }
}
