package com.easySchedule.backend.utils.paginatedresponse;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

public class PageableBuilder {
    private static final int ITENS_POR_PAGINA = 20;

    private void validateParams(Integer page, String sortProperty, String sortDirection) {
    	if (page == null || page < 1) {
            throw new IllegalArgumentException("O número da página deve ser maior que zero.");
        }

        if (sortProperty == null || sortProperty.trim().isEmpty()) {
            throw new IllegalArgumentException("A propriedade para ordenação não pode ser nula ou vazia.");
        }

        if (sortDirection == null || sortDirection.trim().isEmpty()) {
            throw new IllegalArgumentException("A direção da ordenação não pode ser nula ou vazia.");
        }
    }
    
    private String formatString(String string) {
    	return string.trim().toLowerCase();
    }
    
    private Direction validateDirection(String sortDirection) {
    	Direction direction;
    	
        try {
            direction = Sort.Direction.fromString(sortDirection);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("A direção da ordenação deve ser 'asc' ou 'desc'.");
        }
        
        return direction;
    }
    
    public Pageable build(Integer page, String sortProperty, String sortDirection) {
    	
        this.validateParams(page, sortProperty, sortDirection);

        sortDirection = this.formatString(sortDirection);

        Direction direction = this.validateDirection(sortDirection);
        
        int pageNumber = page - 1;

        return PageRequest.of(pageNumber, ITENS_POR_PAGINA, Sort.by(direction, sortProperty));
    }

    public Pageable build(Integer page, String sortProperty, String sortDirection, Integer itemsPerPage) {
    	
    	this.validateParams(page, sortProperty, sortDirection);
    	
    	if (itemsPerPage < 1) {
            throw new IllegalArgumentException("O número de itens por página deve ser maior que zero.");
        }
    	
    	sortDirection = this.formatString(sortDirection);
    	
    	Direction direction = this.validateDirection(sortDirection);
    	
    	int pageNumber = page - 1;
    	
    	return PageRequest.of(pageNumber, itemsPerPage, Sort.by(direction, sortProperty));
    }
    
}
