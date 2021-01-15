package Logic;

public class Date {
    private String year;
    private String month;
    private String day;
    private StringBuilder date;

    public Date(String year, String month, String day) {
        date = new StringBuilder("0000/00/00");
        setYear(year);
        setMonth(month);
        setDay(day);
    }

    public void setYear(String year) {
        date.replace(0, 4, year);
    }

    public void setMonth(String month) {
        date.replace(5, 7, month);
    }

    public void setDay(String day) {
        date.replace(8, 10, day);
    }

    public static void main(String[] args) {
        Date date = new Date("1380", "04", "30");
        System.out.println(date.getDate());
    }

    public StringBuilder getDate() {
        return date;
    }
}
