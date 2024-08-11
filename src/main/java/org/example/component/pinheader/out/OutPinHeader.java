package org.example.component.pinheader.out;

import org.example.component.ComponentType;
import org.example.component.PinHeaderComponent;
import org.example.component.pin.InputPin;
import org.example.component.pin.Pin;
import org.example.component.pin.PinType;

import java.util.ArrayList;
import java.util.List;

public class OutPinHeader extends PinHeaderComponent {

    int id = -1;
    int size;
    private ComponentType componentType;
    private List<Pin> pins = new ArrayList<>();

    public OutPinHeader(int size) {
        this.size = size;
        this.pins = setDefaultPins();
    }

    @Override
    public List<Pin> setDefaultPins() {
        List<Pin> pins = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            pins.add(new InputPin(i + 1, PinType.IN));
        }
        return pins;
    }

    @Override
    public List<Pin> getPins() {
        return pins;
    }

    @Override
    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public Pin getPin(int pinId) {
        return pins.stream()
                .filter(pin -> pin.getId() == pinId)
                .findFirst()
                .orElseThrow();
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public int getSize() {
        return this.size;
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
