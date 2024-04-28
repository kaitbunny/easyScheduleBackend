package com.easySchedule.backend.utils.paginatedresponse;
import org.springframework.data.domain.Page;

public class ResponseBuilder<T> {

	public static <T> PaginatedResponse<T> build(Page<T> result, Integer page) {
		return new PaginatedResponse<>(
				result.getContent(),
				result.getTotalPages(),
				result.getTotalElements(),
				page);
	}
	
}
