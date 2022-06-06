import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.itmo.test.Main;

public class TestCase {

    @Test
    @DisplayName("Рабочий тест 1 на ответ")
    public void test1() {
        Main cailculate = new Main();
        Assertions.assertArrayEquals(new double[]{5}, cailculate.Calculate(1,-10,25));
    }

    @Test
    @DisplayName("Рабочий тест 2 на неверные значения")
    public void test2() {
        Main cailculate = new Main();
        Assertions.assertArrayEquals(null, cailculate.Calculate(111,-10,25));
    }
    @Test
    @DisplayName("Рабочий тест 3 на неверные значения")
    public void test3() {
        Main cailculate = new Main();
        Assertions.assertArrayEquals(null, cailculate.Calculate(1,-102,25));
    }
    @Test
    @DisplayName("Нерабочий тест 4")
    public void test4() {
        Main cailculate = new Main();
        Assertions.assertArrayEquals(null, cailculate.Calculate(0,0,0));
    }
    @Test
    @DisplayName("рабочий тест 5")
    public void test5() {
        Main cailculate = new Main();
        Assertions.assertArrayEquals(new double[]{-4,6}, cailculate.Calculate(1,-2,-24));
    }
    @Test
    @DisplayName("рабочий тест 6")
    public void test6() {
        Main cailculate = new Main();
        Assertions.assertArrayEquals(new double[]{5}, cailculate.Calculate(1,-10,25));
    }
    @Test
    @DisplayName("рабочий тест 7")
    public void test7() {
        Main cailculate = new Main();
        Assertions.assertArrayEquals(new double[]{5}, cailculate.Calculate(1,-10,25));
    }
    @Test
    @DisplayName("рабочий тест 8")
    public void test8() {
        Main cailculate = new Main();
        Assertions.assertArrayEquals(new double[]{5}, cailculate.Calculate(1,-10,25));
    }
    @Test
    @DisplayName("рабочий тест 9")
    public void test9() {
        Main cailculate = new Main();
        Assertions.assertArrayEquals(new double[]{5}, cailculate.Calculate(1,-10,25));
    }

    @Test
    @DisplayName("рабочий тест 10")
    public void test10() {
        Main cailculate = new Main();
        Assertions.assertArrayEquals(new double[]{1.5,0}, cailculate.Calculate(4,-6,0));
    }

}
