///////////////////////////////////
// This is the main shift register class.
// Notice that it implements the ILFShiftRegister interface.
// You will need to fill in the functionality.
///////////////////////////////////

import java.sql.Array;

/**
 * class ShiftRegister
 * @author
 * Description: implements the ILFShiftRegister interface.
 */
public class ShiftRegister implements ILFShiftRegister {
    ///////////////////////////////////
    // Create your class variables here
    ///////////////////////////////////
    // TODO:
    private int[] seed;
    private final int tap;
    private int[] shiftRegisterState = null;
    ///////////////////////////////////
    // Create your constructor here:
    ///////////////////////////////////
    ShiftRegister(int size, int tap) {
        // TODO:
        this.seed = new int[size];
        // one of the bits and must be between 0 and size - 1 (inclusive).
        this.tap = tap;
    }

    ///////////////////////////////////
    // Create your class methods here:
    ///////////////////////////////////
    /**
     * setSeed
     * @param seed
     * Description:
     */
    @Override
    public void setSeed(int[] seed) {
        // TODO:
        boolean isValid = true;
        // Check if the each value of the seed is valid
        for(int i = 0; i < seed.length; i++) {
            if (seed[i] < 0 || seed[i] > 1) {
                isValid = false;
                break;
            }
        }
        if (isValid == true) {
            this.seed = reverse(seed, 0, seed.length - 1);
            this.shiftRegisterState = this.seed;
        }
        return;
    }

    private int[] reverse(int[] arr, int start, int end) {
        if (start >= end) {
            return arr;
        } else {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            return reverse(arr, start + 1, end - 1);
        }
    }
    /**
     * shift
     * @return
     * Description:
     */
    @Override
    public int shift() {
        // TODO:
        // 1. XOR of the most significant bit (left) and the tap bit.
        // 2. The most significant bit is dropped.
        // 3. Every bit is moved one slot to the left.
        // 4. The least significant bit is set to the feedback bit.
        /* What do I need to perform the XOR operation?
        * 1. Most significant bit
        * 2. Next significant bit
        * 3. tap bit ((shiftRegister.length - this.tap) - 1)
        */
        this.shiftRegisterState = recurseShiftRegister(this.shiftRegisterState);
        return this.shiftRegisterState[this.shiftRegisterState.length - 1];
    }

    private int[] recurseShiftRegister(int[] shiftRegister) {
        int index = 0;
        int tapBit = shiftRegister[(shiftRegister.length - this.tap) - 1];
        int mostSignificantBit = shiftRegister[0];
        int[] tempShiftRegisterState = new int[shiftRegister.length];
        while (index < shiftRegister.length - 1) {
            tempShiftRegisterState[index] = shiftRegister[index + 1];
            index = index + 1;
        }
        tempShiftRegisterState[index] = tapBit ^ mostSignificantBit;
        return tempShiftRegisterState;
    }

    /**
     * generate
     * @param k
     * @return
     * Description:
     */
    @Override
    public int generate(int k) {
        // TODO:
        int v = 0;
        int j = 0;
        while(k > 0) {
            v = v * 2;
            j = shift();
            v = v + j;
            k = k - 1;
        }
        return v;
    }

    /**
     * Returns the integer representation for a binary int array.
     * @param array
     * @return
     */
    private int toBinary(int[] array) {
        // TODO:
        return 0;
    }
}
