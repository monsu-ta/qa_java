import com.example.Feline;
import com.example.Lion;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import java.util.List;

    @RunWith(Parameterized.class)
    public class LionTest {
        @Mock
        Feline feline;

        private final String sex;
        private final boolean expectedHasMane;

        public LionTest(String sex, boolean expectedHasMane) {
            this.sex = sex;
            this.expectedHasMane = expectedHasMane;
            MockitoAnnotations.openMocks(this);
        }

        @Parameterized.Parameters
        public static Object[][] getData() {
            return new Object[][] {
                    {"Самец", true},
                    {"Самка", false}
            };
        }

        @Test
        public void testDoesHaveMane() throws Exception {
            Lion lion = new Lion(sex, feline);
            assertEquals(expectedHasMane, lion.doesHaveMane());
        }

        @Test(expected = Exception.class)
        public void testInvalidSex() throws Exception {
            new Lion("Не выявлен", feline);
        }

        @Test
        public void testGetKittens() throws Exception {
            when(feline.getKittens()).thenReturn(1);
            Lion lion = new Lion("Самец", feline);
            assertEquals(1, lion.getKittens());
        }

        @Test
        public void testGetFood() throws Exception {
            List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");
            when(feline.eatMeat()).thenReturn(expectedFood);
            Lion lion = new Lion("Самец", feline);
            assertEquals(expectedFood, lion.getFood());
        }
    }
