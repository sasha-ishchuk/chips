package org.example.component.chip.decoder;

import org.example.component.ComponentType;
import org.example.component.chip.ChipType;
import org.example.component.pin.InputPin;
import org.example.component.pin.OutputPin;
import org.example.component.pin.Pin;
import org.example.component.pin.PinType;
import org.example.component.ChipComponent;
import org.example.edu.uj.po.simulation.interfaces.PinState;
import org.example.logicmatrix.LogicMatrix;
import org.example.logicmatrix.decoder.IC7442LogicMatrix;

import java.util.ArrayList;
import java.util.List;

// BCD decoder -> 1-of-10 decoder
public class IC7442 extends ChipComponent {

    private int id;
    private ComponentType componentType;
    private List<Pin> pins = new ArrayList<>();

    private final ChipType chipType;
    private final LogicMatrix logicMatrix;

    public IC7442() {
        this.pins = setDefaultPins();
        this.logicMatrix = new IC7442LogicMatrix();
        this.chipType = ChipType.IC7442;
    }

    @Override
    public List<Pin> setDefaultPins() {
        List<Pin> defaultPins = new ArrayList<>();
        defaultPins.add(new OutputPin(1, PinState.UNKNOWN, PinType.OUT)); // 0
        defaultPins.add(new OutputPin(2, PinState.UNKNOWN, PinType.OUT)); // 1
        defaultPins.add(new OutputPin(3, PinState.UNKNOWN, PinType.OUT)); // 2
        defaultPins.add(new OutputPin(4, PinState.UNKNOWN, PinType.OUT)); // 3
        defaultPins.add(new OutputPin(5, PinState.UNKNOWN, PinType.OUT)); // 4
        defaultPins.add(new OutputPin(6, PinState.UNKNOWN, PinType.OUT)); // 5
        defaultPins.add(new OutputPin(7, PinState.UNKNOWN, PinType.OUT)); // 6
        defaultPins.add(new OutputPin(9, PinState.UNKNOWN, PinType.OUT)); // 7
        defaultPins.add(new OutputPin(10, PinState.UNKNOWN, PinType.OUT)); // 8
        defaultPins.add(new OutputPin(11, PinState.UNKNOWN, PinType.OUT)); // 9

        defaultPins.add(new InputPin(12, PinState.UNKNOWN, PinType.IN)); // D
        defaultPins.add(new InputPin(13, PinState.UNKNOWN, PinType.IN)); // C
        defaultPins.add(new InputPin(14, PinState.UNKNOWN, PinType.IN)); // B
        defaultPins.add(new InputPin(15, PinState.UNKNOWN, PinType.IN)); // A
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
