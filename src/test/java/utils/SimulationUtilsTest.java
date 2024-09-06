package utils;

import org.example.edu.uj.po.simulation.interfaces.ComponentPinState;
import org.example.edu.uj.po.simulation.interfaces.PinState;
import org.example.utils.SimulationUtils;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SimulationUtilsTest {

    @Test
    public void testGenerateComponentCombinations() {
        // given
        List<Integer> componentIds = List.of(1, 2, 3);
        // when
        List<List<Integer>> result = SimulationUtils.generateComponentCombinations(componentIds);
        // then
        assertEquals(6, result.size());
        assertTrue(result.contains(List.of(1)));
        assertTrue(result.contains(List.of(2)));
        assertTrue(result.contains(List.of(3)));
        assertTrue(result.contains(List.of(1, 2)));
        assertTrue(result.contains(List.of(1, 3)));
        assertTrue(result.contains(List.of(2, 3)));
    }

    @Test
    public void testIsSimulationResultsEqual() {
        // given
        Map<Integer, Set<ComponentPinState>> initialResult = Map.of(
                1, Set.of(new ComponentPinState(4, 1, PinState.HIGH)),
                2, Set.of(new ComponentPinState(4, 2, PinState.LOW))
        );
        Map<Integer, Set<ComponentPinState>> optimizationResult = Map.of(
                1, Set.of(new ComponentPinState(4, 1, PinState.HIGH)),
                2, Set.of(new ComponentPinState(4, 2, PinState.LOW))
        );
        // when/then
        assertTrue(SimulationUtils.isSimulationResultsEqual(initialResult, optimizationResult));
    }

    @Test
    public void testIsSimulationResultsNotEqual() {
        // given
        Map<Integer, Set<ComponentPinState>> initialResult = Map.of(
                1, Set.of(new ComponentPinState(4, 1, PinState.HIGH)),
                2, Set.of(new ComponentPinState(4, 2, PinState.LOW))
        );
        Map<Integer, Set<ComponentPinState>> optimizationResult = Map.of(
                1, Set.of(new ComponentPinState(4, 1, PinState.LOW)),
                2, Set.of(new ComponentPinState(4, 2, PinState.LOW))
        );
        // when/then
        assertFalse(SimulationUtils.isSimulationResultsEqual(initialResult, optimizationResult));
    }
}
