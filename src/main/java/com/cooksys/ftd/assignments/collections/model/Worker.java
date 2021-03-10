package com.cooksys.ftd.assignments.collections.model;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: Implement this class <br>
 * <br>
 * A worker is a type of employee that cannot be a superior to another employee.
 * <br>
 * A worker should have a name, and, optionally, a manager superior to them.
 */
public class Worker implements Employee {

	private String name;
	private Manager manager;

	// TODO: Does this class need private fields? If so, add them here

	/**
	 * TODO: Implement this constructor.
	 *
	 * @param name the name of the worker to be created
	 */
	public Worker(String name) {
		this.name = name;
	}

	/**
	 * TODO: Implement this constructor.
	 *
	 * @param name    the name of the worker to be created
	 * @param manager the direct manager of the worker to be created
	 */
	public Worker(String name, Manager manager) {
		this.name = name;
		this.manager = manager;
	}

	/**
	 * TODO: Implement this getter.
	 *
	 * @return the name of the worker
	 */
	@Override
	public String getName() {
		return this.name;
	}

	/**
	 * TODO: Implement this getter.
	 *
	 * @return {@code true} if this worker has a manager, or {@code false} otherwise
	 */
	@Override
	public boolean hasManager() {

		if (this.manager != null) {
			return true;
		}

		return false;
	}

	/**
	 * TODO: Implement this getter.
	 *
	 * @return the manager of this worker, or null if it has none
	 */
	@Override
	public Manager getManager() {
		return this.manager;
	}

	/**
	 * TODO: Implement this method. <br>
	 * <br>
	 * Retrieves the worker's chain of command as a {@code List<Manager>}, starting
	 * with their direct {@code Manager}, followed by that {@code Manager}'s
	 * {@code Manager}, and so on, until the top of the hierarchy is reached. <br>
	 * <br>
	 * The returned list should never be or contain {@code null}. <br>
	 * <br>
	 * If the worker does not have a {@code Manager}, an empty {@code List<Manager>}
	 * should be returned.
	 *
	 * @return a {@code List<Manager>} that represents the worker's chain of
	 *         command,
	 */
	@Override
	public List<Manager> getChainOfCommand() {
		List<Manager> manager = new ArrayList<Manager>();
		if (hasManager() == false) {
			return manager;
		} else {
			Manager currMan = this.manager;
			manager.add(currMan);
			while( currMan.hasManager()) {
				currMan = currMan.getManager();
				manager.add(currMan);
				
			}
		}

		return manager;
	}

	// TODO: Does this class need custom .equals() and .hashcode() methods? If so,
	// implement them here.

	public boolean equals(Object obj) {
		if (obj instanceof Worker) {
			if (name.equals(((Worker) obj).getName())) {
				if (manager == (((Worker) obj).getManager())) {
					return true;
				}
			}
		}

		return false;

	}


	// TODO [OPTIONAL]: Consider adding a custom .toString() method here if you want
	// to debug your code with System.out.println() statements



	@Override
	public String toString() {
		String manager = hasManager() ? getManager().getName() : "No manager.";
		List<Manager> chainOfCommand = getChainOfCommand();
		StringBuilder chainOfCommandNames = new StringBuilder();
		if (chainOfCommand.isEmpty()) {
			chainOfCommandNames.append("No managers.");
		}
		if (chainOfCommand.size() == 1) {
			chainOfCommandNames.append(chainOfCommand.get(0).getName());
		} else {
			for (int x = 0; x < chainOfCommand.size(); x++) {
				chainOfCommandNames.append(chainOfCommand.get(x).getName()).append(", ");
				if (x == chainOfCommand.size() - 2) {
					chainOfCommandNames.append(chainOfCommand.get(++x).getName());
				}
			}
		}
		return "\nWorker" + "\nName:               " + getName() + "\nManager:            " + manager
				+ "\nHashcode:           " + hashCode() + "\nChain of command:   " + chainOfCommandNames + "\n";
	}
}
