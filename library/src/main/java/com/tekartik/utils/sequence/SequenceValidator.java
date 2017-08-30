package com.tekartik.utils.sequence;

/**
 * Created by alex on 23/05/16.
 * <p>
 * Basic tap pattern recognition
 */
public class SequenceValidator {

    public int sequenceIndex = 0;

    ;
    TYPE type;
    long lastTime = 0;

    public SequenceValidator(TYPE type) {
        this.type = type;
    }

    static public boolean smallDiff(long diff) {
        return diff <= 500;
    }

    static public boolean mediumDiff(long diff) {
        return diff <= 1000;
    }

    static public boolean largeDiff(long diff) {
        return diff >= 500 && diff < 3000;
    }

    public boolean validate(long timestamp) {
        boolean okToContinue = false;
        long diff = timestamp - lastTime;
        if (diff > 1500) {
            sequenceIndex = 0;
        }

        switch (type) {
            case TWO_THREE_ONE:
                if ((sequenceIndex == 0)
                        || (sequenceIndex == 1 && smallDiff(diff))
                        || (sequenceIndex == 2 && largeDiff(diff))
                        || (sequenceIndex == 3 && smallDiff(diff))
                        || (sequenceIndex == 4 && smallDiff(diff))
                        || (sequenceIndex == 5 && largeDiff(diff))
                        ) {
                    sequenceIndex++;
                } else {
                    sequenceIndex = 1;
                }

                if (sequenceIndex == 6) {
                    return true;
                }
                break;
            case SIX: {
                if (mediumDiff(diff)) {
                    sequenceIndex++;
                } else {
                    sequenceIndex = 1;
                }
                if (sequenceIndex == 6) {
                    return true;
                }
                break;
            }
        }

        lastTime = timestamp;
        return false;
    }

    public boolean validate() {
        return validate(System.currentTimeMillis());
    }

    public enum TYPE {
        TWO_THREE_ONE,
        SIX

    }
}
