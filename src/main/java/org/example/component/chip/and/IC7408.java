package org.example.component.chip.and;

import org.example.component.ChipComponent;
import org.example.component.ComponentType;
import org.example.component.chip.ChipType;
import org.example.component.pin.InputPin;
import org.example.component.pin.OutputPin;
import org.example.component.pin.Pin;
import org.example.component.pin.PinType;
import org.example.edu.uj.po.simulation.interfaces.PinState;
import org.example.logicmatrix.LogicMatrix;
import org.example.logicmatrix.and.IC7408LogicMatrix;

import java.util.ArrayList;
import java.util.List;

public class IC7408 extends ChipComponent {

    private int id;
    private ComponentType componentType;
    private List<Pin> pins = new ArrayList<>();

    private final ChipType chipType;
    private final LogicMatrix logicMatrix;

    public IC7408() {
        this.pins = setDefaultPins();
        this.logicMatrix = new IC7408LogicMatrix();
        this.chipType = ChipType.IC7408;
    }

    @Override
    public List<Pin> setDefaultPins() {
        List<Pin> defaultPins = new ArrayList<>();
        defaultPins.add(new InputPin(1, PinState.UNKNOWN, PinType.IN));
        defaultPins.add(new InputPin(2, PinState.UNKNOWN, PinType.IN));
        defaultPins.add(new OutputPin(3, PinState.UNKNOWN, PinType.OUT));
        defaultPins.add(new InputPin(4, PinState.UNKNOWN, PinType.IN));
        defaultPins.add(new InputPin(5, PinState.UNKNOWN, PinType.IN));
        defaultPins.add(new OutputPin(6, PinState.UNKNOWN, PinType.OUT));
        defaultPins.add(new OutputPin(8, PinState.UNKNOWN, PinType.OUT));
        defaultPins.add(new InputPin(9, PinState.UNKNOWN, PinType.IN));
        defaultPins.add(new InputPin(10, PinState.UNKNOWN, PinType.IN));
        defaultPins.add(new OutputPin(11, PinState.UNKNOWN, PinType.OUT));
        defaultPins.add(new InputPin(12, PinState.UNKNOWN, PinType.IN));
        defaultPins.add(new InputPin(13, PinState.UNKNOWN, PinType.IN));
        return defaultPins;
    }

    @Override
    public List<Pin> getPins() {
        return this.pins;
    }

    @Override
    public Pin getPin(int pinId) {
        return pins.stream()
                .filter(pin -> pin.getId() == pinId)
                .findFirst()
                .orElseThrow();
    }

    @Override
    public LogicMatrix getLogicMatrix() {
        return this.logicMatrix;
    }

    public ChipType getChipType() {
        return this.chipType;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Override
    public ComponentType getComponentType() {
        return this.componentType;
    }

    @Override
    public void setComponentType(ComponentType componentType) {
        this.componentType = componentType;
    }
}
