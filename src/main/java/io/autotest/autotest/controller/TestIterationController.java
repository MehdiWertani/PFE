package io.autotest.autotest.controller;

import io.autotest.autotest.entities.TestIteration;
import io.autotest.autotest.service.ITestIterationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/Iteration")
public class TestIterationController {
    @Autowired
    ITestIterationService iTestIterationService ;
    @PostMapping("/save")

    public ResponseEntity<TestIteration> save (@RequestBody TestIteration testIteration){
       TestIteration testIteration1=iTestIterationService.save(testIteration);
       return new ResponseEntity<>(testIteration1, HttpStatus.CREATED);
    }
   @GetMapping("/getAll")
   public  ResponseEntity<List<TestIteration>> getAll(){
        List<TestIteration> testIterations=iTestIterationService.getAll();
        return new ResponseEntity<>(testIterations,HttpStatus.OK);
   }


    @DeleteMapping("/delete")
    public void delete(@RequestBody TestIteration testIteration){
        iTestIterationService.delete(testIteration);
    }
}
