package org.example.utils;

import org.example.edu.uj.po.simulation.interfaces.ComponentPinState;
import org.example.edu.uj.po.simulation.interfaces.PinState;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SimulationUtils {

    public static List<List<Integer>> generateComponentCombinations(Collection<Integer> componentIds) {
        List<List<Integer>> combinations = new ArrayList<>();
        List<Integer> componentList = new ArrayList<>(componentIds);
        generateCombinationsHelper(combinations, new ArrayList<>(), componentList, 0);
        return combinations;
    }

    private static void generateCombinationsHelper(List<List<Integer>> combinations,
                                                   List<Integer> combination,
                                                   List<Integer> componentList, int start) {
        if (!combination.isEmpty() && combination.size() != componentList.size()) {
            combinations.add(new ArrayList<>(combination));
        }
        for (int i = start; i < componentList.size(); i++) {
            combination.add(componentList.get(i));
            generateCombinationsHelper(combinations, combination, componentList, i + 1);
            combination.remove(combination.size() - 1);
        }
    }

    public static boolean isSimulationResultsEqual(Map<Integer, Set<ComponentPinState>> initialResult,
                                                   Map<Integer, Set<ComponentPinState>> simulationResult) {
        if (initialResult.size() != simulationResult.size()) {
            return false;
        }
        for (Map.Entry<Integer, Set<ComponentPinState>> entry : initialResult.entrySet()) {
            if (!simulationResult.containsKey(entry.getKey())) {
                return false;
            }
            Set<ComponentPinState> states1 = entry.getValue();
            Set<ComponentPinState> states2 = simulationResult.get(entry.getKey());
            if (states1.size() != states2.size()) {
                return false;
            }
            for (ComponentPinState state : states1) {
                int componentId = state.componentId();
                int pinId = state.pinId();
                PinState pinState = state.state();
                if (!checkIfPinStateExists(states2, componentId, pinId, pinState)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean checkIfPinStateExists(Set<ComponentPinState> states,
                                                 int componentId,
                                                 int pinId,
                                                 PinState pinState) {
        for (ComponentPinState state : states) {
            if (state.componentId() == componentId && state.pinId() == pinId && state.state().equals(pinState)) {
                return true;
            }
        }
        return false;
    }

    public static Set<Integer> findMaxPossibleComponentsToRemove(List<Set<Integer>> componentsToRemove) {
        if (componentsToRemove == null || componentsToRemove.isEmpty()) {
            return new HashSet<>();
        }
        Set<Integer> maxPossibleComponentsToRemove = new HashSet<>();
        int max = 0;
        for (Set<Integer> components : componentsToRemove) {
            if (components.size() > max) {
                max = components.size();
                maxPossibleComponentsToRemove = components;
            }
        }
        return maxPossibleComponentsToRemove;
    }
}
