import java.util.HashSet;
import java.util.Set;

class QuoteRepository {
    private final Set<String> quotes;
    /*kanye.rest nie udostępnia endpointu do pobrania liczby aktualnie dostepnych cytatów. Max_quotes_numbers na podstawie
    https://github.com/ajzbc/kanye.rest/blob/master/quotes.json */
    private static final int MAX_QUOTES_NUMBERS = 122;

    public QuoteRepository() {
        quotes = new HashSet<>();
    }

    public boolean tryAddQuoteToRep(String quote) {
        if (quotes.size() >= MAX_QUOTES_NUMBERS) {
            throw new IllegalStateException("Osiąnięto maksymalną liczbę dostępnych cytatów");
        } else {
            if (quotes.contains(quote)) {
                return false;
            } else {
                quotes.add(quote);
                return true;
            }
        }
    }
}
