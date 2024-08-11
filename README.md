# Logic (digital) circuit simulation

---
IMPORTANT: 
- the idea of the project belongs to Dr. Piotr Oramus, UJ
- the project was created as a final assignment for the Object Design course, UJ 2024
---

## Objectives & assumptions
- simulate a logical (digital) circuit consisting of connected integrated circuits  
- circuits operate on states 0 (LOW) and 1 (HIGH)
- circuits output state depends on the input state ("combinational" circuits, which do not have "memory")
- the time factor must be taken into account in the simulations: the output state will change to be consistent with 
the current state of their inputs only after some time (signal propagation time)
- the signal propagation time (the time needed for the outputs to obtain a state corresponding to the input state) 
will be constant and identical for all circuits
