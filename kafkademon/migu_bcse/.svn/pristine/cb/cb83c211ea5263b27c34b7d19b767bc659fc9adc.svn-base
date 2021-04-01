package com.chinamobile.cmss.bcselogAnalyse.bean;

public class Pair implements Comparable<Pair> {
	private String word;
	private Integer count;

	public Pair(String word, Integer count) {
		this.word = word;
		this.count = count;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Override
	public int compareTo(Pair o) {
		return count - o.count;
	}

	@Override
	public String toString() {
		return word + " " + count;
	}

	@Override
	public int hashCode() {
		return word.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		if (word.equals(((Pair) obj).word))
			return true;
		return false;
	}

}
