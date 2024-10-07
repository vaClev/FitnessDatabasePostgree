import java.util.Date;
import java.util.GregorianCalendar;

public class CalendarDateTest {
    public static void main(String[] args) {
        GregorianCalendar calendar = new GregorianCalendar();
        Date date = calendar.getTime();
        System.out.println(date);
    }
}
