package eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty;

import eu.jpereira.trainings.designpatterns.structural.adapter.exceptions.CodeMismatchException;
import eu.jpereira.trainings.designpatterns.structural.adapter.exceptions.IncorrectDoorCodeException;
import eu.jpereira.trainings.designpatterns.structural.adapter.model.Door;

public class ThirdPartyDoorObjectAdapter implements Door {

    public enum DoorState {
        OPEN, CLOSED;
    }

    private String code = ThirdPartyDoor.DEFAULT_CODE;
    private DoorState doorState = DoorState.CLOSED;

    @Override
    public void open(String code) throws IncorrectDoorCodeException {
        if (code.equals(this.code)) {
            doorState = DoorState.OPEN;
        }
        else throw new IncorrectDoorCodeException();
    }

    @Override
    public void close() {
        doorState = DoorState.CLOSED;
    }

    @Override
    public boolean isOpen() {
        return doorState == DoorState.OPEN;
    }

    @Override
    public void changeCode(String oldCode, String newCode, String newCodeConfirmation) throws IncorrectDoorCodeException, CodeMismatchException {
        if(oldCode.equals(this.code) && newCode.equals(newCodeConfirmation)) {
            this.code = newCode;
        }
        else if(!oldCode.equals(this.code)){
            throw new IncorrectDoorCodeException();
        }
        else throw new CodeMismatchException();
    }

    @Override
    public boolean testCode(String code) {
        return code.equals(this.code);
    }
}
