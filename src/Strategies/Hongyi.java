package Strategies;

import Move.move;
import Suit.card;

import java.util.*;

/**
 * Author: Hongyi Zhu
 * Project: Blotto-Goofspiel
 * Strategy: Observe-React Strategy *
 */
public class Hongyi extends strategy {

    private int opFollow, opRandom, opFollow_a0;
    private double randomPortion;
    private Random generator;

    public Hongyi() {
        super("Hongyi");
    }

    @Override
    public void init() {
        opFollow = 0;
        opRandom = 0;
        opFollow_a0 = -1;
        randomPortion = 0;
        generator = new Random(Double.doubleToLongBits(Math.random()));
    }

    @Override
    public int[] nextPlay(int round, int[] upCards) throws Exception {
        int[] nextPlay;

        if (myHand.listUnusedCards().size() == 13) {
            if (round > 0) {
                opFollow_a0 = evaluateFollowA0(round)[0];
                int count = evaluateFollowA0(round)[1];
                if (count <= 5) {
                    opRandom += 1;
                } else {
                    opFollow += 1;
                }
                randomPortion = opRandom / (opRandom + opFollow) * 1.0;
            }
        }

        if (myHand.listUnusedCards().size() == 13) {
            nextPlay = getFirstTurn(upCards, opFollow_a0, randomPortion);
        } else if (myHand.listUnusedCards().size() == 1) {
            return myHand.pickRandomCards(1);
        } else {
            nextPlay = getMiddleTurns(upCards, round);
        }

        return nextPlay;
    }

    private int[] getFirstTurn(int[] upCards, int a0, double portion) {
        if (generator.nextFloat() <= portion) {
            return upCards;
        } else {
            int[] ret = new int[3];
            int[] followPN = getFollowPlusN((a0 + 1) % 13);

            for (int i = 0; i < upCards.length; i++) {
                ret[i] = followPN[upCards[i]];
            }

            return ret;
        }
    }

    private int[] getMiddleTurns(int[] upCards, int round) {
        int[] evalResult;
        int[] ret = new int[3];

        if (myHand.listUnusedCards().size() == 10) {
            evalResult  = evaluatePreviousTurns(round)[0];
            if (evalResult[1] < 3) {
                ret = upCards;
            } else {
                int[] followPN = getFollowPlusN(evalResult[0] + 1);
                for (int i = 0; i < upCards.length; i++) {
                    ret[i] = followPN[upCards[i]];
                }
            }
        } else if (myHand.listUnusedCards().size() == 7) {
            evalResult = evaluatePreviousTurns(round)[1];
            if (evalResult[1] <= 3) {
                ret = upCards;
            } else {
                int[] followPN = getFollowPlusN(evalResult[0] + 1);
                for (int i = 0; i < upCards.length; i++) {
                    ret[i] = followPN[upCards[i]];
                }
            }
        } else if (myHand.listUnusedCards().size() == 4) {
            evalResult = evaluatePreviousTurns(round)[2];
            if (evalResult[1] <= 5) {
                ret = upCards;
            } else {
                int[] followPN = getFollowPlusN(evalResult[0] + 1);
                for (int i = 0; i < upCards.length; i++) {
                    ret[i] = followPN[upCards[i]];
                }
            }
        }

        Set<Integer> unused = new HashSet<Integer>();

        for (card c : myHand.listUnusedCards()) {
            unused.add(c.getValue());
        }

        int[] idx = getSortedIndex(upCards);

        for (int i : idx) {
            if (!unused.contains(ret[i])) {
                int j = ret[i];
                while (true) {
                    if (i == idx[0] || i == idx[1]) {
                        j = (j + 1) % 14;
                    } else {
                        j = (j - 1 == 0) ? 13 : j - 1;
                    }
                    if (unused.contains(j)) {
                        ret[i] = j;
                        unused.remove(j);
                        break;
                    }
                }
            } else {
                unused.remove(ret[i]);
            }
        }

        return ret;
    }

    private int[] getSortedIndex(int[] upCards) {
        int[] idx = new int[]{-1, -1, -1};

        if (upCards[0] > upCards[1] && upCards[0] > upCards[2]) {
            idx[0] = 0;
            if (upCards[1] > upCards[2]) {
                idx[1] = 1;
                idx[2] = 2;
            } else {
                idx[1] = 2;
                idx[2] = 1;
            }
        } else if (upCards[0] > upCards[1]) {
            idx[0] = 2;
            idx[1] = 0;
            idx[2] = 1;
        } else if (upCards[0] > upCards[2]) {
            idx[0] = 1;
            idx[1] = 0;
            idx[2] = 2;
        } else {
            idx[2] = 0;
            if (upCards[1] > upCards[2]) {
                idx[1] = 2;
                idx[0] = 1;
            } else {
                idx[0] = 2;
                idx[1] = 1;
            }
        }

        return idx;
    }

    /**
     * Evaluate follow strategy played by the opponents
     */
    private int[][] evaluatePreviousTurns(int round) {
        int[] mov = new int[14];
        int turn = 0;
        int[][] turnResult = new int[5][2];
        try {
            ArrayList<move> previousTurns = allHistory.getSequenceOfMoves(round);

            for (int i = 0; i < mov.length; i++) {
                mov[i] = -1;
            }

            for (move m: previousTurns) {
                for (int i = 0; i < m.getUpCards().length; i++) {
                    mov[m.getUpCards()[i]] = m.getOppCards()[i];
                }

                int match;
                int max_match = -1;
                int max_i = -1;
                for (int i = 0; i < 13; i++){
                    int[] followN = getFollowPlusN(i);
                    match = 0;
                    for (int j = 1; j < 14; j++) {
                        if (followN[j] == mov[j]) {
                            match += 1;
                        }
                    }
                    if (match > max_match) {
                        max_match = match;
                        max_i = i;
                    }
                }
                turnResult[turn][0] = max_i;
                turnResult[turn][1] = max_match;

                turn += 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return turnResult;
    }

    private int[] getFollowPlusN(int N) {
        int[] base = new int[14];

        for (int i = 1; i < 14; i++) {
            base[i] = (i + N) > 13 ? (i + N) % 13 : i + N;
        }

        return base;
    }

    private int[] evaluateFollowA0(int round) {
        return evaluatePreviousTurns(round - 1)[4];
    }

    @Override
    public strategy newInstance() {
        return new Hongyi();
    }
}
