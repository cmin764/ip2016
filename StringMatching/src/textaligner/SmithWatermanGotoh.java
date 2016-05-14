package textaligner;

import jaligner.*;
import jaligner.matrix.*;

public class SmithWatermanGotoh implements TextAligner {

    private String firstText;
    private String secondText;
    private Sequence firstSequence;
    private Sequence secondSequence;
    private float score;
    private int similarity;
    private int firstStart;
    private int secondStart;

    SmithWatermanGotoh() {
    }

    public SmithWatermanGotoh(String firstText, String secondText) {
        this.firstText = firstText;
        this.secondText = secondText;
    }

    public void setFirstText(String text) {
        firstText = text;
    }

    public void setSecondText(String text) {
        secondText = text;
    }

    public String getFirstText() {
        return firstText;
    }

    public String getSecondText() {
        return secondText;
    }

    public float getScore() {
        return score;
    }

    public int getSimilarity() {
        return similarity;
    }

    public int getFirstStart() {
        return firstStart;
    }

    public int getSecondStart() {
        return secondStart;
    }

    public void align() {
        firstSequence = new Sequence(firstText);
        secondSequence = new Sequence(secondText);

        try {
            Alignment alignment =
                SmithWatermanGotoh.align(firstSequence,
                        secondSequence,
                        MatrixLoader.load("BLOSUM70"), 1.0f, 0.0f);

            score = alignment.calculateScore();
            similarity = alignment.getSimilarity();
            firstStart = alignment.getStart1();
            secondStart = alignment.getStart2();
        } catch (MatrixLoaderException err) {
            System.err.println(err);
        }
    }
}
