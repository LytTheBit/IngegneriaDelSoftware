package DomainModel;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RegisterTest {
    Register register = new Register(1,1,0);
    @Test
    public void registerIdReturnTest(){
        assertEquals(1, register.getIdBook());
    }

    @Test
    public void registerNumTakeReturnTest(){
        assertEquals(0, register.getNumTake());
    }

    @Test
    public void registerNumCopyReturnTest(){
        assertEquals(1, register.getNumCopy());
    }
}