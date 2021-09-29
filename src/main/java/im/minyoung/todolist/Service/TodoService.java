package im.minyoung.todolist.Service;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import im.minyoung.todolist.Model.Todo;
import im.minyoung.todolist.Repository.TodoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

	@Autowired
	TodoRepository todoRepository;
	
	// 리스트를 가져오는 메소드
	public List<Todo> searchAll() {
		return todoRepository.findAll();
	}
	
	public void addTodo(Todo todo) {
		todoRepository.save(todo);
	}
	
	public Todo findById(Integer id) {
		Optional<Todo> updateTodo = todoRepository.findById(id);
		return updateTodo.get();
	}
	
	public void deleteAllTodo() {
		List<Todo> allTodo = todoRepository.findAll(); // 처음에 findall로 전부 담고
		List<Todo> doneList = new ArrayList<>(); // 공간만들고
		for(Todo todo:allTodo) {
			if(todo.isDone()) { // isDone해서 나온 것들만
				doneList.add(todo);
			}
		}
		todoRepository.deleteAll(doneList); // doneList에 담겨진 것만 삭제
	}
	
}
