package com.reportingtool.scheduler;

import java.util.List;

/**
 * 
 * @author Esteban Calderon
 *
 * @param <E> : Error type
 */
public interface RTSchedulable<E> {

	public List<E> run();
}
