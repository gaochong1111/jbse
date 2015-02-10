package jbse.algo;

import static jbse.algo.Util.createAndThrowObject;
import static jbse.algo.Util.throwVerifyError;
import static jbse.bc.Offsets.ARRAYLENGTH_OFFSET;
import static jbse.bc.Signatures.NULL_POINTER_EXCEPTION;

import jbse.mem.Array;
import jbse.mem.State;
import jbse.mem.exc.InvalidProgramCounterException;
import jbse.mem.exc.OperandStackEmptyException;
import jbse.mem.exc.ThreadStackEmptyException;
import jbse.val.Reference;

final class Algo_ARRAYLENGTH implements Algorithm {
	
	@Override
    public void exec(State state, ExecutionContext ctx) 
    throws ThreadStackEmptyException, OperandStackEmptyException {
        final Reference tmpRef = (Reference) state.pop();
        if (state.isNull(tmpRef)) {
        	createAndThrowObject(state, NULL_POINTER_EXCEPTION);
			return;
        }
        
        final Array tmpArray = (Array) state.getObject(tmpRef);
        state.push(tmpArray.getLength());

        try {
			state.incPC(ARRAYLENGTH_OFFSET);
		} catch (InvalidProgramCounterException e) {
		    throwVerifyError(state);
		}
    } 
}