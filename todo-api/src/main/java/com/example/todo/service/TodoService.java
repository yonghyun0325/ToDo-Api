package com.example.todo.service;

import com.example.todo.entity.Todo;
import com.example.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    // 전체 조회
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    // 단건 조회
    public Todo getTodoById(Long id) {
        return todoRepository.findById(id).orElseThrow(() -> new RuntimeException("Todo Not Found"));
    }

    // 생성
    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }
    
    // 삭제
    public void deleteTodoById(Long id) {
        todoRepository.deleteById(id);
    }
}
