package com.cooksys.ftd.assignments.collections.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class OrgChart {

	private Set<Employee> orgSet = new HashSet<>();
//	private Set<Manager> orgSet = new HashSet<>();

	// TODO: this class needs to store employee data in private fields in order for
	// the other methods to work as intended.
	// Add those fields here. Consider how you want to store the data, and which
	// collection types to use to make
	// implementing the other methods as easy as possible. There are several
	// different ways to approach this problem, so
	// experiment and don't be afraid to change how you're storing your data if it's
	// not working out!

	/**
	 * TODO: Implement this method <br>
	 * <br>
	 * Adds a given {@code Employee} to the {@code OrgChart}. If the
	 * {@code Employee} is successfully added, this method should return true. If
	 * the {@code Employee} was not successfully added, it should return false. In
	 * order to determine if and how an {@code Employee} can be added, refer to the
	 * following algorithm: <br>
	 * <br>
	 * If the given {@code Employee} is already present in the {@code OrgChart}, do
	 * not add it and return false. Otherwise: <br>
	 * <br>
	 * If the given {@code Employee} has a {@code Manager} and that {@code Manager}
	 * is not part of the {@code OrgChart} yet, add that {@code Manager} first and
	 * then add the given {@code Employee}, and return true. <br>
	 * <br>
	 * If the given {@code Employee} has no {@code Manager}, but is a
	 * {@code Manager} itself, add it to the {@code OrgChart} and return true. <br>
	 * <br>
	 * If the given {@code Employee} has no {@code Manager} and is not a
	 * {@code Manager} itself, do not add it and return false.
	 *
	 * @param employee the {@code Employee} to add to the {@code OrgChart}
	 * @return true if the {@code Employee} was added successfully, false otherwise
	 */
	public boolean addEmployee(Employee employee) {
		if (employee == null) {
			return false;
		} else if (orgSet.contains(employee)) {
			return false;
		} else if (employee.hasManager() && orgSet.contains(employee.getManager())) {
			orgSet.add(employee);
			return true;
		} else if (employee.hasManager() && !orgSet.contains(employee.getManager())) {
			for (Manager manager : employee.getChainOfCommand()) {
				if (!orgSet.contains(manager)) {
					orgSet.add(manager);
				}
			}
			orgSet.add(employee);
			return true;
		} else if (!employee.hasManager() && (employee instanceof Manager)) {
			orgSet.add(employee);
			return true;
		}

		return false;
	}

	/**
	 * TODO: Implement this method <br>
	 * <br>
	 * Checks if a given {@code Employee} has already been added to the
	 * {@code OrgChart}.
	 *
	 * @param employee the {@code Employee} to search for
	 * @return true if the {@code Employee} has been added to the {@code OrgChart},
	 *         false otherwise
	 */
	public boolean hasEmployee(Employee employee) {
		if (orgSet.contains(employee)) {
			return true;
		}
		return false;
	}

	/**
	 * TODO: Implement this method <br>
	 * <br>
	 * Retrieves all {@code Employee}s that have been added to the {@code OrgChart}
	 * as a flat {@code Set}. If no {@code Employee}s have been added to the
	 * {@code OrgChart} yet, the returned {@code Set} should be empty (not
	 * {@code null}!).
	 *
	 * @return all {@code Employee}s in the {@code OrgChart}, or an empty
	 *         {@code Set} if no {@code Employee}s have been added to the
	 *         {@code OrgChart}
	 */
	public Set<Employee> getAllEmployees() {
		Set<Employee> empSet = new HashSet<>(orgSet);
		return empSet;
	}

	/**
	 * TODO: Implement this method <br>
	 * <br>
	 * Retrieves all {@code Manager}s that have been added to the {@code OrgChart}
	 * as a flat {@code Set}. If no {@code Manager}s have been added to the
	 * {@code OrgChart}, the returned {@code Set} should be empty (not
	 * {@code null}!).
	 *
	 * @return all {@code Manager}s in the {@code OrgChart}, or an empty {@code Set}
	 *         if no {@code Manager}s have been added to the {@code OrgChart}
	 */
	public Set<Manager> getAllManagers() {
		Set<Manager> manSet = new HashSet<>();
		for (Employee employees : getAllEmployees()) {
			if (employees instanceof Manager) {
				manSet.add((Manager) employees);
			}
		}
		return manSet;
	}

	/**
	 * TODO: Implement this method <br>
	 * <br>
	 * Retrieves all of the direct subordinates of a given {@code Manager} as a flat
	 * {@code Set}. <br>
	 * <br>
	 * These are the {@code Employee}s (both {@code Worker}s and {@code Manager}s)
	 * that return the given {@code Manager} when their {@code .getManager()} method
	 * is called. <br>
	 * <br>
	 * If the given {@code Manager} does not have any subordinates in the
	 * {@code OrgChart}, or if the given {@code Manager} is itself not in the
	 * {@code OrgChart}, the returned {@code Set} should be empty, but not
	 * {@code null}.
	 *
	 * @param manager the {@code Manager} whose direct subordinates need to be
	 *                returned
	 * @return all {@code Employee}s in the {@code OrgChart} that have the given
	 *         {@code Manager} as a direct parent, or an empty set if the
	 *         {@code Manager} is not present in the {@code OrgChart} or if there
	 *         are no subordinates for the given {@code Manager}
	 */
	public Set<Employee> getDirectSubordinates(Manager manager) {
		Set<Employee> subSet = new HashSet<>();

		if (!hasEmployee(manager)) {
			return subSet;
		}

		for (Employee possibleSub : orgSet) {
			if (possibleSub.hasManager()) {
				if (possibleSub.getManager().equals(manager)) {
					subSet.add(possibleSub);
				}
			}
		}
		return subSet;
	}

	/**
	 * TODO: Implement this method <br>
	 * <br>
	 * Retrieves a full representation of this {@code OrgChart} as a {@code Map} of
	 * {@code Manager} keys and {@code Set<Employee>} values. Every single
	 * {@code Manager} in the {@code OrgChart} should appear as a key in the
	 * returned {@code Map}, and for each (@code Manager} key, every
	 * {@code Employee} (both {@code Worker}s and {@code Manager}s) in the
	 * {@code OrgChart} whose direct manager is that key should be in the associated
	 * {@code Set<Employee>} value. <br>
	 * <br>
	 * Like the other methods, the return value of this method should not be
	 * {@code null} or contain {@code null}, either in its keys or values. An empty
	 * {@code Map} should be returned if the {@code OrgChart} is empty.
	 *
	 * @return a map in which the keys represent the parent {@code Manager}s in the
	 *         {@code OrgChart}, and the each value is a set of the direct
	 *         subordinates of the associated {@code Manager}, or an empty map if
	 *         the {@code OrgChart} is empty.
	 */
	public Map<Manager, Set<Employee>> getFullHierarchy() {
		Map<Manager, Set<Employee>> map = new HashMap<>();
		
		for(Manager manager : getAllManagers()) {
			map.put(manager, getDirectSubordinates(manager));
		}
		
		return map;
	}

}
