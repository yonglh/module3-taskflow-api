package sg.edu.ntu.taskflow_api.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import sg.edu.ntu.taskflow_api.model.Task;
import sg.edu.ntu.taskflow_api.repository.TaskRepository;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> findAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> findTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public Task createTask(Task task) {
        task.setId(null);
        return taskRepository.save(task);
    }

    public Task updateTask(Long id, Task task) {
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() ->
                        new NoSuchElementException("Task not found: " + id));

        existingTask.setTitle(task.getTitle());
        existingTask.setCompleted(task.isCompleted());
        return taskRepository.save(existingTask);
    }

    public boolean deleteTask(Long id) {
        return taskRepository.deleteById(id);
    }

    public Task markTaskAsComplete(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() ->
                        new NoSuchElementException("Task not found: " + id));

        task.setCompleted(true);
        return taskRepository.save(task);
    }
}
