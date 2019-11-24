import java.util.Map;

public class BankNoteHandler {
    private BankNoteHandler nextBankNoteHandler;
    private int bankNoteNominal;
    private int realbankNoteCount;

    BankNoteHandler(BankNoteHandler nextBankNoteHandler, int bankNoteNominal, int realbankNoteCount) {
        this.nextBankNoteHandler = nextBankNoteHandler;
        this.bankNoteNominal = bankNoteNominal;
        this.realbankNoteCount = realbankNoteCount;
    }

    public Map<Integer, Integer> getBankNoteCount(int money, Map<Integer, Integer> storage) {
        int maxAvaliableMoney = bankNoteNominal * realbankNoteCount;
        int bankNoteCount = money / bankNoteNominal;
        if (bankNoteCount == 0) {
            if (nextBankNoteHandler == null) {
                throw new RuntimeException("Сумма должна быть кратна " + bankNoteNominal);
            } else {
                nextBankNoteHandler.getBankNoteCount(money, storage);
            }
        }
        if (maxAvaliableMoney < money) {
            storage.put(bankNoteNominal, realbankNoteCount);
            if (nextBankNoteHandler != null) {
                nextBankNoteHandler.getBankNoteCount(money - maxAvaliableMoney, storage);
            } else {
                throw new RuntimeException("Недостаточно наличных для выдачи");
            }
        } else {
            final int remains = money % bankNoteNominal;
            if (bankNoteCount >= 1) {
                storage.put(bankNoteNominal, bankNoteCount);
            }
            if (nextBankNoteHandler != null) {
                nextBankNoteHandler.getBankNoteCount(remains, storage);
            } else {
                if (remains != 0) {
                    throw new RuntimeException("Сумма должна быть кратна " + bankNoteNominal);
                }
            }
        }
        return storage;
    }
}
