import java.util.Map;

public class BankNoteHandler {
    private BankNoteHandler nextBankNoteHandler;
    private int bankNoteNominal;
    private int currentCount;

    BankNoteHandler(BankNoteHandler nextBankNoteHandler, int bankNoteNominal, int currentCount) {
        this.nextBankNoteHandler = nextBankNoteHandler;
        this.bankNoteNominal = bankNoteNominal;
        this.currentCount = currentCount;
    }

    public Map<Integer, Integer> getBankNoteCount(int money, Map<Integer, Integer> storage) {
        if (money <= 0) {
            throw new RuntimeException("Введенная сумма не может быть 0 или отрицательным числом");
        }
        int count = money / bankNoteNominal;
        int maxAvaliableMoney = bankNoteNominal * currentCount;
        if (nextBankNoteHandler != null) {
            if (money % bankNoteNominal == 0) {
                if (count > currentCount) {
                    storage.put(bankNoteNominal, count);
                }
                else {
                    storage.put(bankNoteNominal, currentCount);
                    int remains = money - maxAvaliableMoney;
                    nextBankNoteHandler.getBankNoteCount(remains, storage);
                }
            } else if (money > bankNoteNominal) {
                if (count > currentCount) {
                    storage.put(bankNoteNominal, currentCount);
                }
                else {
                    storage.put(bankNoteNominal, count);
                }
                int remains = money - maxAvaliableMoney;
                nextBankNoteHandler.getBankNoteCount(remains, storage);
            } else {
                nextBankNoteHandler.getBankNoteCount(money, storage);
            }
        } else {
            if (money % bankNoteNominal == 0) {
                if (count > currentCount) {
                    throw new RuntimeException("Недостаточно купюр для выдачи. Купюра: " + bankNoteNominal + " Требуется: " + count + " Доступно " + currentCount);
                }
                else {
                    storage.put(bankNoteNominal, count);
                }
            } else {
                throw new RuntimeException("Не возможно обработать часть суммы " + money + " - минимальная доступная банкнота - " + bankNoteNominal);
            }
        }
        return storage;
    }
}
