package eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty;

import eu.jpereira.trainings.designpatterns.structural.adapter.exceptions.CodeMismatchException;
import eu.jpereira.trainings.designpatterns.structural.adapter.exceptions.IncorrectDoorCodeException;
import eu.jpereira.trainings.designpatterns.structural.adapter.model.Door;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.exceptions.CannotChangeCodeForUnlockedDoor;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.exceptions.CannotChangeStateOfLockedDoor;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.exceptions.CannotUnlockDoorException;

//NEW CODE
public class ThirdPartyDoorAdapter extends ThirdPartyDoor implements Door {

    @Override
    public void open(String code) throws IncorrectDoorCodeException {
        try{
            //Try to unlock the door
            unlock(code);
            //Set the door state to OPEN
            setState(DoorState.OPEN);
        } catch(CannotUnlockDoorException | CannotChangeStateOfLockedDoor e)
        {
            throw new IncorrectDoorCodeException();
        }
    }

    @Override
    public void close() {
        try{
            setState(DoorState.CLOSED);
        } catch (CannotChangeStateOfLockedDoor e) {
            throw new IllegalStateException("Cannot close a locked door");
        }
    }

    @Override
    public boolean isOpen() {
        return getState() == DoorState.OPEN;
    }

    @Override
    public void changeCode(String oldCode, String newCode, String newCodeConfirmation) throws IncorrectDoorCodeException, CodeMismatchException {
        if(!newCode.equals(newCodeConfirmation)){
            throw new CodeMismatchException();
        }
        try{
            unlock(oldCode);
            setNewLockCode(newCode);
            lock();
        }
        catch(CannotUnlockDoorException e) {
            throw new IncorrectDoorCodeException();
        }
        catch (CannotChangeCodeForUnlockedDoor e) {
            throw new IllegalStateException("Cannot change code of an unlocked door");
        }
    }

    @Override
    public boolean testCode(String code) {
        try{
            unlock(code);
            lock();
            return true;
        }
        catch(CannotUnlockDoorException e) {
            return false;
        }
    }
}
