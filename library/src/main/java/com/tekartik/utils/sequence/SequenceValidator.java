package com.tekartik.utils.sequence;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static java.lang.Boolean.TRUE;

/**
 * Created by alex on 23/05/16.
 * <p>
 * Basic tap pattern recognition
 */
public class SequenceValidator {

    public int sequenceIndex = 0;

    final private TYPE type;

    static public class Multi extends SequenceValidator {

        static public final int DEFAULT_MULTI_COUNT = 6;

        private final int multiCount; // Default multi count

        public Multi() {
            this(DEFAULT_MULTI_COUNT);
        }

        public Multi(int multiCount) {
            super(null);
            this.multiCount = multiCount;
        }

        @Override
        protected boolean validate(long timestamp, long diff) {
            if (mediumDiff(diff)) {
                sequenceIndex++;
            } else {
                sequenceIndex = 1;
            }
            if (sequenceIndex == multiCount) {
                return true;
            }
            return false;
        }


    }

    static public class Suite extends SequenceValidator {

        static public final List<Integer> SUITE_LIST_DEFAUT = Arrays.asList(2, 3, 1);
        private final List<Integer> list;
        private final int sequenceCount;
        private final HashMap<Integer, Boolean> sequenceDiffs = new HashMap<>();


        public Suite() {
            this(SUITE_LIST_DEFAUT);
        }

        public Suite(List<Integer> list) {
            super(null);
            this.list = list;
            int index = 0;
            for (int count : list) {
                // for the first allow any
                index++;
                for (int i = 1; i < count; i++) {
                    // requires small diff then
                    sequenceDiffs.put(index++, true);
                }
            }
            sequenceCount = index;
        }

        @Override
        protected boolean validate(long timestamp, long diff) {
            Boolean checkSmallDiff = sequenceDiffs.get(sequenceIndex);
            if (TRUE.equals(checkSmallDiff)) {
                if (SequenceValidator.smallDiff(diff)) {
                    sequenceIndex++;
                } else {
                    sequenceIndex = 1;
                }
            } else if (SequenceValidator.largeDiff(diff)) {
                sequenceIndex++;
            } else {
                sequenceIndex = 1;
            }
            if (sequenceIndex == sequenceCount) {
                return true;
            }
            return false;
        }
    }

    private long lastTime = 0;


    @Deprecated
    public SequenceValidator(TYPE type) {
        this.type = type;
    }

    static private boolean smallDiff(long diff) {
        return diff <= 500;
    }

    // Ok in Multi, not ok in Suite for small group
    static private boolean mediumDiff(long diff) {
        return diff <= 1000;
    }

    // True if at least 500ms
    static private boolean largeDiff(long diff) {
        return diff >= 500 && diff < 3000;
    }

    protected boolean validate(long timestamp, long diff) {
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
        return false;

    }

    public boolean validate(long timestamp) {
        long diff = timestamp - lastTime;
        if (diff > 1500) {
            sequenceIndex = 0;
        }

        boolean validated = validate(timestamp, diff);
        lastTime = timestamp;
        return validated;
    }

    public boolean validate() {
        return validate(System.currentTimeMillis());
    }

    @Deprecated
    public enum TYPE {
        TWO_THREE_ONE,
        SIX,
    }
}
