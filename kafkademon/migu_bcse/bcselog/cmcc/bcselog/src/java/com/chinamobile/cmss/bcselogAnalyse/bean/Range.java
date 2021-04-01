package com.chinamobile.cmss.bcselogAnalyse.bean;


public class Range {

	private int min;
	private int max;

	public Range() {
	}

	public Range(int min, int max) {
		this.min = min;
		this.max = max;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	@Override
	public String toString() {
		return "[" + this.min + "-" + this.max + "]";
	}

	public static Range getRange(String rangeToString) {
		Range range = null;
		String regex = "^\\[\\d+-\\d+\\]$";
		if (rangeToString != null && rangeToString.matches(regex)) {
			String[] num = rangeToString.replaceAll("\\[", "").replaceAll("\\]", "").split("-");
			range = new Range(Integer.parseInt(num[0]), Integer.parseInt(num[1]));
		}
		return range;
	}

	public static void main(String[] args) {
		String regex = "^\\[\\d+-\\d+\\]$";
		String rangeToString = new Range(1, 2).toString();
		System.out.println(rangeToString.matches(regex));
	}

}