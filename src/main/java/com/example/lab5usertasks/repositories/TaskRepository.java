package com.example.lab5usertasks.repositories;

import com.example.lab5usertasks.models.Task;
import com.example.lab5usertasks.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUser(UserModel user);
}
