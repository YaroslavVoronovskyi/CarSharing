package carsharing.processors.impl.car;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class BackFromCarMenuToPreviousMenuProcessorTest {

    @InjectMocks
    private BackFromCarMenuToPreviousMenuProcessor backFromCarMenuToPreviousMenuProcessor;

    @Test
    public void shouldCheckBackToPreviousMenuProcessor() {
        assertFalse(backFromCarMenuToPreviousMenuProcessor.doActionWithCar(1));
    }

    @Test
    public void shouldReturnSupportedActionTitle() {
        assertEquals(backFromCarMenuToPreviousMenuProcessor.getSupportedCarActionTitle(), "0");
    }
}
