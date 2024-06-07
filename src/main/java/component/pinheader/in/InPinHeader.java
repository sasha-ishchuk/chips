package component.pinheader.in;

import component.ComponentType;
import component.PinHeaderComponent;
import component.pin.OutputPin;
import component.pin.Pin;
import component.pin.PinType;

import java.util.ArrayList;
import java.util.List;

public class InPinHeader extends PinHeaderComponent {

    int id = -1;
    int size;

    private ComponentType componentType;

    private List<Pin> pins = new ArrayList<>();

    public InPinHeader(int size) {
        this.size = size;
        this.pins = setDefaultPins();
    }

    @Override
    public List<Pin> setDefaultPins() {
        List<Pin> pins = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            pins.add(new OutputPin(i + 1, PinType.OUT));
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
        return id;
    }

    @Override
    public int getSize() {
        return size;
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
