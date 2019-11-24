import java.util.*;

public class Atm {
    private List<Integer> moneyTypes = new ArrayList<>(Arrays.asList(5000,1000,500,100));

    public static Map getMoney(int amount) {
        BankNoteHandler oneHundredHandler = new BankNoteHandler(null, 100,54);
        BankNoteHandler fiveHundredHandler = new BankNoteHandler(oneHundredHandler, 500,10);
        BankNoteHandler oneThousandHandler = new BankNoteHandler(fiveHundredHandler, 1000,5);
        BankNoteHandler fiveThousandHandler = new BankNoteHandler(oneThousandHandler, 5000,1);
        return fiveThousandHandler.getBankNoteCount(amount, new TreeMap<>());
    }
}
