package edu.uaslp.project;

import java.util.Comparator;

public class ScoreSorter implements Comparator<Record> {
    @Override
    public int compare(Record o1, Record o2) {
        return Integer.compare(o1.getScorePlayer(), o2.getScorePlayer());
    }
}
