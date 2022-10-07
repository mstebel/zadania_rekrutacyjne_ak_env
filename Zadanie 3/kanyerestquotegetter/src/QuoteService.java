import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;

class QuoteService {
    private final Scanner scanner = new Scanner(System.in);
    private final QuoteRepository quoteRepository;

    public QuoteService() {
        quoteRepository = new QuoteRepository();
    }

    public void run() {
        String option = getOptionFromUser();
        while (!Option.EXIT.name().equals(option)) {
            switch (Option.valueOf(option)) {
                case NEXT -> getNewQuote();
                case EXIT -> System.out.println("Do zobaczenia");
                default -> System.out.println(Option.getOptionNotFoundMessage());
            }
            option = getOptionFromUser();
        }
    }

    private void getNewQuote() {
        boolean quoteAddedToRep;
        try {
            String quote = QuoteRetriever.retrieveQuoteFromRestApi();
            do {
                quoteAddedToRep = quoteRepository.tryAddQuoteToRep(quote);
                if (quoteAddedToRep) {
                    System.out.println("\ncytat: " + quote + "\n");
                } else {
                    quote = QuoteRetriever.retrieveQuoteFromRestApi();
                }
            } while (!quoteAddedToRep);
        } catch (URISyntaxException e) {
            System.out.println("Niepoprawny adres URI");
        } catch (IOException | InterruptedException e) {
            System.out.println("Błąd połączenia. Nie udało się pobrać cytatu. Spróbuj ponownie");
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }

    private String getOptionFromUser() {
        System.out.println("Wpisz komendę. Dostępne komendy:\n" + Option.getOptionsNames());
        String userInput = scanner.nextLine().toUpperCase();
        boolean readComplete = false;
        do {
            try {
                Option.validateUserInput(userInput);
                readComplete = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                userInput = scanner.nextLine().toUpperCase();
            }
        } while (!readComplete);
        return userInput;
    }
}
