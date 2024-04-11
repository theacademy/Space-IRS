package com.wiley.schoolJPA.controller;

import com.wiley.schoolJPA.dao.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
@CrossOrigin
public class StudentController {
    @Autowired
    private StudentRepo studentRepo;

    @GetMapping("/students")
    public ResponseEntity<List<com.wiley.schoolJPA.entity.TaxGroup>> allStudents() {
        List<com.wiley.schoolJPA.entity.TaxGroup> taxGroups = studentRepo.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(taxGroups);
    }

    @GetMapping("/{id}")
    public ResponseEntity<com.wiley.schoolJPA.entity.TaxGroup> getStudentById(@PathVariable("id") Integer id) {
        com.wiley.schoolJPA.entity.TaxGroup taxGroup = studentRepo.findById(id).orElse(null);
        return new ResponseEntity<com.wiley.schoolJPA.entity.TaxGroup>(taxGroup, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addNewStudent(@RequestBody com.wiley.schoolJPA.entity.TaxGroup taxGroup) {
        studentRepo.save(taxGroup);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable("id") Integer id) {
        studentRepo.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update")
    public ResponseEntity<com.wiley.schoolJPA.entity.TaxGroup> updateStudent(@RequestBody com.wiley.schoolJPA.entity.TaxGroup taxGroup) {
        studentRepo.save(taxGroup);
        return new ResponseEntity<com.wiley.schoolJPA.entity.TaxGroup>(taxGroup, HttpStatus.OK);
    }
}
