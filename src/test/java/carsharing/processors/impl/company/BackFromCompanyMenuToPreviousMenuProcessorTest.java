package carsharing.processors.impl.company;

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
public class BackFromCompanyMenuToPreviousMenuProcessorTest {

    private final static String TEST_SUPPORTED_ACTION_TITLE = "0";
    @InjectMocks
    private BackFromCompanyMenuToPreviousMenuProcessor backFromCompanyMenuToPreviousMenuProcessor;

    @Test
    public void shouldBackToPreviousMenu() {
        assertFalse(backFromCompanyMenuToPreviousMenuProcessor.doActionWithCompany());
    }

    @Test
    public void shouldReturnSupportedActionTitle() {
        assertEquals(backFromCompanyMenuToPreviousMenuProcessor.getSupportedCompanyActionTitle(), TEST_SUPPORTED_ACTION_TITLE);
    }
}
