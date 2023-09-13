package com.picpaySim.controllers;

import com.picpaySim.domain.operation.Operation;
import com.picpaySim.dto.OperationDTO;
import com.picpaySim.services.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/operations")
public class OperationController {
    @Autowired
    private OperationService operationService;

    @PostMapping
    public ResponseEntity<Operation> createOperation(@RequestBody OperationDTO operation) throws Exception {
        Operation newOperation = this.operationService.createOperation(operation);
        return new ResponseEntity<>(newOperation, HttpStatus.OK);
    }
}
