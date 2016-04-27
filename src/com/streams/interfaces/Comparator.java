package com.streams.interfaces;

import java.util.function.Function;

@FunctionalInterface
public interface Comparator<T> {
	public int compare(T t1, T t2);
	
	public static <U> Comparator<U> comparing(Function<U, Comparable> f){
		return (o1, o2) -> f.apply(o1).compareTo(f.apply(o2));
	}
}
