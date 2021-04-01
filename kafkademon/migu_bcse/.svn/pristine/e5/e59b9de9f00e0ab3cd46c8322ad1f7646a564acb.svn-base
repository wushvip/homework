package com.chinamobile.cmss.bcselogAnalyse.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 实现了一个topN的优先级队列
 * 
 * @author jinjing
 *
 */
public class TopNPriorityQueue {

	private PriorityQueue1<Pair> queue;
	private int maxSize; // 堆的最大容量

	public TopNPriorityQueue(int maxSize) {
		if (maxSize <= 0)
			throw new IllegalArgumentException();
		this.maxSize = maxSize;
		this.queue = new PriorityQueue1<Pair>(maxSize);

	}

	/**
	 * 加入一个节点,如果这个节点很大，则加入
	 * 
	 * @param e
	 */
	public void add(Pair e) {

		if (queue.contains(e)) {
			int index = queue.indexOf(e);
			Pair old = queue.getIndex(index);
			if (old != null) {
				old.setCount(old.getCount() + e.getCount());
				System.out.println(old.toString());
			}

		} else { // 没有这个节点，尝试添加
			if (queue.size() < maxSize) { // 未达到最大容量，直接添加
				queue.add(e);
			} else { // 队列已满
				Pair peek = queue.peek();
				if (e.compareTo(peek) > 0) { // 将新元素与当前堆顶元素比较
					queue.poll();
					queue.add(e);
				}
			}
		}
	}

	/**
	 * true降序 false 升序
	 * 
	 * @param flag
	 * @return
	 */
	public List<Pair> sortedList(Boolean flag) {
		List<Pair> list = new ArrayList<Pair>(queue);
		if (flag) {// PriorityQueue本身的遍历是无序的，最终需要对队列中的元素进行排序
			Collections.sort(list, Collections.reverseOrder());
		} else {
			Collections.sort(list);
		}
		return list;
	}

}
