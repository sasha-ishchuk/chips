package org.example.component.chip;

public enum ChipType {
    UNSET,
    IC7400, IC7410, IC7420, // NAND
    IC7402, // NOR
    IC7404, // NOT
    IC7408, IC7411, // AND
    IC7432, // OR
    IC7431, // 6 delay elements
    IC7434, // 6 buffer elements
    IC7442, // BCD decoder to "1-of-10" code
    IC7444, // Gray's code decoder to "1-of-10" code
    IC7482, // full 2-bit adder

    IC74138, // 3-line to 8-line decoder/de-multiplexer
    IC74152 // 8-input data selector/multiplexer
}
