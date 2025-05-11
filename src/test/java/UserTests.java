//import com.lab1.exceptions.InvalidDepositException;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import com.lab1.application.entity.User;
//import com.lab1.exceptions.IllegalArgumentException;
//import com.lab1.exceptions.IllegalArgumentException;
//import com.lab1.exceptions.;
//
//public class UserTests {
//    User user;
//    @BeforeEach
//    void setUp() {
//        user = new User("login", "password");
//    }
//
//    @Test
//    void shouldThrowExceptionWhenDepositNegativeAmount() {
//        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> user.deposit(-50));
//        assertEquals("Amount must be greater than 0", exception.getMessage());
//    }
//    @Test
//    void shouldThrowExceptionWhenWithdrawNegativeAmount() {
//        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> user.withdraw(-50));
//        assertEquals("Amount must be greater than 0", exception.getMessage());
//    }
//
//    @Test
//    void shouldThrowExceptionWhenWithdrawMoreThanBalance() throws InvalidDepositException {
//        user.deposit(50);
//        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> user.withdraw(100));
//        assertEquals("Negative balance", exception.getMessage());
//    }
//    @Test
//    void shouldThrowExceptionWhenPrintingEmptyTransactionHistory() {
//        IllegalStateException exception = assertThrows(IllegalStateException.class, user::printTransactionHistory);
//        assertEquals("Транзакции ещё не производились", exception.getMessage());
//    }
//}
