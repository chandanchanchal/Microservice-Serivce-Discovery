package com.test.springboot.learn_spring_springboot;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private List<Course> courses = new ArrayList<>();

    public CourseController() {
        courses.add(new Course(1, "Learn AWS", "Mavetsoft"));
        courses.add(new Course(2, "Learn DevOps", "Mavetsoft"));
    }

    // GET - Retrieve all courses
    @GetMapping
    public List<Course> getAllCourses() {
        return courses;
    }

    // POST - Add a new course
    @PostMapping
    public Course addCourse(@RequestBody Course course) {
        courses.add(course);
        return course;
    }

    // PUT - Update a course by ID
    @PutMapping("/{id}")
    public Course updateCourse(@PathVariable int id, @RequestBody Course updatedCourse) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getId() == id) {
                courses.set(i, updatedCourse);
                return updatedCourse;
            }
        }
        return null;
    }

    // DELETE - Remove a course by ID
    @DeleteMapping("/{id}")
    public String deleteCourse(@PathVariable int id) {
        Optional<Course> courseToRemove = courses.stream()
            .filter(course -> course.getId() == id)
            .findFirst();

        if (courseToRemove.isPresent()) {
            courses.remove(courseToRemove.get());
            return "Course removed with id: " + id;
        } else {
            return "Course not found with id: " + id;
        }
    }
}
