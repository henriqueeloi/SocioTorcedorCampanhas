package com.eloi.campanhas.infrastructure;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;

public abstract class CommonResource<T> extends Resources<T> {

	public CommonResource(Iterable<T> content, Iterable<Link> links) {
		super(content, links);
		// TODO Auto-generated constructor stub
	}

	public CommonResource(Iterable<T> content, Link... links) {
		super(content, links);
		// TODO Auto-generated constructor stub
	}

	public int getResults() {
		return this.getContent().size();
	}

}
