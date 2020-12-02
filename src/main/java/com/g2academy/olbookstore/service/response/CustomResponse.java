package com.g2academy.olbookstore.service.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

public class CustomResponse {

    public ResponseEntity<HashMap<String, String>> customerNotFound (){
        HashMap<String, String> map = new HashMap<>();
        map.put("message", "customer not found");
        return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<HashMap<String, String>> authorNotFound (){
        HashMap<String, String> map = new HashMap<>();
        map.put("message", "author not found");
        return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<HashMap<String, String>> publisherNotFound (){
        HashMap<String, String> map = new HashMap<>();
        map.put("message", "publisher not found");
        return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<HashMap<String, String>> bookNotFound (){
        HashMap<String, String> map = new HashMap<>();
        map.put("message", "book not found");
        return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<HashMap<String, String>> warehouseNotFound (){
        HashMap<String, String> map = new HashMap<>();
        map.put("message", "warehouse not found");
        return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<HashMap<String, String>> whBookNotFound (){
        HashMap<String, String> map = new HashMap<>();
        map.put("message", "warehouse-book not found");
        return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<HashMap<String, String>> transactionItemNotFound (){
        HashMap<String, String> map = new HashMap<>();
        map.put("message", "transaction item not found");
        return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<HashMap<String, String>> transactionNotFound (){
        HashMap<String, String> map = new HashMap<>();
        map.put("message", "transaction not found");
        return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<HashMap<String, String>> emailAlreadyExist (){
        HashMap<String, String> map = new HashMap<>();
        map.put("message", "email already registered ");
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }

}
