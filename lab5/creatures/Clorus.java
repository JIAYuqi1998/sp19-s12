package creatures;

import huglife.Action;
import huglife.Creature;
import huglife.Direction;
import huglife.Occupant;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

import static huglife.HugLifeUtils.randomEntry;

public class Clorus extends Creature {
    /**
     * red color.
     */
    private int r;
    /**
     * green color.
     */
    private int g;
    /**
     * blue color.
     */
    private int b;
    public Clorus(double n) {
        super("clorus");
        r = 34;
        g = 0;
        b = 231;
        energy = n;
    }

    @Override
    public void move() {
        this.energy = this.energy - 0.03;
    }

    @Override
    public void stay() {
        this.energy = this.energy - 0.01;

    }

    @Override
    public Color color() {
        return color(r, g, b);
    }

    @Override
    public void attack(Creature c) {
        this.energy = this.energy + c.energy();
    }

    @Override
    public Clorus replicate() {
        this.energy = this.energy / 2;
        Clorus newC = new Clorus(this.energy);
        return newC;
    }

/**1.If there are no empty squares, the Clorus
 * will STAY (even if there are Plips nearby they
 * could attack since plip squares do not count as
 * empty squares).
 * 2.Otherwise, if any Plips are seen, the Clorus will
 * ATTACK one of them randomly.
 * 3.Otherwise, if the Clorus has energy greater
 * than or equal to one, it will REPLICATE
 * to a random empty square.
 * 4.Otherwise, the Clorus will MOVE to
 * a random empty square.*/

    @Override
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        // Rule 1
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> plipNeighbors = new ArrayDeque<>();
        boolean isPlip = false;
        Boolean noEmpty = true;
        for (Direction key : neighbors.keySet()) {
            if (neighbors.get(key).name().equals("empty")) {
                emptyNeighbors.add(key);
                noEmpty = false;
                // check whether there is any empty room, if there is,
                // store the direction in emptyNeighbors;
            }
            if (neighbors.get(key).name().equals("plip")) {
                plipNeighbors.add(key);
                isPlip = true;
                // check whether there is any plip, if there is,
                // store the direction in plipNeighbors;
            }
        }
        // if there is no empty room, stay.
        if (noEmpty) {
            //stay();
            return new Action(Action.ActionType.STAY);
        } else {
            // if there is any plip around, attach a random plip
            if (isPlip) {
                Direction randomPlip = randomEntry(plipNeighbors);
                //this.attack((Creature) neighbors.get(randomPlip));
                return new Action(Action.ActionType.ATTACK, randomPlip);
            } else if (energy > 1) {
                // if there is no plip around, and the energy is higher than 1,
                // replicate to a random direction.
                //Clorus newClorus = this.replicate();
                Direction randomDirR = randomEntry(emptyNeighbors);
                //neighbors.put(randomDirR, newClorus);
                return new Action(Action.ActionType.REPLICATE, randomDirR);
            } else {
                Direction randomDirM = randomEntry(emptyNeighbors);
                return new Action(Action.ActionType.MOVE, randomDirM);
            }
        }
    }


}
