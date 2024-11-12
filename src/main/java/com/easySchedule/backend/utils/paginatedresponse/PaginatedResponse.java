package com.easySchedule.backend.utils.paginatedresponse;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.Getter;

@Getter
public class PaginatedResponse<T> {
	private List<T> items;
	private Integer totalPages;
	private Long totalItems;
	private Integer currentPage;
	
	public PaginatedResponse(List<T> items, Integer totalPages, Long totalItems, Integer currentPage) {
		this.items = items;
		this.totalPages = totalPages;
		this.totalItems = totalItems;
		this.currentPage = currentPage;
	}

	// Método map para transformar os itens da lista
	public <R> PaginatedResponse<R> map(Function<T, R> mapper) {
		List<R> transformedItems = this.items.stream()
				.map(mapper)
				.collect(Collectors.toList());
		return new PaginatedResponse<>(transformedItems, totalPages, totalItems, currentPage);
	}
}
