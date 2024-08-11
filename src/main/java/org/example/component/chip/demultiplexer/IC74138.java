package org.example.component.chip.demultiplexer;

import org.example.component.ComponentType;
import org.example.component.chip.ChipType;
import org.example.component.pin.InputPin;
import org.example.component.pin.OutputPin;
import org.example.component.pin.Pin;
import org.example.component.pin.PinType;
import org.example.component.ChipComponent;
import org.example.edu.uj.po.simulation.interfaces.PinState;
import org.example.logicmatrix.LogicMatrix;
import org.example.logicmatrix.demultiplexer.IC74138LogicMatrix;

import java.util.ArrayList;
import java.util.List;

// decoder from 3 to 8
public class IC74138 extends ChipComponent {
    private int id;
    private ComponentType componentType;
    private List<Pin> pins = new ArrayList<>();

    private final ChipType chipType;
    private final LogicMatrix logicMatrix;

    public IC74138() {
        this.pins = setDefaultPins();
        this.logicMatrix = new IC74138LogicMatrix();
        this.chipType = ChipType.IC74138;
    }

    @Override
    public List<Pin> setDefaultPins() {
        List<Pin> defaultPins = new ArrayList<>();
        defaultPins.add(new InputPin(1, PinState.UNKNOWN, PinType.IN)); // A
        defaultPins.add(new InputPin(2, PinState.UNKNOWN, PinType.IN)); // B
        defaultPins.add(new InputPin(3, PinState.UNKNOWN, PinType.IN)); // C
        defaultPins.add(new InputPin(4, PinState.UNKNOWN, PinType.IN)); // ~G2A
        defaultPins.add(new InputPin(5, PinState.UNKNOWN, PinType.IN)); // ~G2B
        defaultPins.add(new InputPin(6, PinState.UNKNOWN, PinType.IN)); // G1

        defaultPins.add(new OutputPin(15, PinState.UNKNOWN, PinType.OUT)); // ~Y0
        defaultPins.add(new OutputPin(14, PinState.UNKNOWN, PinType.OUT)); // ~Y1
        defaultPins.add(new OutputPin(13, PinState.UNKNOWN, PinType.OUT)); // ~Y2
        defaultPins.add(new OutputPin(12, PinState.UNKNOWN, PinType.OUT)); // ~Y3
        defaultPins.add(new OutputPin(11, PinState.UNKNOWN, PinType.OUT)); // ~Y4
        defaultPins.add(new OutputPin(10, PinState.UNKNOWN, PinType.OUT)); // ~Y5
        defaultPins.add(new OutputPin(9, PinState.UNKNOWN, PinType.OUT)); // ~Y6
        defaultPins.add(new OutputPin(7, PinState.UNKNOWN, PinType.OUT)); // ~Y7
        return defaultPins;
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

    @Override
    public List<Pin> getPins() {
        return this.pins;
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
