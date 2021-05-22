import exceptions.InvalidChatDataException;
import exceptions.InvalidClientDataException;
import exceptions.UniqueClientException;

public class Main {

    public static void main(String[] args) {
        try {
            Client client = new Client("Jan", "Kowalski", "Warszawa", "s18917@pjwstk.edu.pl");
            Client moderator = new Client("Yehor", "Voiko", "Warszawa", "alamakota@gmail.com");
            Chat chat = new Chat("PJWSTK");
            client.joinChat(chat);
            moderator.moderateChat(chat);
            client.test();
            moderator.test();
            chat.test();
            System.out.println("Testing removing moderator client-sided");

        } catch (UniqueClientException | InvalidClientDataException | InvalidChatDataException e) {
            e.printStackTrace();
        }
    }
}
