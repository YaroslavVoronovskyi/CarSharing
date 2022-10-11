package carsharing.processors.impl.app;

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
public class ExitApplicationProcessorTest {

    @InjectMocks
    private ExitApplicationProcessor exitApplicationProcessor;

    @Test
    public void shouldCheckExitFromApplicationProcessor() {
        assertFalse(exitApplicationProcessor.doAction());
    }

    @Test
    public void shouldReturnSupportedActionTitle() {
        assertEquals(exitApplicationProcessor.getSupportedActionTitle(), "0");
    }
}
