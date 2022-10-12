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

    private final static String TEST_SUPPORTED_ACTION_TITLE = "0";
    private final static int TEST_COMPANY_ID = 1;
    @InjectMocks
    private BackFromCarMenuToPreviousMenuProcessor backFromCarMenuToPreviousMenuProcessor;

    @Test
    public void shouldCheckBackToPreviousMenuProcessor() {
        assertFalse(backFromCarMenuToPreviousMenuProcessor.doActionWithCar(TEST_COMPANY_ID));
    }

    @Test
    public void shouldReturnSupportedActionTitle() {
        assertEquals(backFromCarMenuToPreviousMenuProcessor.getSupportedCarActionTitle(), TEST_SUPPORTED_ACTION_TITLE);
    }
}
