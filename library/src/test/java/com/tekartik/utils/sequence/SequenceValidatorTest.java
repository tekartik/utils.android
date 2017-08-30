package com.tekartik.utils.sequence;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */

public class SequenceValidatorTest {
    @Test
    public void validatorTwoThreeOneOk() throws Exception {
        SequenceValidator validator = new SequenceValidator(SequenceValidator.TYPE.TWO_THREE_ONE);

        assertFalse(validator.validate(0));
        assertEquals(1, validator.sequenceIndex);
        assertFalse(validator.validate(0));
        assertEquals(2, validator.sequenceIndex);
        assertFalse(validator.validate(500));
        assertEquals(3, validator.sequenceIndex);
        assertFalse(validator.validate(500));
        assertEquals(4, validator.sequenceIndex);
        assertFalse(validator.validate(500));
        assertEquals(5, validator.sequenceIndex);
        assertTrue(validator.validate(1000));
        assertEquals(6, validator.sequenceIndex);

    }

    @Test
    public void validatorTwoThreeOneDommed() throws Exception {
        SequenceValidator validator = new SequenceValidator(SequenceValidator.TYPE.TWO_THREE_ONE);

        assertFalse(validator.validate(0));
        assertEquals(1, validator.sequenceIndex);
        assertFalse(validator.validate(0));
        assertEquals(2, validator.sequenceIndex);
        assertFalse(validator.validate(0));
        assertEquals(1, validator.sequenceIndex);
    }

    @Test
    public void validatorSixOk() throws Exception {
        SequenceValidator validator = new SequenceValidator(SequenceValidator.TYPE.SIX);

        assertFalse(validator.validate(0));
        assertEquals(1, validator.sequenceIndex);
        assertFalse(validator.validate(0));
        assertEquals(2, validator.sequenceIndex);
        assertFalse(validator.validate(500));
        assertEquals(3, validator.sequenceIndex);
        assertFalse(validator.validate(500));
        assertEquals(4, validator.sequenceIndex);
        assertFalse(validator.validate(500));
        assertEquals(5, validator.sequenceIndex);
        assertTrue(validator.validate(1000));
        assertEquals(6, validator.sequenceIndex);

    }

    @Test
    public void validatorSixDommed() throws Exception {
        SequenceValidator validator = new SequenceValidator(SequenceValidator.TYPE.SIX);

        assertFalse(validator.validate(0));
        assertEquals(1, validator.sequenceIndex);
        assertFalse(validator.validate(1000));
        assertEquals(2, validator.sequenceIndex);
        assertFalse(validator.validate(2001));
        assertEquals(1, validator.sequenceIndex);
    }

}