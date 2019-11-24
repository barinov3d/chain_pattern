import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Map money = Atm.getMoney(13900);
        getSum(money);
        System.out.println(money);
    }

    private static Integer getSum(Map<Integer, Integer> money) {
        Integer result = 0;
        for (Integer banknote: money.keySet()) {
            result += banknote * money.get(banknote);
        }
        System.out.println(result);
        return result;
    }
}
