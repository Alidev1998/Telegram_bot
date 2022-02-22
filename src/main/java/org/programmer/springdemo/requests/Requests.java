package org.programmer.springdemo.requests;

import org.programmer.springdemo.dbhelper.DataBaseHelper;
import org.programmer.springdemo.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class Requests {

    @GetMapping("/users")
    public ResponseEntity getUsers() {
        return ResponseEntity.ok(new DataBaseHelper().users());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity getOne(@PathVariable("id") int id) {
        return ResponseEntity.ok(new DataBaseHelper().getOne(id));
    }

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody User user) {

        if (new DataBaseHelper().insertData(user)) {
            return ResponseEntity.ok("User successful added!");
        } else {
            return ResponseEntity.ok("Error to insert data!");
        }
    }

    @PutMapping("/update/user")
    public ResponseEntity update(@RequestBody User user) {

        int id = new DataBaseHelper().getUserId(user.getPhone());
        if (id == 0 || id == 1) {
            return ResponseEntity.ok("This user isn't exists!");
        } else {
            if (new DataBaseHelper().update(user, id)) {
                return ResponseEntity.ok("Data successfully updated!");
            } else {
                return ResponseEntity.ok("Data was not updated!");
            }
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") int id) {

        if (new DataBaseHelper().delete(id)) {
            return ResponseEntity.ok("Successful deleted");
        } else {
            return ResponseEntity.ok("Error to delete!");
        }
    }
}














