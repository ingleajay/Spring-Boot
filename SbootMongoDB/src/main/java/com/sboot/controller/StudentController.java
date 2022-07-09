package com.sboot.controller;

import com.sboot.helper.SequenceGeneratorService;
import com.sboot.models.Student;
import com.sboot.models.Subject;
import com.sboot.models.Teacher;
import com.sboot.repository.StudentRespository;
import com.sboot.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentRespository studentRespository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    // post student info..
    @PostMapping("/post")
    public ResponseEntity addStudent(@RequestBody Student student){
        if(student.getTeacher() != null){
            student.getTeacher().setId(sequenceGeneratorService.generateSequence(Teacher.SEQUENCE_NAME));
            teacherRepository.save(student.getTeacher());
        }
        Student save =  this.studentRespository.save(student);
        return ResponseEntity.ok(save);
    }

    // get all student info
    @GetMapping("/get")
    public List<Student> getStudent(){
        return this.studentRespository.findAll();
    }

    // by native
    // get student by name
    @GetMapping("/getbyqname/{name}")
    public ResponseEntity getStudentsByName(@PathVariable String name){
        return ResponseEntity.ok(this.studentRespository.getByName(name));
    }

    // get student by id
    @GetMapping("/getbyid/{studId}")
    public ResponseEntity getStudent(@PathVariable Integer studId){
        return ResponseEntity.ok(this.studentRespository.findById(studId));
    }

    // update studnet info
    @PutMapping("/update")
    public ResponseEntity updateStudent(@RequestBody Student student){
        return ResponseEntity.ok(studentRespository.save(student));
    }

    // delete student by id
    @DeleteMapping("/delete/{studId}")
    public ResponseEntity<String> deleteStudent(@PathVariable Integer studId){
        this.studentRespository.deleteById(studId);
        return ResponseEntity.ok("Student "+studId + " is deleted successfully..");
    }


     //get all subjects of one student
    @GetMapping("/get/{studId}/subjects")
    public ResponseEntity getAllSubjectByStudId(@PathVariable Integer studId){
        Student student = this.studentRespository.findById(studId).get();
        List<Subject> list = student.getSubject();
        return ResponseEntity.ok(list);
    }

    // get student by name
    @GetMapping("/getbyname/{name}")
    public ResponseEntity getStudentByName(@PathVariable String name){
        return ResponseEntity.ok(this.studentRespository.findByName(name));
    }

    // get student by id and name both
    @GetMapping("/getbynameid")
    public ResponseEntity getStudentByNameAndId(@RequestParam String name , @RequestParam Integer id){
        return ResponseEntity.ok(this.studentRespository.findByNameAndId(name,id));
    }

    // find all student by division
    @GetMapping("/getstud")
    public ResponseEntity getStudentBydivision(@RequestParam char division){
        return ResponseEntity.ok(this.studentRespository.findByClassRoomDivision(division));
    }

    // find all student by subjects
    @GetMapping("/getstudbysub")
    public ResponseEntity getStudentBySubject(@RequestParam String subname){
        return ResponseEntity.ok(this.studentRespository.findBySubjectSubName(subname));
    }


    // find stud data on the basis of pageno and page size
    // formula = (pageno-1)*pagesize = skip of data
    @GetMapping("/pagination")
    public ResponseEntity getAllPaginations(@RequestParam int pageNo , @RequestParam int pageSize){
        Pageable pageable = PageRequest.of(pageNo-1, pageSize);
        return ResponseEntity.ok(this.studentRespository.findAll(pageable).get());
    }

    // sort all studnet data on the basis of name in asc and desc
    @GetMapping("/sort")
    public ResponseEntity getSortedStudents(@RequestParam String sortDir,@RequestParam String sortBy){
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() :
                Sort.by(sortBy).descending();
        return ResponseEntity.ok(this.studentRespository.findAll(sort));
    }

    // get all students who's surname is "ingle"
    @GetMapping("/getstudbylike")
    public ResponseEntity getStudentLikeMatchedSurname(@RequestParam String surname){
        return ResponseEntity.ok(this.studentRespository.findByNameIsLike(surname));
    }

    // get student by start name
    @GetMapping("/getstudstart")
    public ResponseEntity getStudentBystartWith(@RequestParam String name){
        return ResponseEntity.ok(this.studentRespository.findByNameStartsWith(name));
    }
}
