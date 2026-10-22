package sg.edu.ntu.taskflow_api.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import sg.edu.ntu.taskflow_api.model.Task;

@Repository
public class TaskRepository {

    private final Map<Long, Task> tasks = new HashMap<>();
    private long nextId = 1L;

    public List<Task> findAll() {
        return new ArrayList<>(tasks.values());
    }

    public Optional<Task> findById(Long id) {
        return Optional.ofNullable(tasks.get(id));
    }

    public Task save(Task task) {
        if (task.getId() == null) {
            task.setId(nextId++);
        }

        tasks.put(task.getId(), task);
        return task;
    }

    public boolean deleteById(Long id) {
        return tasks.remove(id) != null;
    }
}