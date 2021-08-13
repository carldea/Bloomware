package me.offeex.bloomware.client.setting;

import me.offeex.bloomware.client.module.Module;
import java.util.List;

public class Setting<T> {

	private final String name;
	private final Module module;

	private T value, max, min;
	private double inc;
	private List<String> modes;

	public Setting(String name, Module module, T value) {
		this.name = name;
		this.module = module;
		this.value = value;
	}

	public Setting(String name, Module module, T value, T min, T max, double inc) {
		this.name = name;
		this.module = module;
		this.value = value;
		this.min = min;
		this.max = max;
		this.inc = inc;
	}

	public Setting(String name, Module module, List<String> modes, T value) {
		this.name = name;
		this.module = module;
		this.value = value;
		this.modes = modes;
	}

	public String getType() {
		return value.getClass().getSimpleName();
	}

	public String getName() {
		return name;
	}

	public Module getModule() {
		return module;
	}

	public List<String> getModes() {
		return modes;
	}

	public double getInc() {
		return inc;
	}

	public T getMax() {
		return max;
	}

	public T getMin() {
		return min;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	//Math.round(Math.max(min, Math.min(max, (Double) value)) * precision) / precision
}