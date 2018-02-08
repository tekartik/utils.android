package com.tekartik.utils.sequence;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */

public class SequenceValidatorTest {

    @Test
    public void suiteValidatorTwoThreeOneOk() throws Exception {
        SequenceValidator validator = new SequenceValidator.Suite();

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
    public void suiteValidatorTwoThreeOneDommed() throws Exception {
        SequenceValidator validator = new SequenceValidator.Suite();

        assertFalse(validator.validate(0));
        assertEquals(1, validator.sequenceIndex);
        assertFalse(validator.validate(0));
        assertEquals(2, validator.sequenceIndex);
        assertFalse(validator.validate(0));
        assertEquals(1, validator.sequenceIndex);
    }

    @Test
    public void suiteValidatorOne() throws Exception {
        SequenceValidator validator = new SequenceValidator.Suite(Arrays.asList(1));

        assertTrue(validator.validate(0));
        assertEquals(1, validator.sequenceIndex);
        assertTrue(validator.validate(0));
        assertEquals(1, validator.sequenceIndex);
        assertFalse(validator.validate(1500));
        assertEquals(2, validator.sequenceIndex);
        assertTrue(validator.validate(1500));
        assertEquals(1, validator.sequenceIndex);
    }


    @Test
    public void validatorMultiDefaultOk() throws Exception {
        SequenceValidator validator = new SequenceValidator.Multi();

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
    public void validatorMultiTwoOk() throws Exception {
        SequenceValidator validator = new SequenceValidator.Multi(2);

        assertFalse(validator.validate(0));
        assertEquals(1, validator.sequenceIndex);
        assertTrue(validator.validate(500));
        assertEquals(2, validator.sequenceIndex);
        assertFalse(validator.validate(1000));
        assertEquals(3, validator.sequenceIndex);
        assertFalse(validator.validate(2500));
        assertEquals(1, validator.sequenceIndex);
    }

    @Test
    public void validatorSixDommed() throws Exception {
        SequenceValidator validator = new SequenceValidator.Multi();

        assertFalse(validator.validate(0));
        assertEquals(1, validator.sequenceIndex);
        assertFalse(validator.validate(1000));
        assertEquals(2, validator.sequenceIndex);
        assertFalse(validator.validate(2001));
        assertEquals(1, validator.sequenceIndex);
    }

}