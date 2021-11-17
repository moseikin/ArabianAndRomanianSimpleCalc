import interfaces.DataReceiver;
import services.ConsoleDataReceiver;

public class Application {

    public static void main(String[] args) throws Exception {
        DataReceiver dataReceiver = new ConsoleDataReceiver();
        dataReceiver.receive();
    }
}
