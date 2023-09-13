package com.picpaySim.repositories;

import com.picpaySim.domain.operation.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Operation, Long> {
}
