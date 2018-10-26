package api;

public class Main
{

    public static void main(String[] args) {
        CorsFilter filter = new CorsFilter();
        filter.apply();
        new MessageContoller(new MessageService());
    }
}
