import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
    /*
        LocalDate d01 = LocalDate.now();
        System.out.println("d01" + " = " + d01);
        LocalDateTime d02 = LocalDateTime.now();
        System.out.println("d02" + " = " + d02);
        Instant d03 = Instant.now();
        System.out.println("d03" + " = " + d03);
        LocalDate d04 = LocalDate.parse("2022-07-20");
        System.out.println("d04" + " = " + d04);
        LocalDateTime d05 = LocalDateTime.parse("2022-07-20T01:30:34");
        System.out.println("d05" + " = " + d05);
        Instant d06 = Instant.parse("2022-07-20T01:30:34z");
        System.out.println("d06" + " = " + d06);

        Instant d07 = Instant.parse("2022-07-20T01:30:34-03:00");
        System.out.println("d07" + " = " + d07);

        DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate d08 = LocalDate.parse("24/03/2002", fmt1);
        System.out.println("d08" + " = " + d08);
        LocalDate d09 = LocalDate.of(2002,03,24);

     */

        LocalDate d04 = LocalDate.parse("2002-03-24");
        System.out.println("d04" + " = " + d04);
        LocalDateTime d05 = LocalDateTime.parse("2022-07-20T01:30:34");
        System.out.println("d05" + " = " + d05);
        Instant d06 = Instant.parse("2022-07-20T01:30:26Z");
        System.out.println("d06" + " = " + d06);

        DateTimeFormatter fmt1 =  DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter fmt2 =  DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        DateTimeFormatter fmt3 =  DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").withZone(ZoneId.of("America/Sao_Paulo"));

        System.out.println("d04 = " + fmt1.format(d04));
        System.out.println("d04 = " + d04.format(fmt1));
        System.out.println("d05 = " + d05.format(fmt1));
        System.out.println("d05 = " + d05.format(fmt2));
        System.out.println("d05 = " + fmt3.format(d06));


    }
}
