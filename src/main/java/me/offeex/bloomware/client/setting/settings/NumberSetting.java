package me.offeex.bloomware.client.setting.settings;

import me.offeex.bloomware.client.setting.Setting;

public class NumberSetting extends Setting {
	public double value;
	public double minimum;
	public double maximum;
	public double increment;
	  
	public NumberSetting(String name, double value, double minimum, double maximum, double increment) {
		this.setName(name);
	    this.value = value;
	    this.minimum = minimum;
	    this.maximum = maximum;
	    this.increment = increment;
	  }
	  
	public double getValue() {
	    return this.value;
	}
	  
	public void setValue(double value) {
	    double precision = 1.0D / this.increment;
	    this.value = Math.round(Math.max(this.minimum, Math.min(this.maximum, value)) * precision) / precision;
	}
	 
	public void increment(boolean positive) {
	    setValue(getValue() + (positive ? 1 : -1) * increment);
	}
	  
	public double getMinimum() {
	    return this.minimum;
	}

	public void setMinimum(double minimum) {
	    this.minimum = minimum;
	}
	  
	public double getMaximum() {
	    return this.maximum;
	}
	
	public void setMaximum(double maximum) {
	    this.maximum = maximum;
	}
	  
	public double getIncrement() {
	    return this.increment;
	}
	  
	public void setIncrement(double increment) {
	    this.increment = increment;
	}
}